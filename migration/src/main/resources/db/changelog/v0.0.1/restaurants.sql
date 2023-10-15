CREATE SEQUENCE IF NOT EXISTS restaurants_sequence;

COMMENT ON SEQUENCE restaurants_sequence IS 'Последовательность идентификаторов ресторанов';

CREATE TABLE IF NOT EXISTS Restaurants(
    id BIGINT DEFAULT NEXTVAL('restaurants_sequence'),
    address VARCHAR(256) NOT NULL,
    status VARCHAR(30) NOT NULL,
    CONSTRAINT restaurants_pk PRIMARY KEY (id)
);

COMMENT ON TABLE Restaurants IS 'Рестораны';
COMMENT ON COLUMN Restaurants.id IS 'Идентификатор';
COMMENT ON COLUMN Restaurants.address IS 'Адрес';
COMMENT ON COLUMN Restaurants.status IS 'Статус';