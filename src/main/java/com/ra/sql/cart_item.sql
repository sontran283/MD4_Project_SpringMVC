-- #   ---------------- cart_item  ----------------
-- #   ---------------- cart_item  ----------------
-- #   ---------------- cart_item  ----------------
-- Thêm mục giỏ hàng
DELIMITER //
CREATE PROCEDURE CART_ITEM_ADD(
    IN cit_cart_id INT,
    IN cit_product_id INT,
    IN cit_price DECIMAL(10, 2),
    IN cit_quantity INT
)
BEGIN
INSERT INTO cart_item (cart_id, product_id, price, quantity)
VALUES (cit_cart_id, cit_product_id, cit_price, cit_quantity);
END; //


-- Sửa thông tin mục giỏ hàng theo ID
DELIMITER //
CREATE PROCEDURE CART_ITEM_UPDATE(
    IN cit_cartitem_id INT,
    IN cit_product_id INT,
    IN cit_price DECIMAL(10, 2),
    IN cit_quantity INT
)
BEGIN
UPDATE cart_item
SET  product_id = cit_product_id, price = cit_price, quantity = cit_quantity
WHERE cartitem_id = cit_cartitem_id;
END; //


-- Xoá mục giỏ hàng theo ID
DELIMITER //
CREATE PROCEDURE CART_ITEM_DELETE(
    IN cit_cartitem_id INT
)
BEGIN
DELETE FROM cart_item WHERE cartitem_id = cit_cartitem_id;
END; //


-- Lấy tất cả mục giỏ hàng
DELIMITER //
CREATE PROCEDURE CART_ITEM_FY_BY_ALL()
BEGIN
SELECT * FROM cart_item;
END; //


-- Lấy mục giỏ hàng theo ID
DELIMITER //
CREATE PROCEDURE CART_ITEM_FY_BY_ID(
    IN cit_cartitem_id INT
)
BEGIN
SELECT * FROM cart_item WHERE cartitem_id = cit_cartitem_id;
END; //


DELIMITER //
CREATE PROCEDURE CART_ITEM_SORT_BY_QUANTITY()
BEGIN
SELECT * FROM cart_item
ORDER BY quantity;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE CART_ITEM_SORT_BY_PRICE()
BEGIN
SELECT * FROM cart_item
ORDER BY price;
END //
DELIMITER ;

