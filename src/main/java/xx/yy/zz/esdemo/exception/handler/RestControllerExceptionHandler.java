package xx.yy.zz.esdemo.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xx.yy.zz.esdemo.common.RestResult;
import xx.yy.zz.esdemo.exception.BizException;
import xx.yy.zz.esdemo.exception.CommonException;
import xx.yy.zz.esdemo.exception.SysException;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName RestControllerExceptionHandler.java
 * @Description @TODO
 * @createTime 2024年01月19日 09:52:00
 */

//@ControllerAdvice
@RestControllerAdvice(basePackages = "xx.yy.zz.controller")
public class RestControllerExceptionHandler {
    /**
     * 5** error, need to hide from outside.
     * @return
     */
    @ExceptionHandler(value = SysException.class)
    public RestResult<String> handleSysException() {
        RestResult<String> sysResult = new RestResult<>();

        return sysResult;
    }

    /**
     * Other business error, can show to front
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public RestResult<String> handleBizException(CommonException e) {
        RestResult<String> bizResult = new RestResult<>();
        bizResult.setMessage(e.getErrorMessage());
        bizResult.setStatus(e.getErrorCode());
        bizResult.setData("Error Happened");
        return bizResult;
    }
}
