package org.platform_show.service;

import org.platform_show.entity.vo.request.DownloadRequest;
import org.platform_show.entity.vo.request.LogRequest;
import org.platform_show.entity.vo.request.TrainingRequest;
import org.platform_show.entity.vo.response.LogData;

import java.io.IOException;
import java.util.List;

/**
 * @program: AI-Medical-Platform
 * @description: 与python模型相关的服务
 * @author: 王贝强
 * @create: 2024-08-04 18:36
 */
public interface ModelService {
    String startTrainingTask(TrainingRequest trainingRequest);
    boolean getTrainingStatus();
    String stopTraining();
    List<LogData> getLogData(LogRequest logRequest);
    byte[] downloadModel(DownloadRequest request) throws IOException;
}
