/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse;import cn.zhuatech.srm.service.SupplierPerformanceCorrectiveActionService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/srm")public class SupplierPerformanceCorrectiveActionController{
 private final SupplierPerformanceCorrectiveActionService service;public SupplierPerformanceCorrectiveActionController(SupplierPerformanceCorrectiveActionService service){this.service=service;}
 @PostMapping("/supplier-performance-corrective-action")public ApiResponse<SupplierPerformanceCorrectiveActionService.Assessment> assess(@Valid @RequestBody SupplierPerformanceCorrectiveActionService.Request request){return ApiResponse.ok("供应商绩效整改评估完成",service.assess(request));}
}
