# 简介

本项目结合 SpringBoot 使用基于Java Annotation的切面。

在开发过程中发现使用 AspectJ 为切面实现后，无法注入 Bean 到切面类中。

1. 无法通过有参构造函数注入 Bean，会导致 Aspect 实例化错误。
2. 无法通过 `@Component` + `@Autowired` 注入，会导致空指针异常。

因为 AspectJ 编织过程不属于 Spring 管理，Spring 也不会将 Aspect 对象加载到容器中。在[Spring官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-aj-configure)中提到，可以通过手动创建Bean定义将切面对象加载到 Spring 容器中。

## SpringBoot Config

从AspectJ中获取切面的单例对象，加入Spring容器

```java
    @Bean
    public AppAspect appAspect(){
        return Aspects.aspectOf(AppAspect.class);
    }
```

## Output

输出可能像这样：

```
// App.main()
before App.run()
the name of this app is:OneApp  <-- 注入成功，并打印 app.name
app run
after App.run()
```

