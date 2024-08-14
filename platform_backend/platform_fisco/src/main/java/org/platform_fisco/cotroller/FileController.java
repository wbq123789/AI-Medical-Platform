package org.platform_fisco.cotroller;

import jakarta.annotation.Resource;
import org.platform_fisco.entity.RestBean;
import org.platform_fisco.entity.transaction;
import org.platform_fisco.entity.vo.request.UploadModel;
import org.platform_fisco.entity.vo.request.updateReward;
import org.platform_fisco.service.FileService;
import org.platform_fisco.utils.JsonUtil;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(AgencyId);
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
    @PostMapping("/uploadModel")
    public RestBean<Void> update(@RequestBody UploadModel vo) throws Exception {
        boolean result = service.insertFile(Integer.parseInt(vo.getGroup_id()),vo.getAgency_id(),vo.getFile_id(),vo.getFile_param(),vo.getFile_content(),vo.getRound(),vo.getReward());
        return Boolean.TRUE== result ? RestBean.success() : RestBean.failure(401,"文件上传失败");
    }
    @PostMapping("updateReword")
    public RestBean<Void> updateReword(@RequestBody updateReward vo) throws Exception {
        boolean result = service.updateReward(Integer.parseInt(vo.getAgency_id()),vo.getAgency_id(),vo.getFile_id(), vo.getRound(),vo.getReward());
        return Boolean.TRUE== result ? RestBean.success() : RestBean.failure(401,"模型激励值更新失败");
    }
    @GetMapping("/Model")
    public RestBean<String> Model(@RequestParam String Group_id,@RequestParam String Agency_id,@RequestParam String File_id,@RequestParam String Round) throws Exception {
        Object o = service.selectByIdAndRound(Integer.parseInt(Group_id),Agency_id,File_id,Integer.parseInt(Round));
        return RestBean.success(o.toString());
    }

    @GetMapping("/getBlockAndTransactionNumber")
    public RestBean<String> getBlockAndTransactionNumber(@RequestParam String GroupId) {
        String totalTransactionCount = service.getTotalTransactionCount(Integer.parseInt(GroupId));
        return RestBean.success(totalTransactionCount);
    }

    @GetMapping("/getLength")
    public RestBean<String> getLength(@RequestParam String AgencyId) throws Exception {
        List<Object> transactions = service.selectById_01(Integer.parseInt(AgencyId), AgencyId);
        return RestBean.success(JsonUtil.toJson(transactions.size()));
    }
}
