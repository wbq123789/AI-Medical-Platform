package org.platform_show.service.impl;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.platform_show.entity.vo.request.DownloadRequest;
import org.platform_show.entity.vo.request.LogRequest;
import org.platform_show.entity.vo.request.TrainingRequest;
import org.platform_show.entity.vo.response.Log;
import org.platform_show.entity.vo.response.LogData;
import org.platform_show.service.ModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @program: AI-Medical-Platform
 * @description: python模型相关的服务
 * @author: 王贝强
 * @create: 2024-08-04 18:36
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Value("${Model.URL}")
    private String URL;
    @Resource
    RestTemplate restTemplate;
    public String startTrainingTask(TrainingRequest trainingRequest){
        String url = URL+"/train"; // 替换为实际接口地址
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TrainingRequest> requestEntity = new HttpEntity<>(trainingRequest, headers);
        return restTemplate.postForObject(url, requestEntity, String.class);
    }

    public boolean getTrainingStatus() {
        String result = restTemplate.getForObject(URL + "/train/status", String.class);
        if (result != null) {
            return !result.contains("No training task has been started.");
        }
        return false;
    }

    public String stopTraining() {
        String url = URL + "/stop";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.postForObject(url,entity, String.class);
    }

    public List<LogData> getLogData(LogRequest logRequest) {
        String url = URL + "/log";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LogRequest> entity = new HttpEntity<>(logRequest,headers);
        String result = restTemplate.postForObject(url, entity, String.class);
        Log log = JSONObject.parseObject(result, Log.class);
        Object[] data;
        List<LogData> list = null;
        if (log != null) {
            data = log.getData();
            list = Arrays.stream(data).map(d -> JSONObject.parseObject(d.toString(), LogData.class)).toList();
        }
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public byte[] downloadModel(DownloadRequest request) {
        try {
            String url = URL + "/download-model";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<DownloadRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<byte[]> resource = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
            return resource.getBody();
        }catch (Exception ignored){
            return null;
        }
    }
}
