/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
class SourcingAwardGovernanceServiceTest {
    private final SourcingAwardGovernanceService service = new SourcingAwardGovernanceService();

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    @Test
    void awardsCompliantCompetitiveEvent() {
        var result = service.assess(request(3, "800000", "1000000", "30", true));
        assertThat(result.decision()).isEqualTo(SourcingAwardGovernanceService.Decision.AWARD);
    }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    @Test
    void blocksSingleBidAndOverBudgetAward() {
        var result = service.assess(request(1, "1200000", "1000000", "30", true));
        assertThat(result.decision()).isEqualTo(SourcingAwardGovernanceService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(2);
    }

    /** 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    @Test
    void reviewsHighConcentrationWithoutContingency() {
        var result = service.assess(request(3, "800000", "1000000", "80", false));
        assertThat(result.decision()).isEqualTo(SourcingAwardGovernanceService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(3);
    }

    private SourcingAwardGovernanceService.Request request(int bids, String award, String budget,
                                                            String concentration, boolean prepared) {
        return new SourcingAwardGovernanceService.Request("RFQ-100", "SUP-100", bids, false,
                true, true, true, false, false, new BigDecimal(award), new BigDecimal(budget),
                false, new BigDecimal(concentration), new BigDecimal("60"), prepared, prepared, prepared);
    }
}
