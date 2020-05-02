DROP TABLE product;

CREATE TABLE product(
    p_id        INT         NOT NULL    PRIMARY KEY ,
    p_type      VARCHAR(20)                         ,
    p_name      VARCHAR(30) NOT NULL                ,
    p_price     INT                                 ,
    p_amount    INT                                 ,
    p_status    VARCHAR(20)                         
    );
    
INSERT INTO product VALUES (1,'Beverage','Bubble Tea',40,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (2,'Beverage','Thai Milk Tea with Bubble',30,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (3,'Beverage','Matcha Green Tea',40,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (4,'Beverage','Cocoa Freppe with Bubble',45,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (5,'Beverage','Lemon Green Tea',35,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (6,'Dessert','Pancake',25,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (7,'Dessert','Waffle with fillings',20,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (8,'Dessert','Cheesecake',50,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (9,'Dessert','Ice-cream',20,0,'OUT_OF_STOCK');
INSERT INTO product VALUES (10,'Dessert','Cupcake',30,0,'OUT_OF_STOCK');
