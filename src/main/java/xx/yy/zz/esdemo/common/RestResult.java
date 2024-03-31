package xx.yy.zz.esdemo.common;

import lombok.Data;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName RestResult.java
 * @Description @TODO
 * @createTime 2024年01月19日 10:24:00
 */
@Data
public class RestResult<T> {
    private String message;
    private int status;
    private T data;
}
