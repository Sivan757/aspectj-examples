package cn.kapukapu.aspectj;

/**
 * @author sivan
 */
public aspect AppAspect {
    pointcut run(): execution(* App.run());

    before(): run(){ System.out.println("before App.run()"); }

    after(): run(){ System.out.println("after App.run()"); }
}
