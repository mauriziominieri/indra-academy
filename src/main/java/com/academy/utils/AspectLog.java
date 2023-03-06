package com.academy.utils;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Component
@Aspect
@Log4j2
public class AspectLog {

    @Pointcut("execution(* com.academy.controller.*.*(..))")
    private void controller() {}

    @Pointcut("execution(* com.academy.service.*.*(..))")
    private void service() {}

    @Pointcut("execution(* com.academy.mapper.*.*(..))")
    private void mapper() {}

    @Pointcut(//"@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void annotationCrud() {}

    @Before("controller() || service() || mapper()")
    public void allMethods(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.toString().substring(signature.toString().lastIndexOf(".") + 1);
        log.info(signature.getDeclaringType().getSimpleName() + " -> " + methodName);
    }

    @After("annotationCrud()")
    public void getMethods(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.toString().substring(signature.toString().lastIndexOf(".") + 1);
        log.info("SALVO A DB: " + signature.getDeclaringType().getSimpleName() + " -> " + methodName);
    }
//
//    @Around("")
//    public Object preProcessQueryPattern(ProceedingJoinPoint pjp) throws Throwable {
//        return pjp.proceed();
//    }
}