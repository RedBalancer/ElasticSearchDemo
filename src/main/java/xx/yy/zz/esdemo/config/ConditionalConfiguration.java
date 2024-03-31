package xx.yy.zz.esdemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName ConditionalConfiguration.java
 * @Description matchIfMissing = true, 意味着没找到这个配置，就使这个配置有效。
 * 也就是havingValue是默认值
 * 参考：类TransactionAutoConfiguration，SpringBoot使用CGlib作为默认代理。
 * @createTime 2024年01月23日 09:44:00
 */

@ConditionalOnProperty(prefix = "xx.yy.zz", name ="condition", havingValue = "true", matchIfMissing = true)
@Configuration
public class ConditionalConfiguration {
    static {
        System.out.println("value configured");
    }

}
