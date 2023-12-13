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
# drop PROCEDURE IMAGE_PRODUCT_FIND_BY_ID

DELIMITER //
CREATE PROCEDURE IMAGE_PRODUCT_FIND_BY_ID(
    IN i_id INT
)
BEGIN
    SELECT *FROM image WHERE product_id = i_id;
END; //