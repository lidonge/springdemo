package springdemo.users.source;

import cache.IVirtualClient;
import cache.util.ILogable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import springdemo.users.clients.OrderFeignClient;
import springdemo.users.clients.PrepareClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidong@date 2024-06-26@version 1.0
 */
@Service
public class VirtualClients implements ILogable {
    private Map<String, IVirtualClient> clients = new HashMap<>();
//    @Autowired
//    private DiscoveryClient discoveryClient;
    @Autowired
    private OrderFeignClient orderFeignClient;
    public VirtualClients(){
        getLogger().info("Init virtual clients order-service :" + orderFeignClient);
        clients.put("order-service",new VirtualClient("order-service", orderFeignClient));
    }
    public IVirtualClient get(String name){
        IVirtualClient ret = clients.get(name);
        if(ret instanceof VirtualClient) {
            PrepareClient prepareClient = ((VirtualClient) ret).getPrepareClient();
            getLogger().info("Gets virtual clients order-service :" + orderFeignClient);
            getLogger().info("Service in  virtual clients :" + prepareClient);
            //FIXME
            if (prepareClient == null) {
                ret = new VirtualClient("order-service", orderFeignClient);
                getLogger().info("Re-Init virtual clients order-service :" + orderFeignClient);
                clients.put("order-service", ret);
            }
        }

        return ret;
    }
}
