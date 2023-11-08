package ru.liga.deliveryservice.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
@Aspect
public class DeliveryServiceLogging {

    private final Logger logger = LogManager.getLogger(DeliveryServiceLogging.class);


    @Pointcut("execution(* ru.liga.deliveryservice.service.DeliveryService.getDeliveries(..))")
    public void getDeliveries() {
    }

    @Pointcut("execution(* ru.liga.deliveryservice.service.DeliveryService.take(..))")
    public void take() {
    }

    @Pointcut("execution(* ru.liga.deliveryservice.service.DeliveryService.complete(..))")
    public void complete() {
    }

    @Before("getDeliveries() || take() || complete()")
    public void logBeforeCallingMethod(JoinPoint jointPoint) {
        logger.info("[" + new Date() + "] Метод "
                + jointPoint.getSignature().getName()
                + " вызывается. В него передаются данные: "
                + Arrays.toString(jointPoint.getArgs()));
    }

    @AfterReturning(pointcut = "getDeliveries() || take() || complete()", returning = "result")
    public void logAfterSuccessfulEnding(JoinPoint joinPoint, Object result) {
        logger.info("[" + new Date() + "] Метод "
                + joinPoint.getSignature().getName()
                + " завершился успешно и вернул " + result.toString());
    }

    @AfterThrowing(pointcut = "getDeliveries() || take() || complete()", throwing = "exception")
    public void logAfterExceptionEnding(JoinPoint joinPoint, Throwable exception) {
        logger.info("[" + new Date()
                + "] Метод " + joinPoint.getSignature().getName() + " завершился неудачно, вызвав ошибку "
                + exception.getClass().getName()
                + " с сообщением " + exception.getMessage()
                + " и StackTrace " + Arrays.toString(exception.getStackTrace())
        );
    }
}
