package br.com.cakeofseven.components;

import br.com.cakeofseven.structures.ListaDuplamenteEncadeada;

public class ReceitaComponent {

    public ListaDuplamenteEncadeada livroDeReceitas;
    public ListaDuplamenteEncadeada.No receitaFocadaNaUI;

    public ReceitaComponent() {
        this.livroDeReceitas = new ListaDuplamenteEncadeada();
        this.receitaFocadaNaUI = null;
    }

    /**
     * Avança para a próxima receita no livro (Seta Direita na UI)
     */
    public void proximaReceita() {
        if (receitaFocadaNaUI != null && receitaFocadaNaUI.proximo != null) {
            receitaFocadaNaUI = receitaFocadaNaUI.proximo;
        }
    }

    /**
     * Volta para a receita anterior no livro (Seta Esquerda na UI)
     */
    public void receitaAnterior() {
        if (receitaFocadaNaUI != null && receitaFocadaNaUI.anterior != null) {
            receitaFocadaNaUI = receitaFocadaNaUI.anterior;
        }
    }
}