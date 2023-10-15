CREATE SEQUENCE IF NOT EXISTS couriers_sequence;

COMMENT ON SEQUENCE couriers_sequence IS 'Последовательность идентификаторов курьеров';

CREATE TABLE IF NOT EXISTS Couriers(
      id BIGINT DEFAULT NEXTVAL('couriers_sequence'),
      phone VARCHAR(20) NOT NULL UNIQUE,
      status VARCHAR(30) NOT NULL,
      coordinates JSONB,
      CONSTRAINT couriers_pk PRIMARY KEY (id)
);

COMMENT ON TABLE Couriers IS 'Курьеры';
COMMENT ON COLUMN Couriers.id IS 'Идентификатор';
COMMENT ON COLUMN Couriers.phone IS 'Телефон';
COMMENT ON COLUMN Couriers.status IS 'Статус';
COMMENT ON COLUMN Couriers.coordinates IS 'Координаты';