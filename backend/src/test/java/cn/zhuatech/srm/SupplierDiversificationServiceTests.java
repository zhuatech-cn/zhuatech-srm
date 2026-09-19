/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm;

import cn.zhuatech.srm.service.SupplierDiversificationService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class SupplierDiversificationServiceTests {
    private final SupplierDiversificationService service = new SupplierDiversificationService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void recommendsDiversificationForSingleSourceDependency() {
        var result = service.assess(new SupplierDiversificationService.Request("MAT-001", 10000, List.of(
            new SupplierDiversificationService.SupplierExposure("供应商甲", .70, 30, 25),
            new SupplierDiversificationService.SupplierExposure("供应商乙", .30, 20, 20))));

        assertEquals("DIVERSIFY", result.decision());
        assertEquals(.58, result.concentrationIndex());
        assertEquals("供应商甲", result.largestSupplier());
        assertTrue(result.actions().getFirst().contains("45%"));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void keepsBalancedMultiSourcePortfolio() {
        var result = service.assess(new SupplierDiversificationService.Request("MAT-002", 8000, List.of(
            new SupplierDiversificationService.SupplierExposure("供应商甲", .34, 20, 20),
            new SupplierDiversificationService.SupplierExposure("供应商乙", .33, 25, 25),
            new SupplierDiversificationService.SupplierExposure("供应商丙", .33, 15, 18))));

        assertEquals("BALANCED", result.decision());
    }
}
