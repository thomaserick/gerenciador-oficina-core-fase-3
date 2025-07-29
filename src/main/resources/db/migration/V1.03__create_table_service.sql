CREATE TABLE service
(
    id            UUID             NOT NULL,
    description   VARCHAR(255)     NOT NULL,
    price         NUMERIC,
    active        BOOLEAN,
    observation   VARCHAR(255) ,
    CONSTRAINT service_pkey  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_service_description ON service(description);






