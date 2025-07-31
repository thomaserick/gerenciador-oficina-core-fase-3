CREATE TABLE estimates
(
    id            UUID         NOT NULL,
    description   VARCHAR(255),
    customer_id   UUID         NOT NULL,
    vehicle_id    UUID         NOT NULL,
    status        VARCHAR(32)  NOT NULL,
    work_order_id UUID,
    observation   VARCHAR(255),
    odometer     BIGINT        NOT NULL DEFAULT 0,
    created_at  TIMESTAMPTZ    NOT NULL,
    CONSTRAINT estimate_pkey   PRIMARY KEY (id),
    CONSTRAINT fk_customers FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_vehicles  FOREIGN KEY (vehicle_id)  REFERENCES vehicles(id)
);
CREATE INDEX IF NOT EXISTS idx_estimate_customer_id ON estimates(customer_id);
CREATE INDEX IF NOT EXISTS idx_estimate_work_order_id ON estimates(work_order_id);

CREATE TABLE estimates_services
(
    id            UUID         NOT NULL,
    service_id    UUID         NOT NULL,
    estimate_id   UUID         NOT NULL,
    description   VARCHAR(255) NOT NULL,
    price         NUMERIC      NOT NULL,
    quantity      NUMERIC      NOT NULL,
    CONSTRAINT estimates_service_item_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_services FOREIGN KEY (service_id) REFERENCES services(id)
);




