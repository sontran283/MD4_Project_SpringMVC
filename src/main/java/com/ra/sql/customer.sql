-- #   ---------------- customer  ----------------
-- #   ---------------- customer  ----------------
-- #   ---------------- customer  ----------------
DELIMITER //
CREATE PROCEDURE CUSTOMER_ADD(
    IN cus_name VARCHAR(255),
    IN cus_email VARCHAR(255),
    IN cus_address VARCHAR(255),
    IN cus_phone_number VARCHAR(255),
    IN cus_password VARCHAR(255),
    IN cus_role BIT,
    IN cus_status BIT
)
BEGIN
INSERT INTO customer (name, email, address, phone_number, password, role, status)
VALUES (cus_name, cus_email, cus_address, cus_phone_number, cus_password, cus_role, cus_status);
END; //


-- # Cập nhật thông tin của một khách hàng:
DELIMITER //
CREATE PROCEDURE CUSTOMER_UPDATE(
    IN cus_id INT,
    IN cus_name VARCHAR(255),
    IN cus_email VARCHAR(255),
    IN cus_address VARCHAR(255),
    IN cus_phone_number VARCHAR(255)
)
BEGIN
UPDATE customer
SET name = cus_name,
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