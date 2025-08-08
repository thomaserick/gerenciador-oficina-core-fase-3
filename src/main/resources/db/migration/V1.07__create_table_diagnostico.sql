CREATE TABLE diagnostico
(
    id                  UUID            NOT NULL,
    descricao           VARCHAR(255)    NOT NULL,
    data_criacao        TIMESTAMPTZ     NOT NULL,
    ordem_servico_id   UUID            NOT NULL,
    CONSTRAINT diagnostico_pkey  PRIMARY KEY (id)
);

-- Índices para melhor performance
CREATE INDEX idx_ordem_servico ON diagnostico(ordem_servico_id);