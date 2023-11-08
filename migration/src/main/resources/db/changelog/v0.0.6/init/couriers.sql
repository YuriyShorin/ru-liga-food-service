CREATE TABLE IF NOT EXISTS Couriers (
    id UUID DEFAULT gen_random_uuid(),
    phone VARCHAR(20) NOT NULL UNIQUE,
    status VARCHAR(30) NOT NULL,
    longitude NUMERIC,
    latitude NUMERIC,
    CONSTRAINT couriers_pk PRIMARY KEY (id)
);

COMMENT ON TABLE Couriers IS 'Курьеры';
COMMENT ON COLUMN Couriers.id IS 'Идентификатор';
COMMENT ON COLUMN Couriers.phone IS 'Телефон';
COMMENT ON COLUMN Couriers.status IS 'Статус';
COMMENT ON COLUMN Couriers.longitude IS 'Долгота';
COMMENT ON COLUMN Couriers.latitude IS 'Широта';