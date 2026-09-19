/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.time.LocalDate;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="srm_quality_issue")
public class QualityIssue extends BaseEntity {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Status { OPEN, ANALYZING, VERIFYING, CLOSED }
    @Column(nullable=false,unique=true,length=32) private String issueNo; @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="supplier_id") private Supplier supplier;
    @Column(nullable=false,length=150) private String title; @Column(nullable=false,length=20) private String severity;
    @Column(nullable=false) private LocalDate dueDate; @Column(nullable=false,length=40) private String ownerName;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=24) private Status status;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected QualityIssue(){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public QualityIssue(String no,Supplier supplier,String title,String severity,LocalDate dueDate,String owner,Status status){issueNo=no;this.supplier=supplier;this.title=title;this.severity=severity;this.dueDate=dueDate;ownerName=owner;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getIssueNo(){return issueNo;} /**
                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                 */
public Supplier getSupplier(){return supplier;} /**
                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                 */
public String getTitle(){return title;} /**
                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                         */
public String getSeverity(){return severity;} /**
                                                                                                                                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                       */
public LocalDate getDueDate(){return dueDate;} /**
                                                                                                                                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                      */
public String getOwnerName(){return ownerName;} /**
                                                                                                                                                                                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                      */
public Status getStatus(){return status;}
}
