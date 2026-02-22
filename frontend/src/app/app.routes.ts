import { Routes } from '@angular/router';
import { ListaConteudosComponent } from './features/conteudos/pages/lista-conteudos/lista-conteudos.component';
import { DetalheConteudoComponent } from './features/conteudos/pages/detalhe-conteudo/detalhe-conteudo.component';
import { NovoConteudoComponent } from './features/conteudos/pages/novo-conteudo/novo-conteudo.component';

export const routes: Routes = [
  { path: '', redirectTo: '/conteudos', pathMatch: 'full' },
  { path: 'conteudos', component: ListaConteudosComponent },
  { path: 'conteudos/:id', component: DetalheConteudoComponent },
  { path: 'novo-conteudo', component: NovoConteudoComponent },
  { path: '**', redirectTo: '/conteudos' }
];