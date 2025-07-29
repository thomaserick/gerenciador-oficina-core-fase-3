CREATE TABLE vehicle
(
    id           UUID            NOT NULL,
    customer_id  UUID            NOT NULL,
    plate        VARCHAR(12)     NOT NULL,
    model        VARCHAR(64),
    make         VARCHAR(64),
    model_year   VARCHAR(4),
    CONSTRAINT vehicle_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_customer   FOREIGN KEY (customer_id)  REFERENCES customer(id)
);
CREATE INDEX IF NOT EXISTS idx_vehicle_plate ON vehicle(plate);






