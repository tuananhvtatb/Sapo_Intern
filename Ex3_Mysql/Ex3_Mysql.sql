CREATE SCHEMA `ex3_mysql` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE ex3_mysql;

CREATE TABLE tbl_repository
(
    id            int          NOT NULL AUTO_INCREMENT,
    id_repository varchar(10)  NOT NULL UNIQUE,
    name          varchar(45)  NOT NULL,
    place         varchar(100) NOT NULL,
    created_date  DATETIME,
    updated_date  DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE tbl_category
(
    id           int         NOT NULL AUTO_INCREMENT,
    id_category  varchar(10) NOT NULL UNIQUE,
    name         varchar(45) NOT NULL,
    description  varchar(1000),
    created_date DATETIME,
    updated_date DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE tbl_product
(
    id            int         NOT NULL AUTO_INCREMENT,
    id_product    varchar(10) NOT NULL UNIQUE,
    category_id   int         NOT NULL,
    repository_id int         NOT NULL,
    name          varchar(45) NOT NULL,
    description   varchar(1000),
    path_image    varchar(200),
    amount        int         NOT NULL,
    amount_sell   int,
    created_date  DATETIME,
    updated_date  DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES tbl_category (id),
    FOREIGN KEY (repository_id) REFERENCES tbl_repository (id)
);
-- Them kho, danh muc, san pham
INSERT INTO tbl_repository(id_repository, name, place, created_date, updated_date)
VALUES ('KHO01', 'Kho A', 'Ham A', now(), null),
       ('KHO02', 'Kho B', 'Ham B', now(), null),
       ('KHO03', 'Kho C', 'Ham C', now(), null);

INSERT INTO tbl_category(id_category, name, description, created_date, updated_date)
VALUES ('DM01', 'Danh Muc 1', 'Not des', now(), null),
       ('DM02', 'Danh Muc 2', 'OK', now(), null),
       ('DM03', 'Danh Muc 3', 'Test', now(), null),
       ('DM04', 'Apple', 'Test', now(), null);

INSERT INTO tbl_product(id_product, category_id, repository_id, name, description, path_image, amount, amount_sell,
                        created_date, updated_date)
VALUES ('SP01', 2, 3, 'San Pham 1', 'Dep', 'product-02.png', 102, 98, now(), null),
       ('SP02', 1, 3, 'San Pham 2', 'On dinh', 'product-03.png', 132, 35, now(), null),
       ('SP03', 2, 1, 'San Pham 3', 'Ben', 'product-04.png', 14, 2, now(), null),
       ('SP06', 8, 1, 'Dien thoai', 'Ben', 'product-04.png', 14, 2, now(), null);
INSERT INTO tbl_product(id_product, category_id, repository_id, name, description, path_image, amount, amount_sell,
                        created_date, updated_date)
VALUES ('SP06', 8, 1, 'Tu lanh', 'Ben', 'product-04.png', 14, 2, now(), null);


-- Sua san pham, kho, danh muc
UPDATE tbl_repository
SET place = 'Ham X'
WHERE id = 2;

UPDATE tbl_category
SET name = 'Danh Muc X'
WHERE id = 2;

UPDATE tbl_product
SET amount = 500
WHERE id = 2;

-- Xoa san pham, kho, danh muc
DELETE
FROM tbl_repository
WHERE id = 2;
DELETE
FROM tbl_category
WHERE id = 3;
DELETE
FROM tbl_product
WHERE id = 1;

-- Lọc các sản phẩm có chứa từ 'Điện Thoại' và thuộc loại danh mục có tên là 'Apple'
SELECT *
FROM tbl_product
WHERE name = 'Dien thoai'
  AND category_id = (SELECT id FROM tbl_category WHERE name = "Apple");


SELECT *
FROM tbl_category c
         inner join tbl_product p on c.id = p.category_id;

-- Đếm số lượng sản phẩm trong mỗi loại danh mục, sắp xếp theo thứ tự giảm dần
SELECT c.name AS name_category, p.name AS Name_Product, SUM(amount) AS amount_forcate
FROM tbl_category c
         INNER JOIN tbl_product p ON c.id = p.category_id
GROUP BY c.name
ORDER BY amount_forcate DESC

-- Xóa danh mục đồng thời xóa luôn các sản phẩm thuộc danh mục đó (Có sử dụng transaction)
DELIMITER $$
CREATE TRIGGER tg_delete_category
    BEFORE DELETE
    ON tbl_category
    FOR EACH ROW
BEGIN
    DELETE FROM tbl_product WHERE tbl_product.category_id = OLD.id ;
    END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tg_delete_repository
    BEFORE DELETE
    ON tbl_repository
    FOR EACH ROW
BEGIN
    DELETE FROM tbl_product WHERE tbl_product.repository_id = OLD.id ;
END$$
DELIMITER ;

DROP TRIGGER tg_delete_repository

DELETE
FROM tbl_category
WHERE id = 2;
SELECT *
FROM tbl_product

-- Procedure lấy 10 sản phẩm có số lượng bán nhiều nhất
         DELIMITER $$
CREATE PROCEDURE proc_select_10_product_amout_sell_max()
BEGIN
    SELECT *
    FROM tbl_product
    ORDER BY amount_sell DESC
    LIMIT 0,3;
END;
$$
DELIMITER ;

Drop procedure proc_select_10_product_amout_sell_max
/*Gọi Procedure lấy 10 sản phẩm có số lượng bán nhiều nhất*/
call proc_select_10_product_amout_sell_max();


 

