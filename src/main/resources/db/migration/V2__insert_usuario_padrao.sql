-- =========================================
-- LINKCAST - USUÁRIO PADRÃO PARA TESTES
-- =========================================

INSERT INTO usuarios (id, nome, email, senha_hash, criado_em)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Usuário Teste',
    'teste@linkcast.com',
    '$2a$10$dummyHashForTestingPurposeOnly',
    CURRENT_TIMESTAMP
) ON CONFLICT (id) DO NOTHING;