package org.platform_show.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.platform_show.entity.RestBean;
import org.platform_show.entity.UploadModel;
import org.platform_show.entity.vo.request.DownloadRequest;
import org.platform_show.entity.vo.request.LogRequest;
import org.platform_show.entity.vo.request.TrainingRequest;
import org.platform_show.entity.vo.response.Command;
import org.platform_show.entity.vo.response.JSONResponse;
import org.platform_show.entity.vo.response.LogData;
import org.platform_show.service.FileService;
import org.platform_show.service.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * @program: AI-Medical-Platform
 * @description: 模型训练接口类
 * @author: 王贝强
 * @create: 2024-08-04 18:58
 */
@Slf4j
@RestController
@RequestMapping("/api/model")
public class ModelController {
    String local_group_id="003";
    String global_group_id="001";
    @Resource
    RestTemplate restTemplate;
    @Resource
    ModelService modelService;
    @Resource
    FileService fileService;

    @PostMapping("/startTraining")
    public RestBean<String> startTraining(@RequestBody TrainingRequest trainingRequest){
        String command = trainingRequest.getCommand();
        String[] commandArray= command.split(" ");
        if (commandArray.length!=12){
            return RestBean.failure(401,"训练参数错误");
        }
        String modelPath = commandArray[3].subSequence(1,commandArray[3].length()-5).toString();
        String logPath=commandArray[5].substring(1,commandArray[5].length()-1);
        //模型训练参数解析
        Command com = new Command();
        com.setLogPath(logPath);
        com.setRound(commandArray[7]);
        com.setGlobalModelPath("/home/fox/Competition/result/"+modelPath+".pth");
        com.setLocalModelPath("/home/fox/Competition/result/"+modelPath+"_round/model_round");
        //模型训练命令
        String result = modelService.startTrainingTask(trainingRequest);
        if (result.contains("Training task has been started.")){
            //新建一个线程每隔60秒查询一次modelService.getLogData(vo)
            new Thread(() -> {
                List<LogData> ret=null;
                int index=1;
               do {
                    try {
                        Thread.sleep(60000);
                        //查询训练日志
                        LogRequest request=new LogRequest();
                        request.setFile_path(com.getLogPath());
                        request.setStart_round_number(1);
                        request.setEnd_round_number(Integer.parseInt(com.getRound()));
                        ret = modelService.getLogData(request);
                        //上传模型(循环上传)
                        if (ret!=null&&ret.size()>index){
                            for (int i = index; i <=ret.size(); i++,index++) {
                                DownloadRequest downloadRequest = new DownloadRequest();
                                downloadRequest.setFile_path(com.getLocalModelPath()+i+".pth");
                                int finalI = i;
                                List<LogData> finalRet = ret;
                                new Thread(() -> uploadModel(downloadRequest,command,local_group_id, finalI, finalRet)).start();
                            }
                        }
                    } catch (InterruptedException e) {
                        log.error("线程休眠失败");
                    }
               }while(ret!=null&&ret.size()!=Integer.parseInt(com.getRound()));

               if (modelService.getTrainingStatus()){
                   while (modelService.getTrainingStatus()){
                          try {
                            Thread.sleep(10000);
                          } catch (InterruptedException e) {
                            log.error("线程休眠失败");
                          }
                   }
               }
                //上传全局模型
                DownloadRequest downloadRequest = new DownloadRequest();
                downloadRequest.setFile_path(com.getGlobalModelPath());
                uploadModel(downloadRequest,command,global_group_id,1,null);
            }).start();
        }
        return RestBean.success(result);
    }
    @GetMapping("/getModelStatus")
    public RestBean<String> getStatus(){
        boolean status = modelService.getTrainingStatus();
        return status? RestBean.success("模型正在训练"):RestBean.success("模型已训练完成或者没有模型正在训练");
    }
    @PostMapping("/stop")
    public RestBean<String> stopTrain(){
        String success = modelService.stopTraining();
        return RestBean.success(success);
    }
    @PostMapping("/log")
    public RestBean<List<LogData>> getTrainLog(@RequestBody LogRequest vo){
        List<LogData> data = modelService.getLogData(vo);
        System.out.println(data);
        if (data!=null&&!data.isEmpty()){
            return RestBean.success(data);
        }
        return RestBean.success();
    }

    private void uploadModel(DownloadRequest downloadRequest,String command,String group_id,int i,List<LogData> ret) {
        try {
            byte[] model = modelService.downloadModel(downloadRequest);
            if (model!=null){
                //使用文件上传接口上传模型（minio存储）并返回模型地址
                String path = fileService.uploadModel(new ByteArrayInputStream(model));
                //将模型上传到区块链
                String url = "http://localhost:8081/api/uploadModel";
                UploadModel Model=new UploadModel();
                Model.setFile_id(downloadRequest.getFile_path());//模型名称
                Model.setFile_param(command); //模型运行命令
                Model.setRound(i); //模型当前训练轮次
                Model.setAgency_id(local_group_id); //医疗机构id
                Model.setGroup_id(group_id); //群组Id
                Model.setFile_content(path); //模型地址（minio存储地址）
                if (Objects.equals(group_id, global_group_id)) { //全局模型
                    Model.setReward(0); //模型激励值
                }else {
                    Model.setReward((int)(ret.get(i-1).getAccuracy() * 100)); //模型激励值
                }
                restTemplate.postForObject(url,Model,RestBean.class);
                if (!Objects.equals(group_id, global_group_id)) {
                    log.info("第{}轮模型:{}上链成功", i, downloadRequest.getFile_path());
                }else {
                    log.info("全局模型:{}上链成功", downloadRequest.getFile_path());
                }
            }
        } catch (IOException e) {
            log.error("第{}轮模型下载失败:{}", i,e.getMessage());
        }
    }

}
