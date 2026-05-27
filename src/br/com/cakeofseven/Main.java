package br.com.cakeofseven;

import br.com.cakeofseven.core.GameContext;
import br.com.cakeofseven.systems.MontagemSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializa o game context
        GameContext context = new GameContext();

        // 2. Inicializa os sistemas de lógica
        MontagemSystem sistemaMontagem = new MontagemSystem();

        // 3. Loop do Jogo (Console)
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== BEM-VINDO AO CAKE OF SEVEN ===");
        System.out.println("Receita de hoje: " + context.componenteReceitas.receitaFocadaNaUI.nomeReceita);

        while (true) {
            System.out.print("\nDigite o ID do ingrediente ou comando (PROXIMO / DESFAZER / SAIR): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("SAIR")) break;

            // recebe o contexto inteiro e sabe exatamente o que modificar
            sistemaMontagem.processarInput(input, context.componenteBolo, context.componenteReceitas);
        }

        scanner.close();
    }
}