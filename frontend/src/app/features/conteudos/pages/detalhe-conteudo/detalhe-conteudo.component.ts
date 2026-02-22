import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { Conteudo } from '../../models/conteudo.model';
import { Episodio } from '../../models/episodio.model';
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
  episodios: Episodio[] = [];
  carregando = false;
  carregandoEpisodios = false;
  erro: string | null = null;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.carregarConteudo(id);
      this.carregarEpisodios(id);
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

  carregarEpisodios(conteudoId: string): void {
    this.carregandoEpisodios = true;

    this.conteudoService.listarEpisodios(conteudoId).subscribe({
      next: (dados) => {
        this.episodios = dados;
        this.carregandoEpisodios = false;
      },
      error: () => {
        this.carregandoEpisodios = false;
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

  formatarDuracao(segundos: number | null): string {
    if (!segundos) return '-';
    const min = Math.floor(segundos / 60);
    const seg = segundos % 60;
    return `${min}:${seg.toString().padStart(2, '0')}`;
  }
}