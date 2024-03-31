package xx.yy.zz.esdemo.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName CreditEntity.java
 * @Description @TODO
 * @createTime 2024年01月17日 11:31:00
 */

@Data
@Entity(name="CREDIT")
public class CreditEntity {
    @Id
    @Column(name="CREDIT_ID")
    private Long creditId;

    @Column(name="AMOUNT")
    private Double amount;

    @Column(name="CREDIT_DESCRIPTION")
    String creditDesc;

//    @CreationUser
    @Column(name="OPERATE_USER")
    private String operateUser;

    @CreationTimestamp
    @Column(name="SYS_CREATION_DATE")
    LocalDateTime sysCreationDate;

    @UpdateTimestamp
    @Column(name="SYS_UPDATE_DATE")
    LocalDateTime sysUpdateDate;
}
