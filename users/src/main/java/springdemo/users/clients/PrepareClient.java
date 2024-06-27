package springdemo.users.clients;

/**
 * @author lidong@date 2024-06-25@version 1.0
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface PrepareClient {

    void prepareDirty(String compKey);
}
