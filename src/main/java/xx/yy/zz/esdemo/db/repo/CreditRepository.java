package xx.yy.zz.esdemo.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xx.yy.zz.esdemo.db.entity.CreditEntity;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName CreditRepository.java
 * @Description @TODO
 * @createTime 2024年01月17日 12:44:00
 */
public interface CreditRepository extends JpaRepository<CreditEntity, Long>, JpaSpecificationExecutor<CreditEntity>{

    public CreditEntity findByOperateUser(String userName);

}
