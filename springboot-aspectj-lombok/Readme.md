# 简介

本项目结合SpringBoot和Lombok使用基于Java Annotation的切面。

Spring中AOP默认使用JDK动态代理模式，在调用同类方法时，`this`关键字将指向对象本身，而非代理类，所以同类中的方法调用无法通过proxy增强。使用AspectJ可以解决这个问题。

## Maven

### Dependency

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
</dependency>
```

### Plugin

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>aspectj-maven-plugin</artifactId>
    <dependencies>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjtools</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
    </dependencies>
    <executions>
        <execution>
            <phase>process-classes</phase>
            <goals>
                <goal>compile</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <source>${maven.compiler.source}</source>
        <target>${maven.compiler.target}</target>
        <encoding>${project.build.sourceEncoding}</encoding>
        <complianceLevel>${maven.compiler.target}</complianceLevel>
        <Xlint>ignore</Xlint>
        <showWeaveInfo>true</showWeaveInfo>
        <forceAjcCompile>true</forceAjcCompile>
        <aspectLibraries>
            <aspectLibrary>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
            </aspectLibrary>
        </aspectLibraries>
        <weaveDirectories>${project.build.outputDirectory}</weaveDirectories> 
        <sources/>
    </configuration>
</plugin>
```

## Output

```txt
// App.main()
INFO 9669 --- [ task-6] cn.kapukapu.aspectj.App: runA in thread[17], runB in thread[22]
INFO 9669 --- [ task-7] cn.kapukapu.aspectj.App: runA in thread[19], runB in thread[23]
INFO 9669 --- [ task-5] cn.kapukapu.aspectj.App: runA in thread[20], runB in thread[21]
INFO 9669 --- [ task-8] cn.kapukapu.aspectj.App: runA in thread[18], runB in thread[24]
INFO 9669 --- [ task-3] cn.kapukapu.aspectj.App: runA in thread[21], runB in thread[19]
```
