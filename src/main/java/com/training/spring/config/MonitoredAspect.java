package com.training.spring.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitoredAspect {
    @Before("@annotation(Monitored)")
    public void logServiceBeforeCall(JoinPoint jp) {
        System.out.println("Appel finder " + jp.getSignature());
    }

    @AfterReturning(pointcut = "@annotation(Monitored)", returning = "site")
    public void logServiceAfterCall(JoinPoint jp, Object site) {
        if (site == null) {
            System.out.println("Finder " + jp.getTarget() + "returns null");
        } else {
            System.out.println("Finder " + jp.getTarget() + "returns "
                    + site.toString());
        }
    }
}
