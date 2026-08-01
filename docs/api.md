# REST API 摘要

Copyright © 2026 上海如静知华信息科技有限公司。

统一响应结构：`{ "success": true, "message": "操作成功", "data": ... }`。除登录外，接口使用 `Authorization: Bearer <token>`。

| 方法 | 路径 | 角色 | 说明 |
| --- | --- | --- | --- |
| POST | `/api/auth/login` | 公开 | 登录并签发 JWT |
| GET | `/api/auth/me` | 已登录 | 当前账号与角色 |
| GET | `/api/portal/dashboard` | SUPPLIER | 供应商工作台 |
| GET | `/api/portal/rfqs` | SUPPLIER | 可参与的寻源项目 |
| POST | `/api/portal/rfqs/{id}/quotes` | SUPPLIER | 提交正式报价 |
| GET | `/api/portal/orders` | SUPPLIER | 本企业采购订单 |
| POST | `/api/portal/orders/{id}/acknowledge` | SUPPLIER | 确认订单 |
| GET | `/api/admin/dashboard` | ADMIN/BUYER/AUDITOR | 采购驾驶舱 |
| GET | `/api/admin/suppliers` | ADMIN/BUYER/AUDITOR | 供应商列表 |
| GET | `/api/admin/sourcing` | ADMIN/BUYER/AUDITOR | 寻源列表 |

提交报价示例：

```json
{
  "amount": 658000.00,
  "leadDays": 18,
  "validUntil": "2026-09-30",
  "remark": "含税、含包装与送货费用"
}
```

## 供应商风险

`POST /api/admin/supplier-risk`：返回供应商风险分、等级、采购复核标志和建议动作。
