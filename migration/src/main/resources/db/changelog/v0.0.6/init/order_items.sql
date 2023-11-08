CREATE TABLE IF NOT EXISTS Order_items(
    id UUID DEFAULT gen_random_uuid(),
    order_id UUID,
    restaurant_menu_item_id UUID,
    price NUMERIC (10,2) NOT NULL CHECK (price >= 0),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    CONSTRAINT order_items_pk PRIMARY KEY (id),
    CONSTRAINT order_items_orders_fk FOREIGN KEY (order_id)
    REFERENCES Orders (id),
    CONSTRAINT order_items_restaurant_menu_items_fk FOREIGN KEY (restaurant_menu_item_id)
    REFERENCES Restaurant_menu_items (id)
);


COMMENT ON TABLE Order_items IS 'Товары в заказе';
COMMENT ON COLUMN Order_items.id IS 'Идентификатор';
COMMENT ON COLUMN Order_items.order_id IS 'Идентификатор заказа';
COMMENT ON COLUMN Order_items.restaurant_menu_item_id IS 'Индентификатор товара в меню ресторана';
COMMENT ON COLUMN Order_items.price IS 'Цена';
COMMENT ON COLUMN Order_items.quantity IS 'Количество';