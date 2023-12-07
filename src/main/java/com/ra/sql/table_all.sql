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
                         status bit(1) default 1 not null
);

CREATE TABLE orders (
                        order_id  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        customer_id INT NOT NULL,
                        order_date DATE NOT NULL default( CURDATE()),
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