# 简介

本项目使用基于`.aj`文件的切面。

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
      <version>${aspectj.version}</version> <!-- 升级插件内依赖版本 -->
    </dependency>
  </dependencies>
  <executions>
    <execution>
      <goals>
        <goal>compile</goal> <!-- aspectj:compile。默认在Maven:compile阶段执行。 -->
      </goals>
    </execution>
  </executions>
  <configuration>
    <source>${maven.compiler.source}</source> <!-- 源代码Java版本 -->
    <target>${maven.compiler.target}</target> <!-- 编译后Java版本 -->
    <encoding>${project.build.sourceEncoding}</encoding>
    <complianceLevel>${maven.compiler.target}</complianceLevel>
    <Xlint>ignore</Xlint>  <!-- 忽略Xlint输出，隐藏console中的大片丑陋的红色提示 -->
    <showWeaveInfo>true</showWeaveInfo>  <!-- 显示编织信息 -->
    <forceAjcCompile>true</forceAjcCompile>  <!-- 强制使用AJC编译器 -->
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

