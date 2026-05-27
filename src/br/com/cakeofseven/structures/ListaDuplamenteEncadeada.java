package br.com.cakeofseven.structures;

public class ListaDuplamenteEncadeada {

    // Estrutura interna do Nó
    public static class No {
        public String nomeReceita;
        public int[] receitaEsperada; // Array contendo a sequência exata de IDs que o bolo deve ter
        public No proximo;
        public No anterior;

        public No(String nomeReceita, int[] receitaEsperada) {
            this.nomeReceita = nomeReceita;
            this.receitaEsperada = receitaEsperada;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private No cabeca; // Início da lista (Primeira receita)
    private No cauda;  // Fim da lista (Última receita)

    public ListaDuplamenteEncadeada() {
        this.cabeca = null;
        this.cauda = null;
    }

    // Adiciona uma nova receita no final do livro
    public void adicionarReceita(String nome, int[] sequenciaIngredientes) {
        No novoNo = new No(nome, sequenciaIngredientes);

        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.proximo = novoNo;
            novoNo.anterior = cauda;
            cauda = novoNo;
        }
    }

    public No getPrimeira() {
        return cabeca;
    }

    public No getUltima() {
        return cauda;
    }
}