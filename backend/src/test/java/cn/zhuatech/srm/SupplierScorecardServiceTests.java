/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm;

import cn.zhuatech.srm.service.SupplierScorecardService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SupplierScorecardServiceTests {
    private final SupplierScorecardService service = new SupplierScorecardService();

    @Test void identifiesStrategicSupplier() {
        var result = service.evaluate(new SupplierScorecardService.Request("SUP-001", 94, 92, 84, 90, 18));
        assertThat(result.tier()).isEqualTo("STRATEGIC");
        assertThat(result.totalScore()).isGreaterThanOrEqualTo(85);
    }

    @Test void flagsHighRiskSupplierForExitReview() {
        var result = service.evaluate(new SupplierScorecardService.Request("SUP-009", 52, 48, 60, 50, 82));
        assertThat(result.tier()).isEqualTo("EXIT_REVIEW");
        assertThat(result.improvementActions()).anyMatch(action -> action.contains("备选供应源"));
    }
}
