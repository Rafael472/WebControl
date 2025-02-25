-- Remover a tabela se já existir
DROP TABLE IF EXISTS FATURAMENTO;

-- Criar a tabela
CREATE TABLE FATURAMENTO (
    ID_FATURAMENTO BIGINT AUTO_INCREMENT PRIMARY KEY,
    DATA_CADASTRO DATE NOT NULL,
    DATA_EMISSAO DATE NOT NULL,
    VALOR DECIMAL(12,4) NOT NULL,
    CLIENTE_ID BIGINT NOT NULL,
    CLIENTE VARCHAR(255) NOT NULL,
    CONSTRAINT FK_FATURAMENTO_CLIENTE FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE (ID_CLIENTE) ON DELETE CASCADE
);