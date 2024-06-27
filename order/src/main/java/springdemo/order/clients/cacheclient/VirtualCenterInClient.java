package springdemo.order.clients.cacheclient;

import cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdemo.order.clients.UserFeignClient;
import springdemo.users.models.AppUser;

/**
 * @author lidong@date 2023-10-25@version 1.0
 */
@Service
public class VirtualCenterInClient implements IVirtualCenterInClient {
    @Autowired
    private UserFeignClient center;
    //FIXME here is hard code
    static final String CENTER_NAME = "user-service";
    static final String CLIENT_NAME = "order-service";

    public VirtualCenterInClient() {
    }

    @Override
    public boolean registerClient(String name, String key, IBaseClient client) {
        return center.registerClient(name,key);
    }

    @Override
    public void unregisterClient(String name) {
        center.unregisterClient(name);
    }

    @Override
    public ICacheData get(String compKey) {
        AppUser user = center.getUser(compKey);;
        return new IClientCacheData() {
            boolean bPrepareDirty;
            @Override
            public boolean isPrepareDirty() {
                return bPrepareDirty;
            }

            @Override
            public void setPrepareDirty(boolean b) {
                bPrepareDirty = b;
            }

            @Override
            public Object getValue() {
                return user;
            }
        };
    }

    @Override
    public void put(String compKey, ICacheData cacheData) {
        center.put((AppUser) cacheData.getValue());
    }

    @Override
    public boolean isAgreementReached(String compKey) {
        return center.isAgreementReached(compKey);
    }
}
