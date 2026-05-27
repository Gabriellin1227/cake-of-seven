package br.com.cakeofseven.components;

import br.com.cakeofseven.structures.PilhaVetor;

public class BoloComponent {
    public PilhaVetor camadasDoBolo;
    public boolean estaPronto;

    public BoloComponent(int maxCamadas) {
        this.camadasDoBolo = new PilhaVetor(maxCamadas);
        this.estaPronto = false;
    }

    /**
     * Método utilitário rápido para limpar a fôrma
     * caso o bolo vá para o lixo ou seja entregue.
     */
    public void resetarBolo(int maxCamadas) {
        this.camadasDoBolo = new PilhaVetor(maxCamadas);
        this.estaPronto = false;
    }
}