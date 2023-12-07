-- #   ---------------- cart  ----------------
-- #   ---------------- cart  ----------------
-- #   ---------------- cart  ----------------
-- Thêm giỏ hàng
DELIMITER //
CREATE PROCEDURE CART_ADD(
    IN ca_customer_id INT
)
BEGIN
INSERT INTO cart (customer_id) VALUES (ca_customer_id);
END; //


-- Sửa thông tin giỏ hàng theo ID
DELIMITER //
CREATE PROCEDURE CART_UPDATE(
    IN ca_cart_id INT,
    IN ca_customer_id INT
)
BEGIN
UPDATE cart
SET customer_id = ca_customer_id
WHERE cart_id = ca_cart_id;
END; //


-- Xoá giỏ hàng theo ID
DELIMITER //
CREATE PROCEDURE CART_DELETE(
    IN ca_cart_id INT
)
BEGIN
DELETE FROM cart WHERE cart_id = ca_cart_id;
END; //


-- Lấy tất cả giỏ hàng
DELIMITER //
CREATE PROCEDURE CART_FY_BY_ALL()
BEGIN
SELECT * FROM cart;
END; //


-- Lấy giỏ hàng theo ID
DELIMITER //
CREATE PROCEDURE CART_FY_BY_ID(
    IN ca_cart_id INT
)
BEGIN
SELECT * FROM cart WHERE cart_id = ca_cart_id;
END; //


DELIMITER //
CREATE PROCEDURE CART_SORT_BY_CUSTOMER()
BEGIN
SELECT * FROM cart
ORDER BY customer_id;
END //
DELIMITER ;