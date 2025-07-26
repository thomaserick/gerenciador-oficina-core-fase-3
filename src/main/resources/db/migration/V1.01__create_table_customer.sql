CREATE TABLE customer
(
    id         UUID           NOT NULL,
    name       VARCHAR(255)   NOT NULL,
    active     BOOLEAN        NOT NULL,
    email      VARCHAR(255),
    phone      VARCHAR(32),
    identification_document_origin SMALLINT NOT NULL,
    identification_document_number VARCHAR(64) NOT NULL,
    CONSTRAINT customer_pkey  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_customer_name ON customer(name);
CREATE INDEX IF NOT EXISTS idx_customer_identification_document_number ON customer(identification_document_number);





