import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Conteudo } from '../../models/conteudo.model';
import { ConteudoService } from '../../services/conteudo.service';

@Component({
  selector: 'app-lista-conteudos',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './lista-conteudos.component.html',
  styleUrl: './lista-conteudos.component.scss'
})
export class ListaConteudosComponent implements OnInit {
  private readonly conteudoService = inject(ConteudoService);
  
  conteudos: Conteudo[] = [];
  carregando = false;
  erro: string | null = null;

  ngOnInit(): void {
    this.carregarConteudos();
  }

  carregarConteudos(): void {
    this.carregando = true;
    this.erro = null;
    
    this.conteudoService.listarTodos().subscribe({
      next: (dados) => {
        this.conteudos = dados;
        this.carregando = false;
      },
      error: (error) => {
        this.erro = error.message;
        this.carregando = false;
      }
    });
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

  formatarData(data: string): string {
    return new Date(data).toLocaleString('pt-BR');
  }
}