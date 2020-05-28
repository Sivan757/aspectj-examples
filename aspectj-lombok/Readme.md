# 文档

本项目结合Lombok使用基于Java Annotation的切面。

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
            <phase>process-classes</phase>  <!-- change compile phase  -->
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
        <weaveDirectories>   <!-- change weave directory  -->
            <weaveDirectory>${project.build.directory}/classes</weaveDirectory>
        </weaveDirectories>
        <sources/> <!-- Don't compile the source code folder -->
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
