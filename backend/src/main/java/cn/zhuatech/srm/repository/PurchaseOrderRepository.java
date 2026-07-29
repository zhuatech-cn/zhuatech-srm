/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.srm.repository; import cn.zhuatech.srm.model.PurchaseOrder; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long>{List<PurchaseOrder> findBySupplierIdOrderByDeliveryDate(Long supplierId);}
