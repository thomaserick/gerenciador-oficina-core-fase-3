INSERT INTO "_IT".usuarios
(id, nome, sobre_nome, ativo, email, senha)
VALUES('ace50297-1785-4a7d-ae6d-8ec2dc450af6', 'Severino', 'da Silva',true,'severino.dasilva@gmail.com','1234');

INSERT INTO "_IT".clientes
(id, nome, ativo, email, telefone, documento_identificacao_origem, documento_identificacao_numero, endereco)
VALUES('113e83b4-02e3-4059-8498-f4beafbb5ed9', 'Hakuna', true, 'hakuna.matata@gmail.com', '47-981050203', 0, '82711650030', 'Av. Santos Dumont, 831 - Térreo - Santo Antônio, Joinville - SC, 89218-900');

INSERT INTO "_IT".veiculos
(id, cliente_id, placa, modelo, marca, ano)
VALUES('e30facee-4403-4494-b506-89feddf52ab1', '113e83b4-02e3-4059-8498-f4beafbb5ed9', 'CUA1H50', 'FIAT 147', 'FIAT', 2025);

INSERT INTO "_IT".diagnosticos
(id, descricao, data_criacao, ordem_servico_id)
VALUES('a52a365c-bbe5-4993-b1af-5dcc6ac125d0', 'Amortecedor vazando', NOW(), 'da088a1a-67ce-463b-a79b-a4ab28fe44ae');

INSERT INTO "_IT".ordens_servico
(id, cliente_id, veiculo_id, status, data_criacao, data_conclusao, diagnostico_id, usuario_id)
VALUES('da088a1a-67ce-463b-a79b-a4ab28fe44ae', '113e83b4-02e3-4059-8498-f4beafbb5ed9', 'e30facee-4403-4494-b506-89feddf52ab1', 'EM_EXECUCAO', NOW(), null, 'a52a365c-bbe5-4993-b1af-5dcc6ac125d0','ace50297-1785-4a7d-ae6d-8ec2dc450af6' );
