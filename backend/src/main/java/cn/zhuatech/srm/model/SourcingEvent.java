/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="srm_sourcing_event")
public class SourcingEvent extends BaseEntity {
    public enum Status { OPEN, EVALUATING, AWARDED }
    @Column(nullable=false,unique=true,length=32) private String eventNo; @Column(nullable=false,length=120) private String title;
    @Column(nullable=false,length=60) private String categoryName; @Column(nullable=false,length=40) private String ownerName;
    @Column(nullable=false) private LocalDateTime deadline; @Column(nullable=false,precision=14,scale=2) private BigDecimal budget;
    @Column(nullable=false) private int invitedCount; @Column(nullable=false) private int respondedCount;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status;
    protected SourcingEvent(){}
    public SourcingEvent(String no,String title,String category,String owner,LocalDateTime deadline,BigDecimal budget,int invited,int responded,Status status){eventNo=no;this.title=title;categoryName=category;ownerName=owner;this.deadline=deadline;this.budget=budget;invitedCount=invited;respondedCount=responded;this.status=status;}
    public String getEventNo(){return eventNo;} public String getTitle(){return title;} public String getCategoryName(){return categoryName;} public String getOwnerName(){return ownerName;} public LocalDateTime getDeadline(){return deadline;} public BigDecimal getBudget(){return budget;} public int getInvitedCount(){return invitedCount;} public int getRespondedCount(){return respondedCount;} public Status getStatus(){return status;}
    public void markResponded(){respondedCount=Math.min(invitedCount,respondedCount+1);}
}
