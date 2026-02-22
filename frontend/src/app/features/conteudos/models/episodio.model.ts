export type StatusEpisodio = 'GERANDO' | 'PRONTO' | 'FALHA';

export interface Episodio {
  id: string;
  conteudoId: string;
  titulo: string | null;
  descricao: string | null;
  duracaoSegundos: number | null;
  status: StatusEpisodio;
  criadoEm: string;
}

export interface CriarEpisodioRequest {
  titulo: string | null;
  descricao: string | null;
}