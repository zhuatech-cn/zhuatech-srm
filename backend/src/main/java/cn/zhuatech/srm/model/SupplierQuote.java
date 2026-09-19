/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="srm_quote")
public class SupplierQuote extends BaseEntity {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Status { SUBMITTED, SHORTLISTED, REJECTED }
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="event_id") private SourcingEvent event;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="supplier_id") private Supplier supplier;
    @Column(nullable=false,precision=14,scale=2) private BigDecimal amount; @Column(nullable=false) private int leadDays;
    @Column(nullable=false) private LocalDate validUntil; @Column(length=300) private String remark;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected SupplierQuote(){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public SupplierQuote(SourcingEvent event,Supplier supplier,BigDecimal amount,int leadDays,LocalDate validUntil,String remark){this.event=event;this.supplier=supplier;this.amount=amount;this.leadDays=leadDays;this.validUntil=validUntil;this.remark=remark;status=Status.SUBMITTED;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public SourcingEvent getEvent(){return event;} /**
                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                    */
public Supplier getSupplier(){return supplier;} /**
                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                    */
public BigDecimal getAmount(){return amount;} /**
                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                  */
public int getLeadDays(){return leadDays;} /**
                                                                                                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                             */
public LocalDate getValidUntil(){return validUntil;} /**
                                                                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                  */
public String getRemark(){return remark;} /**
                                                                                                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                            */
public Status getStatus(){return status;}
}
