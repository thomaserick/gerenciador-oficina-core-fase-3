CREATE TABLE usuario
(
    id    UUID NOT NULL,
    nome  VARCHAR(255)   NOT NULL,
    email VARCHAR(255)   NOT NULL UNIQUE,
    senha VARCHAR(255)   NOT NULL,
    CONSTRAINT usuario_pkey  PRIMARY KEY (id)
);

