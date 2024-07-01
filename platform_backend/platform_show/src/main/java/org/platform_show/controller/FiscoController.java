package org.platform_show.controller;

import jakarta.annotation.Resource;
import org.platform_show.entity.RestBean;
import org.platform_show.entity.vo.response.transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Ll
 * @description: 与fisco交互的链接
 * @date 2024/6/29 下午5:10
 */

@RestController
@CrossOrigin
@RequestMapping("/api/fisco")
public class FiscoController {
    @Resource
    RestTemplate restTemplate;

    @GetMapping("/getData")
    public RestBean<List<transaction>> GetModel(@RequestParam String AgencyId) throws Exception {
        String url = "http://localhost:8081/api/getData?AgencyId={?}";
        return restTemplate.getForObject(url, RestBean.class, AgencyId);
    }

    @GetMapping("/Model")
    public ResponseEntity<byte[]> Model(@RequestParam String AgencyId, @RequestParam int File_id, @RequestParam String Round) throws Exception {
        String url = "http://localhost:8081/api/Model?AgencyId={?}&FileId={?}&Round={?}";
        return restTemplate.getForObject(url, ResponseEntity.class, AgencyId, File_id, Round);
    }

    @GetMapping("/getBlockAndTransactionNumber")
    public RestBean<String> getBlockAndTransactionNumber(@RequestParam String GroupId) {
        String url = "http://localhost:8081/api/getBlockAndTransactionNumber?GroupId={?}";
        return restTemplate.getForObject(url, RestBean.class, GroupId);
    }

    @GetMapping("/getLength")
    public RestBean<String> getLength(@RequestParam String AgencyId) throws Exception {
        String url = "http://localhost:8081/api/getLength?AgencyId={?}";
        return restTemplate.getForObject(url, RestBean.class, AgencyId);
    }
}