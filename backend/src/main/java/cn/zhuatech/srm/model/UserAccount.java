/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm.model;
import jakarta.persistence.*;
@Entity @Table(name="srm_user")
public class UserAccount extends BaseEntity {
    public enum Role { ADMIN, BUYER, SUPPLIER, AUDITOR }
    @Column(nullable=false,unique=true,length=32) private String username; @Column(nullable=false) private String password;
    @Column(nullable=false,length=50) private String fullName; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="supplier_id") private Supplier supplier; @Column(nullable=false) private boolean enabled=true;
    protected UserAccount(){}
    public UserAccount(String username,String password,String fullName,Role role,Supplier supplier){this.username=username;this.password=password;this.fullName=fullName;this.role=role;this.supplier=supplier;}
    public String getUsername(){return username;} public String getPassword(){return password;} public String getFullName(){return fullName;} public Role getRole(){return role;} public Supplier getSupplier(){return supplier;} public boolean isEnabled(){return enabled;}
}
