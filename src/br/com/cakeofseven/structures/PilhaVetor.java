package br.com.cakeofseven.structures;

public class PilhaVetor {
    private int[] ingredientes;
    private int topo;
    private int capacidade;

    public PilhaVetor(int capacidade) {
        this.capacidade = capacidade;
        this.ingredientes = new int[capacidade];
        this.topo = -1; // -1 significa que a pilha está vazia
    }

    public void empilhar(int idIngrediente) {
        if (topo == capacidade - 1) {
            System.out.println("⚠️ [Pilha] O bolo atingiu a altura máxima permitida!");
            return;
        }
        topo++;
        ingredientes[topo] = idIngrediente;
    }

    // Remove e retorna o ingrediente do topo (Caso o jogador use o DESFAZER)
    public int desempilhar() {
        if (estaVazia()) {
            return -1; // Retorna -1 se não houver nada para desempilhar
        }
        int ingredienteRemovido = ingredientes[topo];
        topo--;
        return ingredienteRemovido;
    }

    // Apenas espia o que está no topo sem remover
    public int espiarTopo() {
        if (estaVazia()) return -1;
        return ingredientes[topo];
    }

    public boolean estaVazia() {
        return topo == -1;
    }

    public int getTamanho() {
        return topo + 1;
    }

    // Método utilitário para o sistema de validação checar os ingredientes internamente
    public int[] getDadosPuros() {
        return this.ingredientes;
    }
}