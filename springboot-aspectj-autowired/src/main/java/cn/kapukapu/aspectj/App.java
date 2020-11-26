package cn.kapukapu.aspectj;

import org.aspectj.lang.Aspects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author sivan
 */
@SpringBootApplication
public class App {

    protected String name = "OneApp";

    public void run() {
        System.out.println("app run");
    }

    @Bean
    public AppAspect appAspect(){
        return Aspects.aspectOf(AppAspect.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);
        App app = applicationContext.getBean(App.class);

        app.run();

        applicationContext.close();
    }
}
