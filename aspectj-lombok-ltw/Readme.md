# 简介

本项目结合Lombok使用基于Java Annotation的切面，在加载时织入(LTW)。

## Maven

### Dependency

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
</dependency>
```

### Plugin

无

## AspectJ LTW
LTW需要`META-INF/aop.xml`配置文件来通知类加载器切面、类所在的路径。
```xml
<?xml version="1.0"?>
<aspectj>
    <weaver options="-showweaveInfo -Xlint:ignore"/>
    <aspects>
        <aspect name="cn.kapukapu.aspectj.AppAspect"/>
    </aspects>
</aspectj>

```

## Run in IDEA

添加编织代理`-javaagent:$ProjectFileDir$/aspectj/lib/aspectjweaver-1.9.5.jar`。

![](http://file.kapukapu.cn/img/20200527231827.png?imgslim)

## Output

```text
[AppClassLoader@18b4aac2] weaveinfo Join point 'method-execution(void cn.kapukapu.aspectj.App.run())' in Type 'cn.kapukapu.aspectj.App' (App.java:9) advised by before advice from 'cn.kapukapu.aspectj.AppAspect' (AppAspect.java)
[AppClassLoader@18b4aac2] weaveinfo Join point 'method-execution(void cn.kapukapu.aspectj.App.run())' in Type 'cn.kapukapu.aspectj.App' (App.java:9) advised by after advice from 'cn.kapukapu.aspectj.AppAspect' (AppAspect.java)
before App.run()
app run
after App.run()
```
