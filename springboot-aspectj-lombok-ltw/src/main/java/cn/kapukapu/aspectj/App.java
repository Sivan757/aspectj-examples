package cn.kapukapu.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sivan
 */
@Slf4j
@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
@SpringBootApplication
public class App {

    @Async
    public void run() {
        log.info("app run in thread{}", Thread.currentThread().getId());
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
