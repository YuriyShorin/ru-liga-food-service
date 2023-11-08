package ru.liga.kitchenservice.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
@Aspect
public class KitchenServiceLogging {

    private final Logger logger = LogManager.getLogger(KitchenServiceLogging.class);

    @Pointcut("execution(* ru.liga.kitchenservice.service.KitchenService.accept(..))")
    public void accept() {
    }

    @Pointcut("execution(* ru.liga.kitchenservice.service.KitchenService.decline(..))")
    public void decline() {
    }

    @Pointcut("execution(* ru.liga.kitchenservice.service.KitchenService.ready(..))")
    public void ready() {
    }

    @Before("accept() || decline() || ready()")
    public void logBeforeCallingMethod(JoinPoint jointPoint) {
        logger.info("[" + new Date() + "] Метод "
                + jointPoint.getSignature().getName()
                + " вызывается. В него передаются данные: "
                + Arrays.toString(jointPoint.getArgs()));
    }

    @AfterReturning(pointcut = "accept() || decline() || ready()", returning = "result")
    public void logAfterSuccessfulEnding(JoinPoint joinPoint, Object result) {
        logger.info("[" + new Date() + "] Метод "
                + joinPoint.getSignature().getName()
                + " завершился успешно и вернул " + result.toString());
    }

    @AfterThrowing(pointcut = "accept() || decline() || ready()", throwing = "exception")
    public void logAfterExceptionEnding(JoinPoint joinPoint, Throwable exception) {
        logger.info("[" + new Date()
                + "] Метод " + joinPoint.getSignature().getName() + " завершился неудачно, вызвав ошибку "
                + exception.getClass().getName()
                + " с сообщением " + exception.getMessage()
                + " и StackTrace " + Arrays.toString(exception.getStackTrace())
        );
    }
}
