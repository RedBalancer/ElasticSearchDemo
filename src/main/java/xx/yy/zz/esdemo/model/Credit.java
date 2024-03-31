package xx.yy.zz.esdemo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName Credit.java
 * @Description For input from request
 * @createTime 2024年01月17日 13:08:00
 */

@Data
public class Credit {
    String creditDesc;
    Double minAmount;
    Double maxAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createEndDate;
}
