/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="srm_quality_issue")
public class QualityIssue extends BaseEntity {
    public enum Status { OPEN, ANALYZING, VERIFYING, CLOSED }
    @Column(nullable=false,unique=true,length=32) private String issueNo; @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="supplier_id") private Supplier supplier;
    @Column(nullable=false,length=150) private String title; @Column(nullable=false,length=20) private String severity;
    @Column(nullable=false) private LocalDate dueDate; @Column(nullable=false,length=40) private String ownerName;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=24) private Status status;
    protected QualityIssue(){}
    public QualityIssue(String no,Supplier supplier,String title,String severity,LocalDate dueDate,String owner,Status status){issueNo=no;this.supplier=supplier;this.title=title;this.severity=severity;this.dueDate=dueDate;ownerName=owner;this.status=status;}
    public String getIssueNo(){return issueNo;} public Supplier getSupplier(){return supplier;} public String getTitle(){return title;} public String getSeverity(){return severity;} public LocalDate getDueDate(){return dueDate;} public String getOwnerName(){return ownerName;} public Status getStatus(){return status;}
}
