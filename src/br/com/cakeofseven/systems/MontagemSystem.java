package br.com.cakeofseven.systems;

import br.com.cakeofseven.components.BoloComponent;
import br.com.cakeofseven.components.ReceitaComponent;

public class MontagemSystem {

    /**
     * Processa a ação do jogador na esteira de montagem.
     * * @param comando O comando digitado ou clicado (ex: "1", "2", "DESFAZER", "PROXIMO")
     * @param bolo O componente que guarda a pilha do bolo atual
     * @param receitas O componente que guarda o livro de receitas
     */
    public void processarInput(String comando, BoloComponent bolo, ReceitaComponent receitas) {
        // Tratamento para evitar NullPointerException caso os componentes não existam
        if (bolo == null || receitas == null) return;

        // Limpa espaços em branco e coloca em maiúsculo para evitar erros de digitação
        String acao = comando.trim().toUpperCase();

        switch (acao) {
            case "DESFAZER":
                // Desempilha o último ingrediente colocado por engano
                int removido = bolo.camadasDoBolo.desempilhar();
                if (removido != -1) {
                    System.out.println("-> [Sistema] Ingrediente ID " + removido + " removido do topo.");
                } else {
                    System.out.println("-> [Sistema] O bolo já está vazio! Nada para desempilhar.");
                }
                break;

            case "PROXIMO":
                // Navega para a próxima receita na lista duplamente encadeada
                receitas.proximaReceita();
                System.out.println("-> [Sistema] Olhando receita: " + receitas.receitaFocadaNaUI.nomeReceita);
                break;

            case "ANTERIOR":
                // Volta para a receita anterior na lista duplamente encadeada
                receitas.receitaAnterior();
                System.out.println("-> [Sistema] Olhando receita: " + receitas.receitaFocadaNaUI.nomeReceita);
                break;

            default:
                // Se não for um comando de controle, tenta interpretar como um ID de ingrediente
                try {
                    int idIngrediente = Integer.parseInt(acao);

                    // Regra de negócio básica: não faz sentido colocar recheio sem a fôrma (ID 1)
                    if (bolo.camadasDoBolo.getTamanho() == 0 && idIngrediente != 1) {
                        System.out.println("[Erro] Você precisa colocar a Fôrma (ID 1) primeiro!");
                        return;
                    }

                    bolo.camadasDoBolo.empilhar(idIngrediente);
                    System.out.println("-> [Sistema] Ingrediente ID " + idIngrediente + " empilhado com sucesso.");

                } catch (NumberFormatException e) {
                    System.out.println("[Alerta] Comando inválido! Digite um ID numérico ou DESFAZER/PROXIMO/ANTERIOR.");
                }
                break;
        }
    }
}