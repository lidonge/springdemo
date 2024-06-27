package springdemo.users.source;

import cache.IAsynListener;
import cache.IVirtualClient;
import springdemo.users.clients.PrepareClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidong@date 2023-10-25@version 1.0
 */
public class VirtualClient implements IVirtualClient {

//    @Autowired
//    private OrderFeignClient client;

    private PrepareClient prepareClient;

    private Map<String, Boolean> keysMap = new HashMap<>();

    //FIXME hard code
    private String name = "order-service";

    public VirtualClient(String name, PrepareClient discoveryClient) {
        this.name = name;
        this.prepareClient = discoveryClient;
    }

    public PrepareClient getPrepareClient() {
        return prepareClient;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void prepareDirty(String compKey, IAsynListener listener) {
        //TODO
//        List<ServiceInstance> instances = discoveryClient.getInstances(name);
//        if (instances == null || instances.isEmpty()) {
//            throw new IllegalArgumentException("No instances found for service: " + name);
//        }
//        String url = instances.get(0).getUri().toString();  // 获取第一个实例的URL
//        DynamicFeignClient client = Feign.builder()
////                .encoder(new JacksonEncoder())
////                .decoder(new JacksonDecoder())
//                .target(DynamicFeignClient.class, url);
        prepareClient.prepareDirty(compKey);
        listener.onSuccess();
    }

    @Override
    public void addKey(String key) {
        keysMap.put(key, true);
    }

    @Override
    public boolean hasKey(String key) {
        return keysMap.get(key) != null;
    }
}
