/****** OBJECT:  TABLE [DBO].[FATURAMENTO]    SCRIPT DATE: 07/07/2021 13:02 ******/
CREATE TABLE FATURAMENTO (
  ID_FATURAMENTO BIGINT IDENTITY(1,1) NOT NULL,
  DATA_CADASTRO DATE NOT NULL,
  DATA_EMISSAO DATE NOT NULL,
  VALOR DECIMAL(12,4) NOT NULL,
  CLIENTE_ID BIGINT NOT NULL,
  CLIENTE VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID_FATURAMENTO),
  FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE (ID_CLIENTE)
)