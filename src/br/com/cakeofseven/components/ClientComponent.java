package br.com.cakeofseven.components;

import br.com.cakeofseven.structures.FilaCircular;

public class ClientComponent {
    public FilaCircular filaDePedidos;
    public int capacidadeMaxima;

    public ClientComponent(int capacidade) {
        this.capacidadeMaxima = capacidade;
        this.filaDePedidos = new FilaCircular(capacidade);
    }
}