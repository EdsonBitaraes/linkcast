import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { Conteudo } from '../../models/conteudo.model';
import { Episodio, CriarEpisodioRequest } from '../../models/episodio.model';
import { ConteudoService } from '../../services/conteudo.service';

@Component({
  selector: 'app-detalhe-conteudo',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './detalhe-conteudo.component.html',
  styleUrl: './detalhe-conteudo.component.scss'
})
export class DetalheConteudoComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly conteudoService = inject(ConteudoService);

  conteudo: Conteudo | null = null;
  episodio: Episodio | null = null;
  carregando = false;
  gerandoEpisodio = false;
  erro: string | null = null;
  mostrarFormEpisodio = false;
  tituloEpisodio = '';
  descricaoEpisodio = '';

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.carregarConteudo(id);
    }
  }

  carregarConteudo(id: string): void {
    this.carregando = true;
    this.erro = null;

    this.conteudoService.buscarPorId(id).subscribe({
      next: (dados) => {
        this.conteudo = dados;
        this.carregando = false;
      },
      error: (error) => {
        this.erro = error.message;
        this.carregando = false;
      }
    });
  }

  podeGerarEpisodio(): boolean {
    return this.conteudo?.status !== 'ERRO';
  }

  abrirFormEpisodio(): void {
    this.mostrarFormEpisodio = true;
    this.tituloEpisodio = this.conteudo?.titulo || '';
    this.descricaoEpisodio = '';
  }

  cancelarEpisodio(): void {
    this.mostrarFormEpisodio = false;
    this.tituloEpisodio = '';
    this.descricaoEpisodio = '';
  }

  gerarEpisodio(): void {
    if (!this.conteudo || !this.podeGerarEpisodio()) return;

    this.gerandoEpisodio = true;
    this.erro = null;

    const request: CriarEpisodioRequest = {
      titulo: this.tituloEpisodio || null,
      descricao: this.descricaoEpisodio || null
    };

    this.conteudoService.gerarEpisodio(this.conteudo.id, request).subscribe({
      next: (episodio) => {
        this.episodio = episodio;
        this.gerandoEpisodio = false;
        this.mostrarFormEpisodio = false;
      },
      error: (error) => {
        this.erro = error.message;
        this.gerandoEpisodio = false;
      }
    });
  }

  voltar(): void {
    this.router.navigate(['/conteudos']);
  }

  getStatusClass(status: string): string {
    const statusClasses: Record<string, string> = {
      'IMPORTADO': 'status-importado',
      'PROCESSANDO': 'status-processando',
      'PRONTO': 'status-pronto',
      'ERRO': 'status-erro'
    };
    return statusClasses[status] || '';
  }

  getStatusEpisodioClass(status: string): string {
    const statusClasses: Record<string, string> = {
      'GERANDO': 'status-gerando',
      'PRONTO': 'status-pronto',
      'FALHA': 'status-erro'
    };
    return statusClasses[status] || '';
  }

  formatarData(data: string): string {
    return new Date(data).toLocaleString('pt-BR');
  }
}