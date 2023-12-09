-- #   ---------------- customer  ----------------
-- #   ---------------- customer  ----------------
-- #   ---------------- customer  ----------------
DELIMITER //
CREATE PROCEDURE CUSTOMER_ADD(
    IN cus_name VARCHAR(255),
    IN cus_img VARCHAR(255),
    IN cus_email VARCHAR(255),
    IN cus_address VARCHAR(255),
    IN cus_phone_number VARCHAR(255),
    IN cus_password VARCHAR(255)
)
BEGIN
INSERT INTO customer (name, img, email, address, phone_number, password)
VALUES (cus_name, cus_img, cus_email, cus_address, cus_phone_number, cus_password);
END; //


-- # Cập nhật thông tin của một khách hàng:
DELIMITER //
CREATE PROCEDURE CUSTOMER_UPDATE(
    IN cus_id INT,
    IN cus_img VARCHAR(255),
    IN cus_name VARCHAR(255),
    IN cus_email VARCHAR(255),
    IN cus_address VARCHAR(255),
    IN cus_phone_number VARCHAR(255)
)
BEGIN
UPDATE customer
SET img = cus_img,
    name = cus_name,
    email = cus_email,
    address = cus_address,
    phone_number = cus_phone_number
WHERE customer_id = cus_id;
END; //


-- #block
DELIMITER //
CREATE PROCEDURE CUSTOMER_CHANGE_STATUS(
    IN cus_id INT
)
BEGIN
Update customer set status = not status WHERE customer_id = cus_id;
END; //


-- #phân quyền
DELIMITER //
CREATE PROCEDURE CUSTOMER_CHANGE_ROLE(
    IN cus_id INT
)
BEGIN
Update customer set role = not role WHERE customer_id = cus_id;
END; //


-- #find_by_all
DELIMITER //
CREATE PROCEDURE CUSTOMER_FY_BY_ALL(
)
BEGIN
Select * from customer;
END; //


-- #find_by_id
DELIMITER //
CREATE PROCEDURE CUSTOMER_FY_BY_ID(
    IN cus_id INT
)
BEGIN
Select *from customer WHERE customer_id = cus_id;
END; //


DELIMITER //
CREATE PROCEDURE CUSTOMER_SEARCH_BY_NAME(IN search_name_param VARCHAR(255))
BEGIN
SELECT * FROM customer
WHERE LCASE(name) LIKE CONCAT('%', search_name_param, '%');
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE CUSTOMER_SORT_BY_NAME()
BEGIN
SELECT * FROM customer
ORDER BY name;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE CUSTOMER_CHECK_EMAIL(in c_email varchar(255))
BEGIN

select * FROM customer where email = c_email;
end; //

-- phân trang
DELIMITER //
create procedure CUSTOMER_PAGINATION(IN _limit int, IN no_page int, OUT total int)
BEGIN
    declare _offset int;
    SET _offset = (no_page - 1) * _limit;
    SET  total = CEIL((SELECT count(*) FROM product) / _limit);
    SELECT * FROM product LIMIT _limit OFFSET _offset;
end; //