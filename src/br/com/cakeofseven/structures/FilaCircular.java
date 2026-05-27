package br.com.cakeofseven.structures;

public class FilaCircular {
    private int[] dados;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    public FilaCircular(int capacidade) {
        this.capacidade = capacidade;
        this.dados = new int[capacidade];
        this.inicio = 0;
        this.fim = -1;
        this.tamanho = 0;
    }

    // Adiciona um novo pedido/cliente no fim da fila
    public void enfileirar(int idPedido) {
        if (tamanho == capacidade) {
            System.out.println("[Fila] A fila de clientes está completamente cheia!");
            return;
        }
        // Avança o ponteiro 'fim' de forma circular usando o operador resto (%)
        fim = (fim + 1) % capacidade;
        dados[fim] = idPedido;
        tamanho++;
    }

    // Atende o primeiro cliente da fila, removendo-o
    public int desenfileirar() {
        if (estaVazia()) {
            return -1;
        }
        int pedidoAtendido = dados[inicio];
        // Avança o ponteiro 'inicio' de forma circular
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return pedidoAtendido;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }
}