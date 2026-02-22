import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CriarConteudoRequest } from '../../models/conteudo.model';
import { ConteudoService } from '../../services/conteudo.service';

@Component({
  selector: 'app-novo-conteudo',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './novo-conteudo.component.html',
  styleUrl: './novo-conteudo.component.scss'
})
export class NovoConteudoComponent {
  private readonly router = inject(Router);
  private readonly conteudoService = inject(ConteudoService);

  readonly USUARIO_PADRAO = '00000000-0000-0000-0000-000000000001';
  
  titulo = '';
  urlOrigem = '';
  textoOriginal = '';
  enviando = false;
  erro: string | null = null;

  salvar(): void {
    if (!this.urlOrigem) {
      this.erro = 'URL de origem e obrigatoria.';
      return;
    }

    this.enviando = true;
    this.erro = null;

    const request: CriarConteudoRequest = {
      usuarioId: this.USUARIO_PADRAO,
      titulo: this.titulo || null,
      urlOrigem: this.urlOrigem,
      textoOriginal: this.textoOriginal || null
    };

    this.conteudoService.criar(request).subscribe({
      next: (result) => {
        this.router.navigate(['/conteudos', result.conteudo.id]);
      },
      error: (error) => {
        this.erro = error.message;
        this.enviando = false;
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/conteudos']);
  }
}