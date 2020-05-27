package cn.kapukapu.aspectj;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sivan
 */
@Getter
@Setter
@Accessors(chain = true)
public class App {
    private String appName;

    public void run(){
        System.out.println(this.getAppName() + " run");
    }

    public static void main(String[] args) {
        new App().setAppName("app").run();
    }
}


