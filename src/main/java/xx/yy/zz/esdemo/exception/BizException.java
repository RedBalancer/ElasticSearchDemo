package xx.yy.zz.esdemo.exception;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName BizException.java
 * @Description @TODO
 * @createTime 2024年01月19日 10:27:00
 */

public class BizException extends CommonException {
    public BizException(int code, String msg) {
        super(code, msg);
    }
}
