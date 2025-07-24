CREATE TABLE usuario
(
    id         UUID           NOT NULL,
    nome       VARCHAR(255)   NOT NULL,
    sobre_nome VARCHAR(255)   NOT NULL,
    ativo      BOOLEAN        NOT NULL,
    email      VARCHAR(255)   NOT NULL UNIQUE,
    senha      VARCHAR(255)   NOT NULL,
    CONSTRAINT usuario_pkey  PRIMARY KEY (id)
);


CREATE TABLE usuario_perfil
(
   usuario_id               UUID		NOT NULL,
   perfil                   SMALLINT 	NOT NULL,

   CONSTRAINT usuario_perfil_pkey		PRIMARY KEY (usuario_id, perfil),
   CONSTRAINT fk_usuario 			    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

