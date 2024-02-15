DROP TABLE IF EXISTS shopping_item;

CREATE TABLE shopping_item (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    category VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    active BOOLEAN NOT NULL DEFAULT false
);