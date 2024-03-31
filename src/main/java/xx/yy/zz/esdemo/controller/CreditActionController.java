package xx.yy.zz.esdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xx.yy.zz.esdemo.db.entity.CreditEntity;
import xx.yy.zz.esdemo.exception.BizException;
import xx.yy.zz.esdemo.exception.CommonException;
import xx.yy.zz.esdemo.model.Credit;
import xx.yy.zz.esdemo.service.CreditService;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName CreditActionController.java
 * @Description @TODO
 * @createTime 2024年01月17日 16:30:00
 */

@RestController
@Validated
@RequestMapping("/v1/kibana")
@RequiredArgsConstructor

public class CreditActionController {

    private final CreditService creditService;

    @GetMapping(value="/credits", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CreditEntity> getCredits(Credit credit, HttpServletRequest request) {
        try {
            validInput(credit, request);

            String page = request.getParameter("page");
            int pageNum = 0;
            if (!StringUtils.isBlank(page)) {
                pageNum = Integer.valueOf(page).intValue();
            }
            // PropertyPath 356
            PageRequest pageRequest = PageRequest.of(pageNum, 10, Sort.by("\\QsysCreationDate\\E"));
            return creditService.listCredits(credit, pageRequest);
        } catch (Exception e) {
            if( e instanceof CommonException e1) {
                throw e1;
            } else {
                throw new BizException(500, e.getMessage());
            }
        }
    }

    private void validInput(Credit credit, HttpServletRequest request) throws Exception {
        Double maxAmount = Double.valueOf(0);
        Double minAmount = Double.valueOf(0);
        if(!ObjectUtils.isEmpty(credit.getMaxAmount())) {
            maxAmount = credit.getMaxAmount();
        }
        if(!ObjectUtils.isEmpty(credit.getMinAmount())) {
            minAmount = credit.getMinAmount();
        }
        if( maxAmount.equals(minAmount)) {
            throw new BizException(400, "Not valid input amount");
        }
    }

    @GetMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditEntity getByUser(String userName) {
        return creditService.findByOperateUser(userName);
    }
}
