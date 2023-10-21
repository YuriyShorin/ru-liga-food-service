ALTER TABLE Customers
ADD COLUMN coordinates JSONB;

ALTER TABLE Restaurants
ADD COLUMN coordinates JSONB;

ALTER TABLE Couriers
ADD COLUMN coordinates JSONB;

ALTER TABLE Customers
DROP COLUMN longitude;

ALTER TABLE Customers
DROP COLUMN latitude;

ALTER TABLE Restaurants
DROP COLUMN longitude;

ALTER TABLE Restaurants
DROP COLUMN latitude;

ALTER TABLE Couriers
DROP COLUMN longitude;

ALTER TABLE Couriers
DROP COLUMN latitude;