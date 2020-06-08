# 文档

本项目结合Lombok使用基于Java Annotation的切面。

## Maven

### Dependency

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
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
            <phase>process-classes</phase>  <!--  修改插件执行阶段  -->
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
        <aspectDirectory>src/main/java</aspectDirectory>
        <weaveDirectories>   <!-- 修改编织目录为编译后文件夹  -->
            <weaveDirectory>${project.build.directory}/classes</weaveDirectory>
        </weaveDirectories>
        <sources/> <!-- 不编译源代码文件夹 -->
    </configuration>
</plugin>
```

## Output

```txt
// App.main()
before setAppName
after setAppName
before App.run()
app run
after App.run()
```
