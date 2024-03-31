package xx.yy.zz.esdemo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import xx.yy.zz.esdemo.db.entity.CreditEntity;
import xx.yy.zz.esdemo.db.repo.CreditRepository;
import xx.yy.zz.esdemo.model.Credit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {

    @InjectMocks
    CreditService creditService;

    @Mock
    CreditRepository repository;

    @Test
    void testListCredits() {
//        Credit credit = new Credit();
//        credit.setCreditDesc("Credit with 10 USD");
//        credit.setCreateEndDate(LocalDateTime.now().minusDays(10));
//        credit.setCreateEndDate(LocalDateTime.now());
//        credit.setMinAmount(Double.valueOf(0));
//        credit.setMaxAmount(Double.valueOf(100));
        creditService.listCredits((Credit) any(), (Pageable) any());
        verify(repository,times(1)).findAll((Specification<CreditEntity>)any(), (Pageable) any());
    }
}