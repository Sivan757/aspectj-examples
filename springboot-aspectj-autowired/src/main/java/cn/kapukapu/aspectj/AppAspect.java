package cn.kapukapu.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sivan
 */
@Aspect
public class AppAspect {

    @Autowired App app;

    @Pointcut("execution (* cn.kapukapu.aspectj.App.run(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void before() {
        System.out.println("before App.run()");
        System.out.println("the name of this app is:" + app.name);
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after App.run()");
    }

}
