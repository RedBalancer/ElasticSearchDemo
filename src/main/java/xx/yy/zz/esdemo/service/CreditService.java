package xx.yy.zz.esdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xx.yy.zz.esdemo.db.SearchCreditsWithPage;
import xx.yy.zz.esdemo.db.entity.CreditEntity;
import xx.yy.zz.esdemo.db.repo.CreditRepository;
import xx.yy.zz.esdemo.model.Credit;

import java.util.Optional;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName CreditService.java
 * @Description @TODO
 * @createTime 2024年01月17日 13:57:00
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreditService {
    final CreditRepository creditRepository;

    public Page<CreditEntity> listCredits(Credit credit, Pageable pageInfo) {
        return creditRepository.findAll(SearchCreditsWithPage.getCreditSpecification(credit), pageInfo);
    }

    public CreditEntity findCreditById(Long id) {
        Optional<CreditEntity> byId = creditRepository.findById(id);
        return byId.get();
    }

    public CreditEntity findByOperateUser(String userName) {
        return creditRepository.findByOperateUser(userName);
    }
}
