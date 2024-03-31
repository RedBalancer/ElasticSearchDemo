package xx.yy.zz.esdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.util.Collections;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName SpringApplication.java
 * @Description @TODO
 * @createTime 2024年01月12日 10:19:00
 */

@SpringBootApplication
public class ESSearchSpringApplication {
    public static void main(String[] args) {
        String testProfile = "local";

        SpringApplication springApplication = new SpringApplication(ESSearchSpringApplication.class);
        springApplication.setDefaultProperties(Collections.singletonMap("spring.profiles.default", testProfile));
        springApplication.addListeners(new ApplicationPidFileWriter());

        springApplication.run(args);

    }
}
