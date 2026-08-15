/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.srm;

import cn.zhuatech.srm.service.SupplierContinuityService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SupplierContinuityServiceTests {
    private final SupplierContinuityService service = new SupplierContinuityService();

    @Test void identifiesCriticalSingleSourceExposure() {
        var result = service.assess(new SupplierContinuityService.Request("SUP-100", 45, 18, 0, 95, 60, 80));
        assertThat(result.riskLevel()).isEqualTo("CRITICAL");
        assertThat(result.recommendedBufferDays()).isEqualTo(34);
        assertThat(result.actions()).hasSizeGreaterThanOrEqualTo(3);
    }

    @Test void recognizesResilientSupply() {
        var result = service.assess(new SupplierContinuityService.Request("SUP-200", 15, 30, 3, 20, 10, 10));
        assertThat(result.riskLevel()).isEqualTo("RESILIENT");
    }
}
