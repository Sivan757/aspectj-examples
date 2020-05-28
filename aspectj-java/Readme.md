# 简介

本项目使用基于Java Annotation的切面。

## Maven

### Dependency

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
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
        <aspectDirectory>src/main/java</aspectDirectory> <!-- 指定 Java Aspect 路径 -->
    </configuration>
</plugin>
```

## Output

```txt
// App.main()
before App.run()
app run
after App.run()
```

