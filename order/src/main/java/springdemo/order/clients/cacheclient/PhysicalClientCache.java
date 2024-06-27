package springdemo.order.clients.cacheclient;

import cache.IClientCacheData;
import cache.client.IClient;
import cache.client.IPhysicalCache;
import cache.util.ILogable;
import cache.util.LockerByName;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidong@date 2023-10-25@version 1.0
 */
@Service
public class PhysicalClientCache implements IPhysicalCache, ILogable {
    private Map<String, IClientCacheData> map = new HashMap<>();
    private LockerByName keyLocker = new LockerByName();
    private VirtualCenterInClient virtualCenter;
    private IClient client;

    public PhysicalClientCache() {
        this.virtualCenter = virtualCenter;
    }

    public void setVirtualCenter(VirtualCenterInClient virtualCenter) {
        this.virtualCenter = virtualCenter;
    }

    public void setClient(IClient client) {
        this.client = client;
    }

    @Override
    public IClientCacheData get(String key) {
        return map.get(key);
    }

    @Override
    public void put(String compKey, IClientCacheData cacheData) {
        map.put(compKey,cacheData);
    }

    @Override
    public void setDirty(String key) {
        map.remove(key);
    }

    @Override
    public boolean isDirty(String key) {
        return map.get(key) == null;
    }

    @Override
    public Object getLocker(String compKey) {
        return keyLocker;
    }

    @Override
    public boolean isKeyInit(String compKey) {
        return get(compKey) != null;
    }
}
