CREATE TABLE pecas_insumos (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    quantidade_estoque INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para melhor performance
CREATE INDEX idx_pecas_insumos_nome ON pecas_insumos(nome);
CREATE INDEX idx_pecas_insumos_descricao ON pecas_insumos(descricao);
CREATE INDEX idx_pecas_insumos_valor_unitario ON pecas_insumos(valor_unitario);
CREATE INDEX idx_pecas_insumos_quantidade_estoque ON pecas_insumos(quantidade_estoque); 