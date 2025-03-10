-- Remover a tabela se já existir
DROP TABLE IF EXISTS PRODUTO;

-- Criar a tabela
CREATE TABLE PRODUTO (
    ID_PRODUTO BIGINT AUTO_INCREMENT PRIMARY KEY,
    CODIGO VARCHAR(255) NOT NULL UNIQUE,
    NOME VARCHAR(255) NOT NULL,
    UN_MEDIDA VARCHAR(6) NOT NULL,
    QUANTIDADE DECIMAL(12,4) NOT NULL,
    VALOR DECIMAL(12,4) NOT NULL,
    STATUS VARCHAR(255) NOT NULL,
    DATA_CADASTRO DATE NOT NULL,
    USUARIO_CADASTRO VARCHAR(255) NOT NULL,
    DATA_ALTERACAO DATE NOT NULL,
    HORA_ALTERACAO TIME NOT NULL,
    USUARIO_ALTERACAO VARCHAR(255) NOT NULL,
    DESCRICAO TEXT NOT NULL
);