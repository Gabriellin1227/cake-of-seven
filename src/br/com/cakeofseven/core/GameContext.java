package br.com.cakeofseven.core;

import br.com.cakeofseven.components.BoloComponent;
import br.com.cakeofseven.components.ClientComponent;
import br.com.cakeofseven.components.ReceitaComponent;

public class GameContext {
    // Declaração dos componentes globais do jogo
    public ClientComponent componenteClientes;
    public BoloComponent componenteBolo;
    public ReceitaComponent componenteReceitas;

    public GameContext() {
        this.componenteClientes = new ClientComponent(5); // Fila para 5 clientes
        this.componenteBolo = new BoloComponent(6);          // Bolo de até 6 camadas
        this.componenteReceitas = new ReceitaComponent();  // Livro de receitas

        // Adiciona as receitas padrões
        this.componenteReceitas.livroDeReceitas.adicionarReceita("Bolo Morango", new int[]{1, 2, 3});
        this.componenteReceitas.livroDeReceitas.adicionarReceita("Bolo Chocolate Supremo", new int[]{1, 4, 4, 3});

        // Define a receita inicial que aparece na tela
        this.componenteReceitas.receitaFocadaNaUI = this.componenteReceitas.livroDeReceitas.getPrimeira();
    }
}