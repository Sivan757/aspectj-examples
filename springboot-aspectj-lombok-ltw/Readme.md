# 简介

本项目结合SpringBoot和Lombok使用基于Java Annotation的切面，在加载时织入(LTW)。

Spring中AOP默认使用JDK动态代理模式，在调用同类方法时，`this`关键字将指向对象本身，而非代理类，所以同类中的方法调用无法通过proxy增强。使用AspectJ可以解决这个问题。

## Maven

### Dependency

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
</dependency>
```

### Plugin
无

## AspectJ LTW
LTW需要`META-INF/aop.xml`配置文件来通知类加载器切面、类所在的路径。
```xml
<?xml version="1.0"?>
<aspectj>
    <weaver options="-showweaveInfo -Xlint:ignore">
        <!-- 指定编织路径 -->
        <include within="cn.kapukapu.aspectj.**"/>
    </weaver>
</aspectj>
```

`spring-aspects`包中提供提供了另一份`META-INF/aop.xml`，指定了切面类的路径。
```xml
<?xml version="1.0"?>

<!--
	AspectJ load-time weaving config file to install common Spring aspects.
-->
<aspectj>

	<!--
	<weaver options="-showWeaveInfo"/>
	-->

	<aspects>
		<aspect name="org.springframework.beans.factory.aspectj.AnnotationBeanConfigurerAspect"/>
		<aspect name="org.springframework.scheduling.aspectj.AnnotationAsyncExecutionAspect"/>
		<aspect name="org.springframework.transaction.aspectj.AnnotationTransactionAspect"/>
		<aspect name="org.springframework.transaction.aspectj.JtaAnnotationTransactionAspect"/>
		<aspect name="org.springframework.cache.aspectj.AnnotationCacheAspect"/>
		<aspect name="org.springframework.cache.aspectj.JCacheCacheAspect"/>
	</aspects>

</aspectj>
```

## Run in IDEA

添加编织代理
`-javaagent:$ProjectFileDir$/aspectj/spring-lib/spring-instrument-5.2.6.RELEASE.jar`  
`-javaagent:$ProjectFileDir$/aspectj/lib/aspectjweaver-1.9.5.jar`。

![](http://file.kapukapu.cn/img/20200527231827.png?imgslim)


## Output

```txt
// App.main()
INFO 9865 --- [task-6] cn.kapukapu.aspectj.App : runA in thread[19], runB in thread[22]
INFO 9865 --- [task-5] cn.kapukapu.aspectj.App : runA in thread[21], runB in thread[21]
INFO 9865 --- [task-8] cn.kapukapu.aspectj.App : runA in thread[18], runB in thread[23]
INFO 9865 --- [task-3] cn.kapukapu.aspectj.App : runA in thread[20], runB in thread[19]
INFO 9865 --- [task-7] cn.kapukapu.aspectj.App : runA in thread[17], runB in thread[24]
```
