CREATE TABLE ordem_servico
(
    id               UUID         NOT NULL,
    cliente_id       UUID         NOT NULL,
    veiculo_id       UUID         NOT NULL,
    status           VARCHAR(32)  NOT NULL,
    data_criacao     TIMESTAMPTZ  NOT NULL,
    data_conclusao   TIMESTAMPTZ,
    diagnostico_id   UUID         NOT NULL,
    CONSTRAINT ordem_servico_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_ordem_servico_diagnostico FOREIGN KEY (diagnostico_id) REFERENCES diagnostico(id)
);