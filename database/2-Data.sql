INSERT INTO pruebat.depreciation (id, percentage, fiscal_year)
VALUES (NEXTVAL('pruebat.seq_depreciation'), 4.00, '2023');

INSERT INTO pruebat.purchases (id,date_purchase,value_purchase)
VALUES (NEXTVAL('pruebat.seq_purchases'),'2020-01-01', 2000);

INSERT INTO pruebat.assets (id,name,serial_number,description,id_purchase)
VALUES (NEXTVAL('pruebat.seq_assets'), 'Nombre','121313','Descripcion',1);