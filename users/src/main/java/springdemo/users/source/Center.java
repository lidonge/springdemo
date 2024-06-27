package springdemo.users.source;

import cache.center.ICenter;
import cache.center.IPhysicalCenter;
import org.springframework.stereotype.Service;

/**
 * @author lidong@date 2023-10-25@version 1.0
 */
@Service
public class Center implements ICenter {
    private PhysicalCenter physicalCenter = new PhysicalCenter();

    @Override
    public boolean isAgreementReached(String compKey) {
        return physicalCenter.isAgreementReached(compKey);
    }

    @Override
    public IPhysicalCenter getPhysicalCenter() {
        return physicalCenter;
    }

}
