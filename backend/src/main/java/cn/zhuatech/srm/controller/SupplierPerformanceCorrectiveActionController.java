/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.controller;
import cn.zhuatech.srm.common.ApiResponse;import cn.zhuatech.srm.service.SupplierPerformanceCorrectiveActionService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/srm")public class SupplierPerformanceCorrectiveActionController{
 private final SupplierPerformanceCorrectiveActionService service;/**
                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                   */
public SupplierPerformanceCorrectiveActionController(SupplierPerformanceCorrectiveActionService service){this.service=service;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/supplier-performance-corrective-action")public ApiResponse<SupplierPerformanceCorrectiveActionService.Assessment> assess(@Valid @RequestBody SupplierPerformanceCorrectiveActionService.Request request){return ApiResponse.ok("供应商绩效整改评估完成",service.assess(request));}
}
