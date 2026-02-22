export type StatusConteudo = 'IMPORTADO' | 'PROCESSANDO' | 'PRONTO' | 'ERRO';

export interface Conteudo {
  id: string;
  usuarioId: string;
  titulo: string | null;
  urlOrigem: string;
  textoOriginal: string | null;
  status: StatusConteudo;
  criadoEm: string;
}

export interface CriarConteudoRequest {
  usuarioId: string;
  titulo: string | null;
  urlOrigem: string;
  textoOriginal: string | null;
}