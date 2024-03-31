package xx.yy.zz.esdemo.temp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName LongCacheValuesTest.java
 * @Description @TODO
 * @createTime 2024年03月05日 13:04:00
 */

@ExtendWith(MockitoExtension.class)
public class LongCacheValuesTest {

    // -Djava.lang.Long.LongCache.high=500
    @Test
    void cachedValueTest() {
        Long l1 = (long) 200;
        Long l2 = (long) 200;

        assertThat(l1==l2).isTrue();
    }

    @Test
    void defaultCachedValueTest() {
        Long l1 = (long) 100;
        Long l2 = (long) 100;

        assertThat(l1==l2).isTrue();
    }

    @Test
    void showMsg() {
        System.out.println(getString());
    }

    String getString() {
        return "Hello";
    }
}
