# RPG do Shrek 

**Matéria:** MC322  
**Membros:** Arthur Nascimento de Souza (269451) - Felipe Garcia Prado (281173)



## Visão Geral
Num lugar tão, tão distante, a paz de um pântano bucólico foi interrompida por perigos diversos. Este projeto é uma simulação de combate onde a lógica de programação encontra o universo de Shrek. O objetivo é simples: sobreviver ao ataque de um Dragão usando estratégia a partir de um deck de habilidades poderosas. 



**Funcionalidades:**
* **Sistema de turnos:** O jogador e o inimigo alternam ações até que a vida de um chegue a zero.
* **Gerenciamento de Energia:** O herói possui uma quantidade limitada de energia por turno para usar cartas.
* **Deck de Cartas:** Cartas de dano com diferentes custos e potências.
* **Mecânica de Escudo:** Proteção que absorve o dano recebido e reseta ao final de cada rodada.
* **Interface via Console:** Interação interativa utilizando a classe Scanner.

---


## Estrutura do Projeto

O projeto é dividido nas seguintes classes principais:

| Classe |Descrição| 
| :--- | :--- | 
| **App** | Ponto de entrada (Main) que gerencia o fluxo da partida e a interface com o usuário. |
| **Herói** | Representa o jogador, gerencia o inventário de cartas, vida, energia e escudo. | 
| **Inimigo** | IA simples que ataca o herói após o encerramento do turno do jogador. | 
| **CartaDano** | Define o nome, custo de energia e o valor de dano causado ao oponente.|
| **CartaEscudo** | Define a quantidade de proteção fornecida ao herói. |




## Como Jogar 

Ao iniciar o jogo, você terá um determinado número de pontos de energia por turno. No turno, poderá escolher essas opções:

* **Opção 1 (Usar CartaDano):** Abre seu menu de cartas. Cada carta consome energia. Escolha uma carta.
* **Opção 2 (Usa CartaEscudo):** Use para adicionar um escudo ao personagem. Custa energia. Funciona como um aumento nos pontos de vida.
* **Opção 3 (Encerrar):** Finaliza suas ações e permite que o inimigo ataque. 
* *Dica: O escudo reseta a cada turno, então use-o com sabedoria*




