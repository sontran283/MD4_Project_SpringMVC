-- #   ---------------- product  ----------------
-- #   ---------------- product  ----------------
-- #   ---------------- product  ----------------
# drop PROCEDURE PRODUCT_ADD;
DELIMITER //
CREATE PROCEDURE PRODUCT_ADD(
    IN p_category_id INT,
    IN p_img VARCHAR(255),
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT,
    IN p_status BIT
)
BEGIN
INSERT INTO product (category_id,img, name, description, price, quantity,status)
VALUES (p_category_id ,p_img, p_name, p_description, p_price, p_quantity,p_status);
END; //


-- # Cập nhật thông tin của một sản phẩm:
DELIMITER //
CREATE PROCEDURE PRODUCT_UPDATE(
    IN p_product_id INT,
    IN p_category_id INT,
    IN p_img VARCHAR(255),
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT,
    IN p_status bit
)
BEGIN


UPDATE product
SET category_id = p_category_id,
    img = p_img,
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

-- # đổi trạng thái sp:
DELIMITER //
CREATE PROCEDURE PRODUCT_UPDATE_STATUS(
    IN c_product_id INT
)
BEGIN
    UPDATE product
    SET status = not status
    WHERE product_id = c_product_id;
END; //


-- phân trang
DELIMITER //
create procedure PRODUCT_PAGINATION(IN _limit int, IN no_page int, OUT total int)
BEGIN
    declare _offset int;
    SET _offset = (no_page - 1) * _limit;
    SET  total = CEIL((SELECT count(*) FROM product) / _limit);
SELECT * FROM product LIMIT _limit OFFSET _offset;
end; //



DELIMITER //
CREATE PROCEDURE PRODUCT_ADD_PRODUCT_ID(
    IN p_category_id INT,
    IN p_img VARCHAR(255),
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT,
    IN p_status BIT,
    out _productId int
)
BEGIN
    INSERT INTO product (category_id,img, name, description, price, quantity,status)
    VALUES (p_category_id ,p_img, p_name, p_description, p_price, p_quantity,p_status);
    SELECT LAST_INSERT_ID() INTO _productId;
END; //