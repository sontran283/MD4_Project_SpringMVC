-- #   ---------------- category  ----------------
-- #   ---------------- category  ----------------
-- #   ---------------- category  ----------------
DELIMITER //
CREATE PROCEDURE CATEGORY_ADD(
    IN c_name VARCHAR(255),
    IN c_description VARCHAR(255),
    IN c_status BOOLEAN
)
BEGIN
INSERT INTO category (name, description, status)
VALUES (c_name, c_description, c_status);
END; //


-- # Cập nhật thông tin của một danh mục:
DELIMITER //
CREATE PROCEDURE CATEGORY_UPDATE(
    IN c_category_id INT,
    IN c_name VARCHAR(255),
    IN c_description VARCHAR(255),
    IN c_status BOOLEAN
)
BEGIN
UPDATE category
SET name = c_name, description = c_description, status = c_status
WHERE category_id = c_category_id;
END; //


-- # xoá danh mục
DELIMITER //
CREATE PROCEDURE CATEGORY_DELETE(
    IN c_category_id INT
)
BEGIN
DELETE FROM category WHERE category_id = c_category_id;
END; //


-- # đổi trạng thái danh mục:
DELIMITER //
CREATE PROCEDURE CATEGORY_UPDATE_STATUS(
    IN c_category_id INT
)
BEGIN
UPDATE category
SET status = not status
WHERE category_id = c_category_id;
END; //


-- # findall
DELIMITER //
CREATE PROCEDURE CATEGORY_FIND_ALL(
)
BEGIN
SELECT* FROM category;
END; //


-- #findByID
DELIMITER //
CREATE PROCEDURE CATEGORY_FIND_BY_ID(IN c_category_id int)
BEGIN
SELECT* FROM category where category_id = c_category_id;
END; //


DELIMITER //
CREATE PROCEDURE CATEGORY_SEARCH_BY_NAME(IN c_name varchar(255))
BEGIN
SELECT * FROM category where LCASE(name) LIKE CONCAT('%' , c_name, '%');
END; //


DELIMITER //
CREATE PROCEDURE CATEGORY_SORT_BY_NAME()
BEGIN
SELECT *FROM category ORDER BY name;
END; //


delimiter //
create procedure CATEGORY_CHANGE_STATUS(in _id int)
BEGIN
    update category set status=status^1 where category_id=_id;
END; //