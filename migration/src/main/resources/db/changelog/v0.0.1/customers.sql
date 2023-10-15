CREATE SEQUENCE IF NOT EXISTS customers_sequence;

COMMENT ON SEQUENCE customers_sequence IS 'Последовательность идентификаторов заказчиков';

CREATE TABLE IF NOT EXISTS Customers(
    id BIGINT DEFAULT NEXTVAL('customers_sequence'),
    phone VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(128) NOT NULL UNIQUE,
    address VARCHAR(256) NOT NULL,
    CONSTRAINT customers_pk PRIMARY KEY (id)
);

COMMENT ON TABLE Customers IS 'Заказчики';
COMMENT ON COLUMN Customers.id IS 'Идентификатор';
COMMENT ON COLUMN Customers.phone IS 'Телефон';
COMMENT ON COLUMN Customers.email IS 'Электронная почта';
COMMENT ON COLUMN Customers.address IS 'Адрес';