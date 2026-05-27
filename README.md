# Documentação de Design Técnico (TDD) — Cake of Seven

**Projeto:** Jogo de simulação de confeitaria inspirado no "Purble Place" (modo de jogo de bolos).  
**Linguagem:** Java 21 (SDK 21)  
**Arquitetura:** ECS (Entity Component System) simplificado com foco em Orientação a Dados (Data-Oriented Design) e uso de Estruturas de Dados Customizadas (desenvolvidas do zero, sem coleções nativas).

---

## 1. Arquitetura Geral do Sistema (ECS)

O projeto adota o padrão Architectural ECS para maximizar a eficiência de memória no Java, evitar chamadas excessivas ao Garbage Collector (GC) através do uso de arrays lineares, e garantir o desacoplamento total entre dados e lógica de estados.

* **GameContext (Mundo):** Atua como o banco de dados em memória que centraliza e gerencia o ciclo de vida global dos componentes.
* **Componentes (Data):** Classes puras (sacos de dados) desprovidas de métodos de propriedade ou processamento complexos, servindo apenas para envelopar as estruturas de dados de cada entidade.
* **Sistemas (Logic):** Classes puras de execução que processam e transformam os dados contidos nos componentes através de iterações em loops.

---

## 2. Tabela de Identificadores Únicos (IDs de Ingredientes)

Para simplificar o processamento na memória física e garantir a alta velocidade de comparação nas operações de validação, todos os elementos e insumos do jogo são representados por IDs numéricos do tipo `int`.

| ID | Categoria | Ingrediente Correspondente (Visual) |
| :---: | :--- | :--- |
| **1** | Fôrma / Base | Fôrma Redonda Padrão (Obrigatório para iniciar qualquer bolo) |
| **2** | Massa | Massa de Baunilha |
| **3** | Massa | Massa de Chocolate |
| **4** | Recheio / Creme | Creme de Morango |
| **5** | Recheio / Creme | Creme de Chocolate |
| **6** | Cobertura | Glacê Branco |
| **7** | Cobertura | Glacê de Chocolate |

---

## 3. Especificação das Estruturas de Dados (Desenvolvidas do Zero)

### A. Pilha (`PilhaVetor`)
* **Mecânica Relacionada:** Montagem física do bolo na esteira de produção.
* Funciona sob o princípio **LIFO (Last In, First Out)**. O último insumo injetado pelas máquinas sobre o bolo será o primeiro elemento removido caso o jogador decida cancelar a última ação através do comando `DESFAZER`.
* **Implementação:** Vetor estático de inteiros com ponteiro indexador `int topo`.
* **Complexidade Temporal:** Inserção (Empilhar) e Remoção (Desempilhar) em tempo constante $O(1)$.

### B. Fila Circular (`FilaCircular`)
* **Mecânica Relacionada:** Gerenciamento e chegada do fluxo de clientes/pedidos.
* Funciona sob o princípio **FIFO (First In, First Out)**. Garante que os clientes sejam atendidos rigorosamente por ordem de chegada.
* **Implementação:** Array com ponteiros de controle `inicio` e `fim`. O operador resto de divisão (`%`) é utilizado para reuso de memória de slots antigos sem necessidade de deslocamento em massa de elementos do array.
* **Complexidade Temporal:** Enfileirar e Desenfileirar rodam em tempo estrito de $O(1)$.

### C. Lista Duplamente Encadeada (`ListaReceitas`)
* **Mecânica Relacionada:** Livro de Receitas do Menu e Interface do Usuário (UI).
* Permite o encadeamento dinâmico de nós onde cada elemento conhece explicitamente seu nó `proximo` e seu nó `anterior`.
* **Mecânica de Jogo:** Essencial para que o jogador use comandos direcionais da interface (como `PROXIMO` e `ANTERIOR`) para avançar à próxima receita ou retroceder à página anterior de forma instantânea.

---

## 4. Mapeamento de Componentes Inicializados

Os componentes vivem encapsulados dentro do pacote `br.com.cakeofseven.components`:

1. **ClientComponent:** Inicializa a instância da `FilaCircular` definindo a capacidade fixa máxima do restaurante para espera.
2. **BoloComponent:** Inicializa a `PilhaVetor` com limite de camadas verticais físicas e gerencia a flag de conclusão (`estaPronto`).
3. **ReceitaComponent:** Contém a referência para a cabeça da `ListaDuplamenteEncadeada` e gerencia o nó ponteiro que dita qual receita está renderizada na tela no frame atual (`receitaFocadaNaUI`).

---

## 5. Próximos Passos de Engenharia

Para a evolução contínua da produção técnica do Cake of Seven, devem ser estruturados os seguintes pacotes secundários:

* Desenvolvimento do `ValidacaoSystem` para desempilhar o bolo final conferindo com o array estático do nó de receita focado.
* Implementação de um mecanismo de tempo no loop para simular a chegada automática de clientes a cada X segundos na fila circular.
* Criação do desacoplamento da camada de texto para renderização gráfica em janelas nativas via Swing ou JavaFX.
