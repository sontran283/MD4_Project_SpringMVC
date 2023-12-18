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
                        img varchar(255),
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
                         img varchar(255) ,
                         name varchar(255) ,
                         email varchar(255) ,
                         address varchar(255) ,
                         phone_number varchar(255)  ,
                         password varchar(255) ,
                         role bit(1) default 0 ,
                         status bit(1) default 1
);

CREATE TABLE orders (
                        order_id  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        customer_id INT NOT NULL,
                        order_date DATE NOT NULL default( CURDATE()),
                        FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
                        total DOUBLE NOT NULL,
                        status int default 1,
                        phone varchar(25),
                        address text,
                        note varchar(255)
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
                          product_id int not null,
                          price double NOT NULL,
                          quantity int NOT NULL
);