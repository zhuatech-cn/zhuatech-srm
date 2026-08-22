/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="srm_quote")
public class SupplierQuote extends BaseEntity {
    public enum Status { SUBMITTED, SHORTLISTED, REJECTED }
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="event_id") private SourcingEvent event;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="supplier_id") private Supplier supplier;
    @Column(nullable=false,precision=14,scale=2) private BigDecimal amount; @Column(nullable=false) private int leadDays;
    @Column(nullable=false) private LocalDate validUntil; @Column(length=300) private String remark;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status;
    protected SupplierQuote(){}
    public SupplierQuote(SourcingEvent event,Supplier supplier,BigDecimal amount,int leadDays,LocalDate validUntil,String remark){this.event=event;this.supplier=supplier;this.amount=amount;this.leadDays=leadDays;this.validUntil=validUntil;this.remark=remark;status=Status.SUBMITTED;}
    public SourcingEvent getEvent(){return event;} public Supplier getSupplier(){return supplier;} public BigDecimal getAmount(){return amount;} public int getLeadDays(){return leadDays;} public LocalDate getValidUntil(){return validUntil;} public String getRemark(){return remark;} public Status getStatus(){return status;}
}
