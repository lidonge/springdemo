package springdemo.order.aspects;

import com.alicp.jetcache.anno.Cached;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CacheAspect {

    private static final Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    Map<String, Object> localCache = new HashMap<>();
    @Around("@annotation(cached)")
    public Object aroundCachedMethod(ProceedingJoinPoint joinPoint, Cached cached) throws Throwable {
        logger.info("Before executing method: " + joinPoint.getSignature().getName() +",cache: "+ cached.name() + "," +cached.key() +","+cached.cacheType());

        String key = cached.name() + joinPoint.getArgs()[0];

        // Proceed with the original method call
        Object result = localCache.get(key);
        if(result == null) {
            result = joinPoint.proceed();
            localCache.put(key,result);
        }

        logger.info("After executing method: " + joinPoint.getSignature().getName());
        return result;
    }
}
