# aspectj-examples

在网上看了很多文章，大多是介绍 AspectJ 语法（.aj、annotation）、Spring Aop 和 AspectJ 的关系。因此在这里做一次分享～

## AspectJ 是什么

AspectJ 是一个 AOP 的具体实现框架。AOP（Aspect Oriented Programming）即面向切面编程，可以通过预编译方式和运行期动态代理实现在不修改源代码的情况下给程序动态统一添加功能的一种技术。

### 织入时间

将定义横切关注点(切面)插入到编写的源代码中的过程，称为编织。 在 AspectJ 中，编织有3个不同的时间段:

1. Compile-time(CTW): 编译时编织，AJC编译器从源代码编译并生成编织好的文件输出。
2. Post-compile time: 编译后编织，用于编织已编译的类。（为了方便，在下文中将使用简写，`PCTW`）
3. Load-time(LTW): 加载时编织，在类加载器将类加载到JVM时编织，需要提供AspectJ Java Agent支持.

### AspectJ Jar

我们经常会使用到AspecJ提供的三个Jar包：

1. aspectjrt.jar: 提供运行时的注解、静态方法，基本上用到AspectJ都需要引入依赖（但我还未找到不需要的场景...）。
2. aspectjweaver.jar: 包含AspectJ运行时、编织器、编织类装入器和编织代理。它还包含用于解析XML编织配置文件的DTD。
3. aspectjtools.jar: 提供AJC编译器，可以在编译期将 `.java`、`.class`、`.aj`定义的切面编织到源代码中。

## 模块介绍

```txt
aspectj-examples
├── aspectj                         # LTW 相关Jar
├── aspectj-aj                      # Aspect.aj CTW示例
├── aspectj-java                    # Aspect.java CTW示例
├── aspectj-lombok                  # Aspect + Lombok PCTW示例
├── aspectj-lombok-ltw              # Aspect + Lombok LTW示例
├── springboot-aspectj              # SpringBoot + Aspect 示例
├── springboot-aspectj-lombok       # SpringBoot + Aspect + Lombok PCTW示例
└── springboot-aspectj-lombok-ltw   # SpringBoot + Aspect + Lombok LTW示例

```

各模块相互独立，父pom.xml仅定义依赖版本；各模块拥有说明文档提供简短说明、依赖、运行、输出等信息; 按模块顺序，模块中相同的说明信息不会重复出现。

> NOTE:
>
> 1. 除LTW（Load Time Weaving）外，其余模块都需要使用AJC编译器 (aspectj-maven-plugin 默认引入)。
>
> 2. lombok 模块，需要在'IDE'中设置'Post-compile weave'
>    - IDEA: 'Project Structure' -> 'Facets' -> 'xx-lombok-module' -> 'Compiler' -> 'Post-compile weave mode'
>    - Eclipse: [EJDT](https://www.eclipse.org/ajdt/)
> 3. 尝试在LTW中使用'.aj'文件，暂无解决方案。


## 参考

1. [The AspectJtm Development Environment Guide](https://www.eclipse.org/aspectj/doc/released/devguide/index.html)
2. [AspectJ and Lombok are not mutually exclusive](https://www.robingander.de/blog/2019/aspectj-spring-boot-lombok.html)
3. [Mojo's AspectJ Maven Plugin - aspectj:compile](https://www.mojohaus.org/aspectj-maven-plugin/compile-mojo.html)

4. [原生AspectJ用法分析以及SpringAOP原理分析](https://blog.mythsman.com/post/5d301cf2976abc05b34546be/)
