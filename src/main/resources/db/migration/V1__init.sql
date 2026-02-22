-- =========================================
-- LINKCAST - ESTRUTURA INICIAL DO BANCO
-- =========================================

-- Extensão para gerar UUID automaticamente
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- =========================================
-- TABELA: usuarios
-- =========================================
CREATE TABLE usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(180) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- TABELA: conteudos
-- =========================================
CREATE TABLE conteudos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id UUID NOT NULL,
    titulo VARCHAR(300),
    url_origem TEXT NOT NULL,
    texto_original TEXT,
    status VARCHAR(30) NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_conteudo_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE CASCADE
);

-- =========================================
-- TABELA: episodios
-- =========================================
CREATE TABLE episodios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conteudo_id UUID NOT NULL,
    titulo VARCHAR(300),
    descricao TEXT,
    duracao_segundos INTEGER,
    status VARCHAR(30) NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_episodio_conteudo
        FOREIGN KEY (conteudo_id)
        REFERENCES conteudos(id)
        ON DELETE CASCADE
);

-- =========================================
-- TABELA: audios
-- =========================================
CREATE TABLE audios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    episodio_id UUID NOT NULL,
    url_arquivo TEXT NOT NULL,
    formato VARCHAR(20) NOT NULL,
    tamanho_bytes BIGINT,
    gerado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_audio_episodio
        FOREIGN KEY (episodio_id)
        REFERENCES episodios(id)
        ON DELETE CASCADE
);

-- =========================================
-- ÍNDICES IMPORTANTES
-- =========================================
CREATE INDEX idx_conteudos_usuario ON conteudos(usuario_id);
CREATE INDEX idx_episodios_conteudo ON episodios(conteudo_id);
CREATE INDEX idx_audios_episodio ON audios(episodio_id);