package xx.yy.zz.esdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName CommonException.java
 * @Description @TODO
 * @createTime 2024年01月19日 10:44:00
 */
@Data
@AllArgsConstructor
public class CommonException extends RuntimeException {
    int errorCode;
    String errorMessage;
}
