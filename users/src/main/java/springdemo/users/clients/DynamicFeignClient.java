package springdemo.users.clients;

/**
 * @author lidong@date 2024-06-25@version 1.0
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
public interface DynamicFeignClient {
//    @PostMapping("/cache/{compKey}")
    @RequestMapping(method = RequestMethod.GET, value = "/endpoint")
    void prepareDirty(@PathVariable("compKey") String compKey);
}