package xx.yy.zz.esdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import xx.yy.zz.esdemo.exception.BizException;
import xx.yy.zz.esdemo.model.Credit;
import xx.yy.zz.esdemo.service.CreditService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditActionControllerTest {
    @InjectMocks
    CreditActionController controller;

    @Mock
    CreditService creditService;

    @Mock
    HttpServletRequest request;

    Credit credit;

    @BeforeEach
    void init() {
        credit = new Credit();
        credit.setCreditDesc( "Some desc" );
        credit.setMaxAmount(100.0);
        credit.setMinAmount(1.0);
        credit.setCreateStartDate(LocalDateTime.now().minusDays(1));
        credit.setCreateEndDate((LocalDateTime.now()));
    }

    @Test
    void testException() {
        assertThrows(BizException.class, ()->{
            controller.getCredits(null, request);
        });
    }

    @Test
    void testPage() {
        when(request.getParameter("page")).thenReturn("1");
        controller.getCredits(credit,request);
        verify(creditService, times(1)).listCredits(any(Credit.class), any(Pageable.class));
    }
}