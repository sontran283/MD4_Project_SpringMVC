CREATE DATABASE project_module4_webfruit;
USE project_module4_webfruit;

create table category(
category_id int primary key NOT NULL auto_increment,
name varchar(255) NOT NULL unique,
description varchar(255) NOT NULL,
status bit(1)
);

create table product(
product_id int primary key NOT NULL auto_increment,
category_id int NOT NULL ,
foreign key (category_id) REFERENCES category(category_id),
name varchar(255) NOT NULL unique,
description varchar(255) NOT NULL,
price double NOT NULL,
quantity int NOT NULL,
status bit(1)
);

create table image(
id int primary key NOT NULL auto_increment,
url text NOT NULL ,
product_id int not null,
foreign key (product_id) REFERENCES product(product_id)
);

create table customer(
customer_id int primary key NOT NULL auto_increment,
name varchar(255) NOT NULL unique,
email varchar(255) NOT NULL,
address varchar(255) NOT NULL,
phone_number double not null ,
password varchar(255) NOT NULL,
role bit(1) default 0 not null,
status bit(1)
);

CREATE TABLE orders (
order_id  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
customer_id INT NOT NULL,
order_date DATE DEFAULT CURRENT_DATE(),
FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
total DOUBLE NOT NULL,
status bit(1)
);

create table order_detail(
order_id int not null,
product_id int not null,
foreign key (order_id) references orders(order_id),
foreign key (product_id) references product(product_id),
quantity int NOT NULL,
price double NOT NULL,
primary key (order_id, product_id)
);

create table cart(
cart_id int primary key NOT NULL auto_increment,
customer_id int not null,
foreign key (customer_id) references customer(customer_id)
);

create table cart_item(
cartitem_id int primary key NOT NULL auto_increment,
cart_id int not null,
foreign key (cart_id) references cart(cart_id),
product_id int not null,
price double NOT NULL,
quantity int NOT NULL
);


#   ---------------- category  ----------------
#   ---------------- category  ----------------
#   ---------------- category  ----------------
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


# Cập nhật thông tin của một danh mục:
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


# xoá danh mục
DELIMITER //
CREATE PROCEDURE CATEGORY_DELETE(
IN c_category_id INT
)
BEGIN
DELETE FROM category WHERE category_id = c_category_id;
END; //


# đổi trạng thái danh mục:
DELIMITER //
CREATE PROCEDURE CATEGORY_UPDATE_STATUS(
IN c_category_id INT
)
BEGIN
UPDATE category
SET status = not status
WHERE category_id = c_category_id;
END; //


# findall
DELIMITER //
CREATE PROCEDURE CATEGORY_FIND_ALL(
)
BEGIN
SELECT* FROM category;
END; //


#findByID
DELIMITER //
CREATE PROCEDURE CATEGORY_FIND_BY_ID(IN c_category_id int)
BEGIN
SELECT* FROM category where category_id = c_category_id;
END; //



#   ---------------- product  ----------------
#   ---------------- product  ----------------
#   ---------------- product  ----------------
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


# Cập nhật thông tin của một sản phẩm:
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


# Xóa một sản phẩm:
DELIMITER //
CREATE PROCEDURE PRODUCT_DELETE(
IN p_product_id INT
)
BEGIN
DELETE FROM product WHERE product_id = p_product_id;
END; //


#findall
DELIMITER //
CREATE PROCEDURE PRODUCT_FIND_ALL(
)
BEGIN
SELECT *FROM product;
END; //


# findbyid
DELIMITER //
CREATE PROCEDURE PRODUCT_FIND_BY_ID(
IN p_product_id INT
)
BEGIN
SELECT *FROM product WHERE product_id = p_product_id;
END; //



#   ---------------- image  ----------------
#   ---------------- image  ----------------
#   ---------------- image  ----------------
DELIMITER //
CREATE PROCEDURE IMAGE_ADD(
IN i_url VARCHAR(255),
IN i_product_id INT
)
BEGIN
INSERT INTO image (url, product_id)
VALUES (i_url, i_product_id);
END; //


# Cập nhật thông tin của một hình ảnh:
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


# Xóa một hình ảnh:
DELIMITER //
CREATE PROCEDURE IMAGE_DELETE(
IN i_id INT
)
BEGIN
DELETE FROM image WHERE id = i_id;
END; //


#find_all
DELIMITER //
CREATE PROCEDURE IMAGE_FIND_ALL(
)
BEGIN
SELECT * FROM image;
END; //


#find_by_id
DELIMITER //
CREATE PROCEDURE IMAGE_FIND_BY_ID(
IN i_id INT
)
BEGIN
SELECT *FROM image WHERE id = i_id;
END; //



#   ---------------- customer  ----------------
#   ---------------- customer  ----------------
#   ---------------- customer  ----------------
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


# Cập nhật thông tin của một khách hàng:
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


#block
DELIMITER //
CREATE PROCEDURE CUSTOMER_CHANGE_STATUS(
IN cus_id INT
)
BEGIN
Update customer set status = not status WHERE customer_id = cus_id;
END; //


#phân quyền
DELIMITER //
CREATE PROCEDURE CUSTOMER_CHANGE_ROLE(
IN cus_id INT
)
BEGIN
Update customer set role = not role WHERE customer_id = cus_id;
END; //


#find_by_all
DELIMITER //
CREATE PROCEDURE CUSTOMER_FY_BY_ALL(
)
BEGIN
Select * from customer;
END; //


#find_by_id
DELIMITER //
CREATE PROCEDURE CUSTOMER_FY_BY_ID(
IN cus_id INT
)
BEGIN
Select *from customer WHERE customer_id = cus_id;
END; //


#   ---------------- orders  ----------------
#   ---------------- orders  ----------------
#   ---------------- orders  ----------------
DELIMITER //
CREATE PROCEDURE ORDER_ADD(
IN or_customer_id INT,
IN or_total DOUBLE
)
BEGIN
INSERT INTO orders (customer_id, total)
VALUES (or_customer_id, or_total);
END //
DELIMITER ;


#update trạng thái
DELIMITER //
CREATE PROCEDURE ORDER_CHANGE_STATUS(
IN or_id INT,
IN or_status BIT
)
BEGIN
UPDATE orders
SET
status = or_status
WHERE order_id = or_id;
END //
DELIMITER ;



-- Sửa thông tin đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_UPDATE(
IN or_order_id INT,
IN or_customer_id INT,
IN or_total DOUBLE,
IN or_status BIT
)
BEGIN
UPDATE orders
SET customer_id = or_customer_id, total = or_total, status = or_status
WHERE order_id = or_order_id;
END //
DELIMITER ;


-- Xoá đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_DELETE(
IN or_order_id INT
)
BEGIN
DELETE FROM orders WHERE order_id = or_order_id;
END //
DELIMITER ;



-- Lấy tất cả đơn hàng
DELIMITER //
CREATE PROCEDURE ORDER_FY_BY_ALL()
BEGIN
SELECT * FROM orders;
END //
DELIMITER ;


-- Lấy đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_FY_BY_ID(
IN or_id INT
)
BEGIN
SELECT * FROM orders WHERE order_id = or_id;
END //
DELIMITER ;



#   ---------------- order_detail  ----------------
#   ---------------- order_detail  ----------------
#   ---------------- order_detail  ----------------
-- Thêm chi tiết đơn hàng
DELIMITER //
CREATE PROCEDURE ORDER_DETAIL_ADD(
IN dt_order_id INT,
IN dt_product_id INT,
IN dt_quantity INT,
IN dt_price DOUBLE
)
BEGIN
INSERT INTO order_detail (order_id, product_id, quantity, price)
VALUES (dt_order_id, dt_product_id, dt_quantity, dt_price);
END; //


-- Sửa thông tin chi tiết đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_DETAIL_UPDATE(
IN dt_order_id INT,
IN dt_product_id INT,
IN dt_quantity INT,
IN dt_price DECIMAL(10, 2)
)
BEGIN
UPDATE order_detail
SET quantity = dt_quantity, price = dt_price
WHERE order_id = dt_order_id AND product_id = dt_product_id;
END; //


-- Xoá chi tiết đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_DETAIL_DELETE(
IN dt_order_id INT,
IN dt_product_id INT
)
BEGIN
DELETE FROM order_detail WHERE order_id = dt_order_id AND product_id = dt_product_id;
END; //


-- Lấy tất cả chi tiết đơn hàng
DELIMITER //
CREATE PROCEDURE ORDER_DETAIL_FY_BY_ALL()
BEGIN
SELECT * FROM order_detail;
END; //


-- Lấy chi tiết đơn hàng theo ID
DELIMITER //
CREATE PROCEDURE ORDER_DETAIL_FY_BY_ID(
IN dt_order_id INT,
IN dt_product_id INT
)
BEGIN
SELECT * FROM order_detail WHERE order_id = dt_order_id AND product_id = dt_product_id;
END; //



#   ---------------- cart  ----------------
#   ---------------- cart  ----------------
#   ---------------- cart  ----------------
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



#   ---------------- cart_item  ----------------
#   ---------------- cart_item  ----------------
#   ---------------- cart_item  ----------------
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
IN cit_cart_id INT,
IN cit_product_id INT,
IN cit_price DECIMAL(10, 2),
IN cit_quantity INT
)
BEGIN
UPDATE cart_item
SET cart_id = cit_cart_id, product_id = cit_product_id, price = cit_price, quantity = cit_quantity
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
CREATE PROCEDURE CART_ITEM_ADD_FY_BY_ALL()
BEGIN
SELECT * FROM cart_item;
END; //


-- Lấy mục giỏ hàng theo ID
DELIMITER //
CREATE PROCEDURE CART_ITEM_ADD_FY_BY_ID(
IN cit_cartitem_id INT
)
BEGIN
SELECT * FROM cart_item WHERE cartitem_id = cit_cartitem_id;
END; //

