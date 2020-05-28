# 简介

本项目结合SpringBoot使用基于Java Annotation的切面。

Spring中AOP默认使用JDK动态代理模式，在调用同类方法时，`this`关键字将指向对象本身，而非代理类，所以同类中的方法调用无法通过proxy增强。使用AspectJ可以解决这个问题。

## Maven

### Dependency

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
</dependency>
<!-- spring aspectJ 切面库 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
</dependency>
<!-- 切面库中包含事务切面，需要依赖tx -->
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
            <aspectLibrary> <!-- 指定切面库 -->
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
            </aspectLibrary>
        </aspectLibraries>
    </configuration>
</plugin>
```
## SpringBoot Config

SpringBoot中可以指定 @Async、@Transactional、@Cacheable 使用AspectJ代理。通过以下注解设置：

```java
@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableCaching(mode = AdviceMode.ASPECTJ)
```

## Output

如果使用默认的`@EnableAsync`，那么输出可能像这样：

```
// App.main()
runA in thread[21], runB in thread[21]
runA in thread[24], runB in thread[24]
runA in thread[22], runB in thread[22]
runA in thread[20], runB in thread[20]
runA in thread[23], runB in thread[23]
```

此时，异步方法runB运行在runA方法中，并非异步执行的。

若使用`@EnableAsync(mode = AdviceMode.ASPECTJ)`，将会输出：

```txt
// App.main()
runA in thread[21], runB in thread[23]
runA in thread[24], runB in thread[27]
runA in thread[22], runB in thread[26]
runA in thread[20], runB in thread[21]
runA in thread[23], runB in thread[25]
```

此时，在异步方法都通过代理调用，彼此运行在不同的线程上。

