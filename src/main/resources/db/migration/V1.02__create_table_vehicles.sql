CREATE TABLE vehicles
(
    id           UUID            NOT NULL,
    customer_id  UUID            NOT NULL,
    plate        VARCHAR(12)     NOT NULL,
    model        VARCHAR(64),
    make         VARCHAR(64),
    model_year   SMALLINT DEFAULT 0,
    CONSTRAINT vehicles_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_customers   FOREIGN KEY (customer_id)  REFERENCES customers(id)
);
CREATE INDEX IF NOT EXISTS idx_vehicles_plate ON vehicles(plate);






