CREATE SEQUENCE IF NOT EXISTS orders_sequence;

COMMENT ON SEQUENCE orders_sequence IS 'Последовательность идентификаторов заказчиков';

CREATE TABLE IF NOT EXISTS Orders(
    id BIGINT DEFAULT NEXTVAL('orders_sequence'),
    customer_id BIGINT,
    restaurant_id BIGINT,
    status VARCHAR(30),
    courier_id BIGINT,
    timestamp TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT orders_pk PRIMARY KEY (id),
    CONSTRAINT orders_customers_fk FOREIGN KEY (customer_id)
    REFERENCES Customers (id),
    CONSTRAINT orders_restaurants_fk FOREIGN KEY (restaurant_id)
    REFERENCES Restaurants (id),
    CONSTRAINT orders_couriers_fk FOREIGN KEY (courier_id)
    REFERENCES Couriers (id)
);

COMMENT ON TABLE Orders IS 'Заказы';
COMMENT ON COLUMN Orders.id IS 'Идентификатор';
COMMENT ON COLUMN Orders.customer_id IS 'Идентификатор заказчика';
COMMENT ON COLUMN Orders.restaurant_id IS 'Идентификатор ресторана';
COMMENT ON COLUMN Orders.status IS 'Статус';
COMMENT ON COLUMN Orders.courier_id IS 'Идентификатор курьера';
COMMENT ON COLUMN Orders.timestamp IS 'Время заказа';