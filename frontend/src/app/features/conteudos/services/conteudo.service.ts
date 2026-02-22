import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Conteudo, CriarConteudoRequest } from '../models/conteudo.model';
import { Episodio, CriarEpisodioRequest } from '../models/episodio.model';
import { ApiError } from '../models/api-error.model';

export interface CriarConteudoResponse {
  conteudo: Conteudo;
  episodio: Episodio;
}

@Injectable({
  providedIn: 'root'
})
export class ConteudoService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = environment.apiUrl;
  private readonly baseUrl = `${this.apiUrl}/conteudos`;

  listarTodos(): Observable<Conteudo[]> {
    return this.http.get<Conteudo[]>(this.baseUrl)
      .pipe(catchError(this.handleError));
  }

  listarPorUsuario(usuarioId: string): Observable<Conteudo[]> {
    return this.http.get<Conteudo[]>(`${this.baseUrl}?usuarioId=${usuarioId}`)
      .pipe(catchError(this.handleError));
  }

  buscarPorId(id: string): Observable<Conteudo> {
    return this.http.get<Conteudo>(`${this.baseUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  criar(request: CriarConteudoRequest): Observable<CriarConteudoResponse> {
    return this.http.post<CriarConteudoResponse>(this.baseUrl, request)
      .pipe(catchError(this.handleError));
  }

  listarEpisodios(conteudoId: string): Observable<Episodio[]> {
    return this.http.get<Episodio[]>(`${this.baseUrl}/${conteudoId}/episodios`)
      .pipe(catchError(this.handleError));
  }

  gerarEpisodio(conteudoId: string, request: CriarEpisodioRequest): Observable<Episodio> {
    return this.http.post<Episodio>(`${this.baseUrl}/${conteudoId}/episodios`, request)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Erro desconhecido';
    
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Erro: ${error.error.message}`;
    } else {
      const apiError = error.error as ApiError;
      errorMessage = apiError?.erro || `Erro ${error.status}: ${error.message}`;
    }
    
    return throwError(() => new Error(errorMessage));
  }
}