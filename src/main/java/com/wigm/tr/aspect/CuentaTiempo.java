package com.wigm.tr.aspect;

import com.wigm.tr.exception.ManejadorExcepciones;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CuentaTiempo {

    private static final Logger logger =
            LoggerFactory.getLogger(CuentaTiempo.class);

    @Around("@annotation(Tiempo)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.info("Endpoint: " + joinPoint.getSignature().getName() +
                " ejecutado en: " + executionTime + "ms");

        return result;
    }
}