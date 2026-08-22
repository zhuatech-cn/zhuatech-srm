/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal; import java.time.LocalDate; import java.time.LocalDateTime; import java.util.List;
public final class SrmDto {
    private SrmDto(){}
    public record Metric(String label,String value,String helper,String tone){}
    public record Activity(String time,String title,String detail,String type){}
    public record Dashboard(List<Metric> metrics,List<Activity> activities,Object data){}
    public record SupplierView(Long id,String code,String name,String category,String contact,String phone,String status,int qualificationProgress,BigDecimal rating,BigDecimal onTimeRate,String riskLevel){}
    public record SourcingView(Long id,String eventNo,String title,String category,String owner,LocalDateTime deadline,BigDecimal budget,int invited,int responded,String status){}
    public record QuoteRequest(@NotNull @DecimalMin("0.01") BigDecimal amount,@Min(1) int leadDays,@NotNull LocalDate validUntil,@Size(max=300) String remark){}
    public record OrderView(Long id,String orderNo,String supplierName,String itemSummary,BigDecimal amount,LocalDate deliveryDate,int deliveredPercent,String status){}
    public record QualityView(Long id,String issueNo,String supplierName,String title,String severity,LocalDate dueDate,String owner,String status){}
}
