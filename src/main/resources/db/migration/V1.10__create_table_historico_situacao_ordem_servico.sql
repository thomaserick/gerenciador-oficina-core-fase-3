CREATE TABLE situacao_ordem_servico
(
    id                UUID         NOT NULL,
    status            VARCHAR(32)  NOT NULL,
    ordem_servico_id  UUID         NOT NULL,
    data_criacao      TIMESTAMPTZ  NOT NULL,
    usuario_id        UUID         NOT NULL,

    CONSTRAINT situacao_ordem_servico_pkey  PRIMARY KEY (id)
);

-- Índices para melhor performance
CREATE INDEX idx_situacao_ordem_servico_ordem_servico ON situacao_ordem_servico(ordem_servico_id);