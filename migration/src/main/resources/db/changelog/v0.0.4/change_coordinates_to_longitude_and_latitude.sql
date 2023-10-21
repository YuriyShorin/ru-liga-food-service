ALTER TABLE Customers
DROP COLUMN coordinates;

ALTER TABLE Restaurants
DROP COLUMN coordinates;

ALTER TABLE Couriers
DROP COLUMN coordinates;

ALTER TABLE Customers
ADD COLUMN longitude NUMERIC;

ALTER TABLE Customers
ADD COLUMN latitude NUMERIC;

ALTER TABLE Restaurants
ADD COLUMN longitude NUMERIC;

ALTER TABLE Restaurants
ADD COLUMN latitude NUMERIC;

ALTER TABLE Couriers
ADD COLUMN longitude NUMERIC;

ALTER TABLE Couriers
ADD COLUMN latitude NUMERIC;

