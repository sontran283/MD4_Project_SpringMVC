-- #   ---------------- image  ----------------
-- #   ---------------- image  ----------------
-- #   ---------------- image  ----------------
use project_module4_webfruit;
DELIMITER //
CREATE PROCEDURE IMAGE_ADD(
    IN i_url VARCHAR(255),
    IN i_product_id INT
)
BEGIN
INSERT INTO image (url, product_id)
VALUES (i_url, i_product_id);
END; //


-- # Cập nhật thông tin của một hình ảnh:
DELIMITER //
CREATE PROCEDURE IMAGE_UPDATE(
    IN i_id INT,
    IN i_url VARCHAR(255),
    IN i_product_id INT
)
BEGIN
UPDATE image
SET url = i_url,
    product_id = i_product_id
WHERE id = i_id;
END; //


-- # Xóa một hình ảnh:
DELIMITER //
CREATE PROCEDURE IMAGE_DELETE(
    IN i_id INT
)
BEGIN
DELETE FROM image WHERE id = i_id;
END; //


-- #find_all
DELIMITER //
CREATE PROCEDURE IMAGE_FIND_ALL(
)
BEGIN
SELECT * FROM image;
END; //


-- #find_by_id
DELIMITER //
CREATE PROCEDURE IMAGE_FIND_BY_ID(
    IN i_id INT
)
BEGIN
SELECT *FROM image WHERE id = i_id;
END; //

--      DELIMITER //
-- CREATE PROCEDURE PROC_CREATE_PRODUCT(
--     IN p_category_id INT,
--     IN p_name VARCHAR(255),
--     IN p_description VARCHAR(255),
--     IN p_price DOUBLE,
--     in _url_image varchar(255),
--     IN p_stock INT,
--     OUT _productId INT
-- )
-- BEGIN
-- INSERT INTO product (category_id, name, description, price,url_image, stock)
-- VALUES (p_category_id, p_name, p_description, p_price,_url_image, p_stock);
--
-- -- Lấy ID của sản phẩm vừa được thêm
-- SELECT LAST_INSERT_ID() INTO _productId;
-- END //
-- DELIMITER ;