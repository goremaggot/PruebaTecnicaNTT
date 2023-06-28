CREATE SCHEMA pruebat;

CREATE TABLE
    IF NOT EXISTS pruebat.purchases (
        id INT NOT NULL UNIQUE,
        date_purchase DATE NOT NULL,
        value_purchase INT NOT NULL,
        CONSTRAINT pk_purchase PRIMARY KEY (id)
    );

CREATE TABLE
    IF NOT EXISTS pruebat.assets (
        id INT NOT NULL UNIQUE,
        name VARCHAR(50) NOT NULL,
        serial_number VARCHAR(20) NOT NULL,
        description VARCHAR(50) NOT NULL,
        id_purchase INT NOT NULL,
        CONSTRAINT pk_asset PRIMARY KEY (id),
        CONSTRAINT fk_asset_purchase FOREIGN KEY (id_purchase) REFERENCES pruebat.purchases (id)
    );

CREATE TABLE
    IF NOT EXISTS pruebat.depreciation (
        id INT NOT NULL UNIQUE,
        percentage NUMERIC(5, 2) NOT NULL,
        fiscal_year VARCHAR(4),
        CONSTRAINT pk_depreciation PRIMARY KEY (id)
    );

CREATE SEQUENCE pruebat.seq_assets
INCREMENT 1  
START 1;

CREATE SEQUENCE pruebat.seq_purchases
INCREMENT 1  
START 1;

CREATE SEQUENCE pruebat.seq_depreciation
INCREMENT 1  
START 1;