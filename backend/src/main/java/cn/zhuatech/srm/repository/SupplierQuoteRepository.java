/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.srm.repository; import cn.zhuatech.srm.model.SupplierQuote; import org.springframework.data.jpa.repository.JpaRepository;
public interface SupplierQuoteRepository extends JpaRepository<SupplierQuote,Long>{long countBySupplierId(Long supplierId);}
