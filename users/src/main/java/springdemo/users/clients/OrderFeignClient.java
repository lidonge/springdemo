package springdemo.users.clients;

/**
 * @author lidong@date 2024-06-25@version 1.0
 */

import cache.IVirtualClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "order-service")
public interface OrderFeignClient extends PrepareClient{
    @Override
    @PostMapping("/orders/cache/prepareDirty/{compKey}")
    void prepareDirty(@PathVariable("compKey") String compKey);
}
