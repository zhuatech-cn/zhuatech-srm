/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SupplierOnboardingGovernanceServiceTest {
    private final SupplierOnboardingGovernanceService service = new SupplierOnboardingGovernanceService();
    @Test void approvesLowRiskSupplierWithCompleteDueDiligence() {
        var result = service.assess(new SupplierOnboardingGovernanceService.Request(
                "SUP-001", 18, true, true, true, true, true));
        assertEquals("APPROVED", result.decision());
        assertEquals(82, result.trustScore());
        assertTrue(result.blockers().isEmpty());
    }
    @Test void blocksCriticalSupplierWhenControlsAreMissing() {
        var result = service.assess(new SupplierOnboardingGovernanceService.Request(
                "SUP-002", 84, true, false, false, false, false));
        assertEquals("BLOCKED", result.decision());
        assertEquals(5, result.blockers().size());
    }
}
