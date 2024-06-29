package org.platform_fisco.cotroller;

import jakarta.annotation.Resource;
import org.platform_fisco.entity.RestBean;
import org.platform_fisco.entity.transaction;
import org.platform_fisco.service.FileService;
import org.platform_fisco.utils.JsonUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: AI-Medical-Platform
 * @description: 模型下载、数据获取接口类
 * @author: 王贝强
 * @create: 2024-06-29 16:14
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class FileController {
    @Resource
    FileService service;

    @GetMapping("/getData")
    public RestBean<List<transaction>> GetModel(@RequestParam String AgencyId) throws Exception {
        if(AgencyId.equals("001")) {
            List<transaction> ret=new ArrayList<>();
            for(int i=2;i<6;i++) {
                ret.addAll(service.selectById(1, "00" + i));
            }
            return RestBean.success(ret);
        }else{
            List<transaction> transactions = service.selectById(Integer.parseInt(AgencyId),AgencyId);
            return RestBean.success(transactions);
        }
    }

    @GetMapping("/Model")
    public ResponseEntity<byte[]> Model(@RequestParam String AgencyId,@RequestParam int File_id,@RequestParam String Round) throws Exception {
        Object o = service.selectByIdAndRound(1,AgencyId,File_id,Integer.parseInt(Round));
        String jsonData = JsonUtil.toJson(o);

        //将JSON字符串转换为字节数组
        byte[] contentBytes = jsonData.getBytes(StandardCharsets.UTF_8);

        // 设置HTTP Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8)); // 设置内容类型为JSON
        headers.setContentDispositionFormData("attachment", "model.json"); // 将文件作为附件下载，并设置下载时的文件名为model.json

        // 创建并返回ResponseEntity对象
        return new ResponseEntity<>(contentBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/getBlockAndTransactionNumber")
    public RestBean<String> getBlockAndTransactionNumber(@RequestParam String GroupId) {
        String totalTransactionCount = service.getTotalTransactionCount(Integer.parseInt(GroupId));
        return RestBean.success(totalTransactionCount);
    }

    @GetMapping("/getLength")
    public RestBean<String> getLength(@RequestParam String AgencyId) throws Exception {
        List<transaction> transactions = service.selectById(1, AgencyId);
        return RestBean.success(JsonUtil.toJson(transactions.size()));
    }
}
