CREATE TABLE pecas_insumos (
    id UUID PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    modelo_veiculo VARCHAR(255),
    valor_unitario DECIMAL(10,2) NOT NULL,
    quantidade_estoque INTEGER NOT NULL DEFAULT 0,
    quantidade_minimo_estoque INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para melhor performance
CREATE INDEX idx_pecas_insumos_modelo_veiculo ON pecas_insumos(modelo_veiculo);
CREATE INDEX idx_pecas_insumos_descricao ON pecas_insumos(descricao);
