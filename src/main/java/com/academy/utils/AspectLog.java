package com.academy.utils;

import com.academy.dto.LogDto;
import com.academy.service.LogService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

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

    @Value("${server.servlet.context-path}")
    private String module;

    private String controllerName;

    private String signatureString;

    @Autowired
    LogService logService;

    @Pointcut("execution(* com.academy.controller.*.*(..))")
    private void controller() {}

    @Pointcut("execution(* com.academy.service.*.*(..))")
    private void service() {}

    @Pointcut("execution(* com.academy.component.*.*(..))")
    private void worker() {}

    @Pointcut("execution(* com.academy.mapper.*.*(..))")
    private void mapper() {}

    @Pointcut(//"@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void saveLog() {}   // decido di salvare solo le loggate Post Put e Delete

    private void getInformations(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        this.controllerName = method.getDeclaringClass().getSimpleName();
        StringJoiner signatureBuilder = new StringJoiner(", ", method.getName() + "(", ")");
        for (int i = 0; i < method.getParameterCount(); i++)
            signatureBuilder.add(method.getParameterTypes()[i].getSimpleName() + " " + signature.getParameterNames()[i]);
        this.signatureString = signatureBuilder.toString();
    }

    @Before("controller() || service() || worker()")
    public void logs(JoinPoint joinPoint) {
        getInformations(joinPoint);
        log.info(controllerName + " -> " + signatureString);
    }

    @Before("mapper()")
    public void mapper(JoinPoint joinPoint) {
        getInformations(joinPoint);
        if(joinPoint.getArgs()[0] instanceof List) {
            List list = (List) joinPoint.getArgs()[0];
            if(!list.isEmpty()) {
                String classType = list.get(0).getClass().getSimpleName();
                log.debug(String.format("%s trovati: %s", classType, list.size()));
            }
        }
        log.debug(controllerName + " -> " + signatureString);
    }

    @Around("saveLog()")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        getInformations(joinPoint);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Object response = joinPoint.proceed();  // eseguo il metodo in questione, se lancia un'eccezione non continuo e quindi non salvo la loggata, lo stesso risultato lo avremmo con @AfterReturning

        String action = method.isAnnotationPresent(GetMapping.class) ? "READ"
                        : method.isAnnotationPresent(PostMapping.class) ? "CREATE"
                        : method.isAnnotationPresent(PutMapping.class) ? "UPDATE"
                        : method.isAnnotationPresent(DeleteMapping.class) ? "DELETE"
                        : "N/A";

        int i = 0;
        JSONObject jo = new JSONObject();
        for (String  s : signature.getParameterNames()) {
            jo.put(s, joinPoint.getArgs()[i] == null ? "null" : joinPoint.getArgs()[i].toString());
            i++;
        }

        LogDto logDto = new LogDto();
        logDto.setLogDate(new Date());
        logDto.setModule(this.module.replace("/", ""));
        logDto.setAction(action);
        logDto.setDescription(controllerName + " -> " + signatureString);
        logDto.setDetail(jo.toString().getBytes());

        logService.add(logDto);

        return response;
    }
}