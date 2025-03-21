-- Ativa a extensão para UUID (se ainda não estiver ativa)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Remove a tabela, se ela existir (para ambiente de testes)
DROP TABLE IF EXISTS favorite_medications;

-- Cria a tabela de favoritos
CREATE TABLE favorite_medications (
    user_id UUID NOT NULL,
    medication_id UUID NOT NULL,
    PRIMARY KEY (user_id, medication_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (medication_id) REFERENCES medications(medication_id) ON DELETE CASCADE
);
