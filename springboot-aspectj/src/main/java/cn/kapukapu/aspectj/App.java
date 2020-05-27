package cn.kapukapu.aspectj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sivan
 */
@EnableAsync(mode = AdviceMode.ASPECTJ)
@SpringBootApplication
public class App {

    @Async
    public void run() {
        System.out.printf("app run in thread[%d]%n", Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);
        App app = applicationContext.getBean(App.class);

        int i = 5;
        while (i > 0) {
            app.run();
            i--;
        }

        applicationContext.close();
    }
}
