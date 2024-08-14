package org.platform_show.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.platform_show.entity.RestBean;
import org.platform_show.entity.vo.response.transaction;
import org.platform_show.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.util.List;

/**
 * @author wbq
 * @description: 与fisco交互的链接
 * @date 2024/6/29 下午5:10
 */

@RestController
@CrossOrigin
@RequestMapping("/api/fisco")
public class FiscoController {
    @Resource
    FileService fileService;
    @Resource
    RestTemplate restTemplate;

    @GetMapping("/getData")
    public RestBean<List<transaction>> GetModel(@RequestParam String AgencyId) throws Exception {
        String url = "http://localhost:8081/api/getData?AgencyId={?}";
         return restTemplate.getForObject(url, RestBean.class, AgencyId);
    }

    @GetMapping("/Model")
    public ResponseEntity<byte[]> Model(@RequestParam String GroupId,@RequestParam String AgencyId, @RequestParam String File_id, @RequestParam String Round,
                                        HttpServletResponse response) throws Exception {
        String url = "http://localhost:8081/api/Model?Group_id={?}&Agency_id={?}&File_id={?}&Round={?}";
        RestBean<String> path = restTemplate.getForObject(url, RestBean.class, GroupId, AgencyId, File_id, Round);
        if (path != null) {
            String string = path.data().substring(1,path.data().length()-1);
            response.setHeader("Content-Type","application/octet-stream");
            ServletOutputStream outputStream = response.getOutputStream();
            fileService.fetchModel(outputStream,string);
        }
        return null;
    }

    @GetMapping("/getBlockAndTransactionNumber")
    public RestBean<String> getBlockAndTransactionNumber(@RequestParam String GroupId) {
        String url = "http://localhost:8081/api/getBlockAndTransactionNumber?GroupId={?}";
        return restTemplate.getForObject(url, RestBean.class, GroupId);
    }

    @GetMapping("/getLength")
    public RestBean<String> getLength(@RequestParam String AgencyId) {
        String url = "http://localhost:8081/api/getLength?AgencyId={?}";
        return restTemplate.getForObject(url, RestBean.class, AgencyId);
    }

}