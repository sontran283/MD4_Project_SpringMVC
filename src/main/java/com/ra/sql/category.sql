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
CREATE PROCEDURE SEARCH_CATEGORY_AND_PRODUCTS(IN search_param VARCHAR(255))
BEGIN
    SELECT * FROM category
    WHERE LCASE(name) LIKE CONCAT('%', search_param, '%')
       OR LCASE(description) LIKE CONCAT('%', search_param, '%');

    SELECT p.* FROM product p
                        INNER JOIN category c ON p.category_id = c.category_id
    WHERE LCASE(c.name) LIKE CONCAT('%', search_param, '%')
       OR LCASE(p.name) LIKE CONCAT('%', search_param, '%');
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE CATEGORY_SORT_BY_NAME()
BEGIN
SELECT *FROM category ORDER BY name;
END; //


delimiter //
create procedure CATEGORY_CHANGE_STATUS(in _id int)
BEGIN
    update category set status = not status where category_id=_id;
END; //


#phân trang
DELIMITER //
CREATE PROCEDURE CATEGORY_PAGINATION(IN limit_in int, IN current_page int, OUT total_page int)
begin
    DECLARE offset_page int;
    SET offset_page = (current_page - 1) * limit_in;
    SET total_page = CEIL((SELECT count(*) from category) / limit_in);
    SELECT * FROM category LIMIT limit_in offset offset_page;
end //


DELIMITER //
CREATE PROCEDURE CATEGORY_CHECK_NAME(in c_name varchar(255))
BEGIN

    select * FROM category where name = c_name;
end; //