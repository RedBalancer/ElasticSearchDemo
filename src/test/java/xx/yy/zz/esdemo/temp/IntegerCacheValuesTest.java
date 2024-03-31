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
public class IntegerCacheValuesTest {

    // -Djava.lang.Integer.IntegerCache.high=200
    @Test
    void cachedValueTest() {
        Integer l1 = 200;
        Integer l2 = 200;

        assertThat(l1==l2).isTrue();
    }

    @Test
    void defaultCachedValueTest() {
        Integer l1 = 100;
        Integer l2 = 100;

        assertThat(l1==l2).isTrue();
    }
}
