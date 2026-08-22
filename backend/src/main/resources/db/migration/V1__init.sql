-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/
CREATE TABLE srm_supplier (
  id BIGINT PRIMARY KEY AUTO_INCREMENT, supplier_code VARCHAR(24) NOT NULL UNIQUE, supplier_name VARCHAR(100) NOT NULL,
  category_name VARCHAR(60) NOT NULL, contact_name VARCHAR(40), contact_phone VARCHAR(30), status VARCHAR(20) NOT NULL,
  qualification_progress INT NOT NULL, rating DECIMAL(4,1) NOT NULL, on_time_rate DECIMAL(5,2) NOT NULL, risk_level VARCHAR(20) NOT NULL,
  created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL
);
CREATE TABLE srm_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(32) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL,
  full_name VARCHAR(50) NOT NULL, role VARCHAR(20) NOT NULL, supplier_id BIGINT NULL, enabled BOOLEAN NOT NULL DEFAULT TRUE,
  created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL,
  CONSTRAINT fk_user_supplier FOREIGN KEY (supplier_id) REFERENCES srm_supplier(id)
);
CREATE TABLE srm_sourcing_event (
  id BIGINT PRIMARY KEY AUTO_INCREMENT, event_no VARCHAR(32) NOT NULL UNIQUE, title VARCHAR(120) NOT NULL,
  category_name VARCHAR(60) NOT NULL, owner_name VARCHAR(40) NOT NULL, deadline DATETIME(6) NOT NULL,
  budget DECIMAL(14,2) NOT NULL, invited_count INT NOT NULL, responded_count INT NOT NULL, status VARCHAR(20) NOT NULL,
  created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL
);
CREATE TABLE srm_quote (
  id BIGINT PRIMARY KEY AUTO_INCREMENT, event_id BIGINT NOT NULL, supplier_id BIGINT NOT NULL,
  amount DECIMAL(14,2) NOT NULL, lead_days INT NOT NULL, valid_until DATE NOT NULL, remark VARCHAR(300), status VARCHAR(20) NOT NULL,
  created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL,
  CONSTRAINT fk_quote_event FOREIGN KEY (event_id) REFERENCES srm_sourcing_event(id),
  CONSTRAINT fk_quote_supplier FOREIGN KEY (supplier_id) REFERENCES srm_supplier(id)
);
CREATE TABLE srm_purchase_order (
  id BIGINT PRIMARY KEY AUTO_INCREMENT, order_no VARCHAR(32) NOT NULL UNIQUE, supplier_id BIGINT NOT NULL,
  item_summary VARCHAR(150) NOT NULL, amount DECIMAL(14,2) NOT NULL, delivery_date DATE NOT NULL,
  delivered_percent INT NOT NULL, status VARCHAR(24) NOT NULL, created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL,
  CONSTRAINT fk_order_supplier FOREIGN KEY (supplier_id) REFERENCES srm_supplier(id)
);
CREATE TABLE srm_quality_issue (
  id BIGINT PRIMARY KEY AUTO_INCREMENT, issue_no VARCHAR(32) NOT NULL UNIQUE, supplier_id BIGINT NOT NULL,
  title VARCHAR(150) NOT NULL, severity VARCHAR(20) NOT NULL, due_date DATE NOT NULL, owner_name VARCHAR(40) NOT NULL,
  status VARCHAR(24) NOT NULL, created_at DATETIME(6) NOT NULL, updated_at DATETIME(6) NOT NULL,
  CONSTRAINT fk_quality_supplier FOREIGN KEY (supplier_id) REFERENCES srm_supplier(id)
);
