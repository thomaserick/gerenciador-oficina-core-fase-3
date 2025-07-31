CREATE TABLE users
(
    id         UUID           NOT NULL,
    name       VARCHAR(255)   NOT NULL,
    last_name  VARCHAR(255)   NOT NULL,
    active     BOOLEAN        NOT NULL,
    email      VARCHAR(255)   NOT NULL UNIQUE,
    password   VARCHAR(255)   NOT NULL,
    CONSTRAINT users_pkey  PRIMARY KEY (id)
);


CREATE TABLE users_role
(
   user_id                UUID		NOT NULL,
   role                   SMALLINT 	NOT NULL,

   CONSTRAINT users_role_pkey		PRIMARY KEY (user_id, role),
   CONSTRAINT fk_users 			    FOREIGN KEY (user_id) REFERENCES users (id)
);

