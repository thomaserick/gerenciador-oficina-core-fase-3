CREATE TABLE veiculos
(
    id           UUID            NOT NULL,
    cliente_id   UUID            NOT NULL,
    placa        VARCHAR(12)     NOT NULL,
    modelo       VARCHAR(64),
    marca        VARCHAR(64),
    ano          SMALLINT DEFAULT 0,
    CONSTRAINT veiculo_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_clientes   FOREIGN KEY (cliente_id)  REFERENCES clientes(id)
);
CREATE INDEX IF NOT EXISTS idx_veiculos_placa ON veiculos(placa);






