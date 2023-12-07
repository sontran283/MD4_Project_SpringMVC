-- #   ---------------- orders  ----------------
-- #   ---------------- orders  ----------------
-- #   ---------------- orders  ----------------
DELIMITER //
CREATE PROCEDURE ORDER_ADD(
    IN or_customer_id INT,
    IN or_total DOUBLE
)
BEGIN
INSERT INTO orders (customer_id, total)
VALUES (or_customer_id, or_total);
END //
DELIMITER ;


-- #update trạng thái
DELIMITER //
CREATE PROCEDURE ORDER_CHANGE_STATUS(
    IN or_id INT,
    IN or_status BIT
)
BEGIN
UPDATE orders
SET
    status = or_status
WHERE order_id = or_id;
END //
DELIMITER ;


-- Sửa thông tin đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_UPDATE(
    IN or_order_id INT,
    IN or_customer_id INT,
    IN or_total DOUBLE,
    IN or_status BIT
)
BEGIN
UPDATE orders
SET customer_id = or_customer_id, total = or_total, status = or_status
WHERE order_id = or_order_id;
END //
DELIMITER ;


-- Xoá đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_DELETE(
    IN or_order_id INT
)
BEGIN
DELETE FROM orders WHERE order_id = or_order_id;
END //
DELIMITER ;



-- Lấy tất cả đơn hàng
DELIMITER //
CREATE PROCEDURE ORDER_FY_BY_ALL()
BEGIN
SELECT * FROM orders;
END //
DELIMITER ;


-- Lấy đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_FY_BY_ID(
    IN or_id INT
)
BEGIN
SELECT * FROM orders WHERE order_id = or_id;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE ORDERS_SORT_BY_DATE()
BEGIN
SELECT * FROM orders
ORDER BY order_date;
END //
DELIMITER ;