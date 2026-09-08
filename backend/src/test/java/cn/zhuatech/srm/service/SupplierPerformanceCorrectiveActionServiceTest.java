/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.service;
import org.junit.jupiter.api.Test;import static org.assertj.core.api.Assertions.assertThat;
class SupplierPerformanceCorrectiveActionServiceTest{
 private final SupplierPerformanceCorrectiveActionService service=new SupplierPerformanceCorrectiveActionService();
 @Test void acceptsHealthySupplier(){var a=service.assess(new SupplierPerformanceCorrectiveActionService.Request("SC-1","buyer","director",92,90,85,88,true,true,false,true,true,true,true,true,true));assertThat(a.decision()).isEqualTo(SupplierPerformanceCorrectiveActionService.Decision.ACCEPT);assertThat(a.weightedScore()).isGreaterThan(80);}
 @Test void reviewsUnacknowledgedCorrectiveAction(){var a=service.assess(new SupplierPerformanceCorrectiveActionService.Request("SC-2","buyer","director",65,60,70,70,true,true,false,true,true,true,false,false,false));assertThat(a.decision()).isEqualTo(SupplierPerformanceCorrectiveActionService.Decision.REVIEW);assertThat(a.actions()).hasSize(3);}
 @Test void blocksCriticalBreachWithoutOwners(){var a=service.assess(new SupplierPerformanceCorrectiveActionService.Request("SC-3","same","same",40,30,50,50,true,true,true,false,false,false,true,true,true));assertThat(a.decision()).isEqualTo(SupplierPerformanceCorrectiveActionService.Decision.BLOCKED);assertThat(a.riskLevel()).isEqualTo(SupplierPerformanceCorrectiveActionService.RiskLevel.CRITICAL);assertThat(a.blockers()).hasSizeGreaterThanOrEqualTo(4);}
}
