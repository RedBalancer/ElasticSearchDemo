package xx.yy.zz.esdemo.db;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import xx.yy.zz.esdemo.db.entity.CreditEntity;
import xx.yy.zz.esdemo.model.Credit;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName SearchCreditsWithPage.java
 * @Description @TODO
 * @createTime 2024年01月17日 11:30:00
 */
public class SearchCreditsWithPage {
    public static Specification<CreditEntity> getCreditSpecification(Credit credit) {
        Specification<CreditEntity> spec = (root, query, criteriaBuilder) -> {
            var predArray = new ArrayList<Predicate>();
            if(!StringUtils.isBlank(credit.getCreditDesc())) {
                Path<String> creditDescription = root.get("creditDesc");
                Predicate likePred = criteriaBuilder.like(creditDescription, credit.getCreditDesc());
                predArray.add(likePred);
            }
            if(!ObjectUtils.isEmpty(credit.getMinAmount())
            && !ObjectUtils.isEmpty(credit.getMaxAmount())) {
                Path<Double> amount = root.get("amount");
                Predicate betweenPred = criteriaBuilder.between(amount, credit.getMinAmount(), credit.getMaxAmount());
                predArray.add(betweenPred);
            }
            if(!ObjectUtils.isEmpty(credit.getCreateStartDate())
                    && !ObjectUtils.isEmpty(credit.getCreateEndDate())) {
                Path<LocalDateTime> creationDate = root.get("sysCreationDate");
                Predicate betweenPred = criteriaBuilder.between(creationDate, credit.getCreateStartDate(), credit.getCreateEndDate());
                predArray.add(betweenPred);
            }
            return criteriaBuilder.and(predArray.toArray(new Predicate[predArray.size()]));
        };
        return spec;
    }
}
