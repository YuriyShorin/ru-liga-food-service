INSERT INTO Orders(id, customer_id, restaurant_id, status, timestamp)
VALUES ('d2988db6-ec40-4cbd-8989-6f920d180120', '10ccfb57-a0a8-43c1-88c2-0040e627ead0', '72ee7ac1-3d51-4998-8518-0c756dce049f', 'CUSTOMER_CREATED', now()),
       ('cc9ba4b8-3b0b-4b76-84aa-b834b3c48497', 'a70a7965-5824-4f80-8f7f-589564bde188', '789a09a5-cf2e-4fd6-83a6-3df822577b78', 'CUSTOMER_CREATED', now()),
       ('7e2e169e-1419-4857-ba71-b39fec011d75', 'd402e2d6-daa6-4eb5-8a3c-3ba26e945824', '789a09a5-cf2e-4fd6-83a6-3df822577b78', 'CUSTOMER_CREATED', now()),
       ('223e79a5-ff75-48a1-a598-ab65522892d3', 'f68821de-83d8-4784-ab65-cead3c3ee406', '4dd574d3-8abf-4902-92e6-03a492d8b69a', 'KITCHEN_PREPARING', now()),
       ('2ce4dc40-2968-4a85-a5c1-756f4ec65761', '2eec9b13-e128-4d05-a5bf-a1fb73b511d9', '0d6bfb73-ec86-4534-bf42-f0fdff689777', 'KITCHEN_PREPARING', now()),
       ('2572f05f-21c2-4522-b37f-b7709221d4e0', 'cad28c3b-2f54-4da7-abc2-77fe93671ac9', 'fc285086-e4ae-46d2-ae07-05111a966ba6', 'KITCHEN_FINISHED', now()),
       ('aef90bba-0601-440d-96c3-b6a308fd64ef', '791aac2c-5db6-40c6-9255-82c5de6c0896', '08b3c903-411a-46f7-b41f-0f0c115c6add', 'KITCHEN_FINISHED',  now())