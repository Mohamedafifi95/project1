package com.skillstorm.warehouses.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

import java.util.Arrays;
/*
* I have created this class for monitoring the service preformance and debug the outputs
*
* */
@Aspect
@Component
public class MonitorAspect {
    Logger log = LoggerFactory.getLogger(MonitorAspect.class);

    @Pointcut("within(com.skillstorm.warehouses.services.ElectronicsService)")
     public void checkElectronics() {}
    @Pointcut("within(com.skillstorm.warehouses.services.WarehouseService)")
    public void checkWarehouses() {}
    @Pointcut("within(com.skillstorm.warehouses.controllers.WarehouseController)")
    public void checkControllerWarehouses() {}

    @AfterReturning(value = "checkElectronics()" , returning = "returnedData" )
    public void request(JoinPoint joinpoint, Object returnedData ) {

        log.debug("A response was sent from {} with the returned data: {}",
                joinpoint.getSignature().getName(),
                returnedData.toString());
    }

    @AfterReturning(value = "checkWarehouses()" , returning = "returnedData" )
    public void response(JoinPoint joinpoint, Object returnedData ) {

        log.debug("A response was sent from {} with the returned data: {}",
                joinpoint.getSignature().getName(),
                returnedData.toString());
    }
    @After("checkControllerWarehouses()")
    public void cotrollerResponse(JoinPoint joinpoint) {

        log.debug("A request was made to {} with the argument(s): {}",
                joinpoint.getSignature(), joinpoint.getTarget(),
                Arrays.toString(joinpoint.getArgs()));

    }

}
