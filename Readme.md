# Prueba Tecnica NTT Data
## _Ejercicio Api Rest_


Se Realiza Ejercicio segun lo solicitado en Spring boot y java 20 con bd PostgresSQL se crean las siguientes carpetas

- database : Contiene la creacion de las tablas y data ejemplo
- ✨Lo Demas haceparte del poyecto Spring Boot✨
- Se generan archivos Dockerfile docker-compose.yml y Makefile

## Instalacion

- Se puede ejecutar make up, si se tene instalado make en el equipo
- Se puede ejecutar docker compose up -d --build para compilar y cargar el proyecto
- Para ejecutar en local cambiar el archivo properties en donde dice database a localhost

## Url de swagger:
Para validar la documentacion del proyecto con swagger se debe ingresar  a la url

http://localhost:8080/swagger-ui/index.html

Ahi se pueden ejecutar los endpoints Correspondientes

##Data Ejemplo
Se deja insertado previamente los siguientes registros en la db

```sql
INSERT INTO pruebat.depreciation (id, percentage, fiscal_year)
VALUES (NEXTVAL('pruebat.seq_depreciation'), 4.00, '2023');

INSERT INTO pruebat.purchases (id,date_purchase,value_purchase)
VALUES (NEXTVAL('pruebat.seq_purchases'),'2020-01-01', 2000);

INSERT INTO pruebat.assets (id,name,serial_number,description,id_purchase)
VALUES (NEXTVAL('pruebat.seq_assets'), 'Nombre','121313','Descripcion',1);
```
##EndPoints
En el endpoint assets/1 se podra observar la consulta del activo con el siguiente json de respuesta

    {
      "id": 1,
      "name": "Nombre",
      "serialNumber": "121313",
      "description": "Descripcion",
      "datePurchase": "2020-01-01",
      "valuePurchase": 2000,
      "valueWithDepreciation": 1760
    }
En donde Value Purchase es el valor inicial cuando se realizo la compra y ValueWithDepreciation Es el valor con el porcentaje aplicado.

Los demas endpoints se pueden consultar en la documentancion con swagger y OpenAi

Realizado Por Ricardo Vallejo
