CREATE TABLE customers
(
    id         UUID           NOT NULL,
    name       VARCHAR(255)   NOT NULL,
    active     BOOLEAN        NOT NULL,
    email      VARCHAR(255),
    phone      VARCHAR(32),
    identification_document_origin SMALLINT NOT NULL,
    identification_document_number VARCHAR(64) NOT NULL UNIQUE,
    CONSTRAINT customers_pkey  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_customers_name ON customers(name);
CREATE INDEX IF NOT EXISTS idx_customers_identification_document_number ON customers(identification_document_number);





