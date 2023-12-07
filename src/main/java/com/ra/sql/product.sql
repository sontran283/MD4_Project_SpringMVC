-- #   ---------------- product  ----------------
-- #   ---------------- product  ----------------
-- #   ---------------- product  ----------------
DELIMITER //
CREATE PROCEDURE PRODUCT_ADD(
    IN p_category_id INT,
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT
)
BEGIN
INSERT INTO product (category_id, name, description, price, quantity)
VALUES (p_category_id , p_name, p_description, p_price, p_quantity);
END; //


-- # Cập nhật thông tin của một sản phẩm:
DELIMITER //
CREATE PROCEDURE PRODUCT_UPDATE(
    IN p_product_id INT,
    IN p_category_id INT,
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT,
    IN p_status bit
)
BEGIN
UPDATE product
SET category_id = p_category_id,
    name = p_name,
    description = p_description,
    price = p_price,
    quantity = p_quantity,
    status = p_status
WHERE product_id = p_product_id;
END; //


-- # Xóa một sản phẩm:
DELIMITER //
CREATE PROCEDURE PRODUCT_DELETE(
    IN p_product_id INT
)
BEGIN
DELETE FROM product WHERE product_id = p_product_id;
END; //


-- #findall
DELIMITER //
CREATE PROCEDURE PRODUCT_FIND_ALL(
)
BEGIN
SELECT *FROM product;
END; //


-- # findbyid
DELIMITER //
CREATE PROCEDURE PRODUCT_FIND_BY_ID(
    IN p_product_id INT
)
BEGIN
SELECT *FROM product WHERE product_id = p_product_id;
END; //


DELIMITER //
CREATE PROCEDURE PRODUCT_SEARCH_BY_NAME(IN search_name_param VARCHAR(255))
BEGIN
SELECT * FROM product
WHERE LCASE(name) LIKE CONCAT('%', search_name_param, '%');
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE PRODUCT_SORT_BY_NAME()
BEGIN
SELECT * FROM product
ORDER BY name;
END //
DELIMITER ;