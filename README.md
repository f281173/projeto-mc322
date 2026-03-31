# RPG do Shrek 

**Matéria:** MC322  
**Membros:** Arthur Nascimento de Souza (269451) - Felipe Garcia Prado (281173)



## Visão Geral
Num lugar tão, tão distante, a paz de um pântano bucólico foi interrompida por perigos diversos. Este projeto é uma simulação de combate onde a lógica de programação encontra o universo de Shrek. O objetivo é simples: sobreviver ao ataque de um Dragão montando estratégias a partir de um baralho de habilidades e gerenciando seus recursos com sabedoria.


**Funcionalidades:**
* **Sistema de Turnos:** O jogador e o inimigo alternam ações até que a vida de um chegue a zero.
* **Sistema de Fases:** O turno do jogador é dividido entre "Fase de Compra" e "Fase de Ação", trazendo mecânicas de deckbuilding e card games.
* **Gerenciamento de Energia:** O herói possui uma quantidade limitada de energia por turno para usar cartas.
* **Baralho e Mão Dinâmicos:** As cartas são compradas de um baralho, utilizadas a partir da mão do jogador e embaralhadas novamente ao fim do turno.
* **Deck de Cartas:** Cartas de dano, escudo e efeito com diferentes custos e potências.
* **Mecânica de Escudo:** Proteção que absorve o dano recebido e reseta ao final de cada rodada.
* **Interface via Console:** Interação interativa utilizando a classe Scanner.
* **Musica:** Nesse jogo ispirado no universo de Shrek temos que ao longo da partida músicas dos filmes são tocados.

---

**Sobre cada tipo de Carta implementada:**

- ***Cartas de Dano:*** São cartas com diferentes custos e sua única função é atacar um monstro.Elas voltam para o Baralho após o seu uso.  

- ***Cartas de Escudo:*** São cartas que dão proteção extra para o heroi que a invocou, mas tenha em mente que o escudo é sempre resetado ao final de cada turno  
 mesmo em caso do Heroi não sofrer ataque.
- ***Cartas de Efeito:*** São cartas que têm duração maior que um turno e podem ter seus efeitos usados em qualquer momento da partida.Nesse jogo temos 2 tipos de Efeitos que cartas do tipo efeito podem usar de modo variado para surpeender o inimigo.  
    - **Efeito Veneno:** Esse efeito é caracterizado por gerar um dano de x acúmulos e a cada início de turno diminuir o seu acúmulo em uma unidade. É importante notar que o efeito Veneno deixa de existir se a quantidade de acúmulos atingir o zero e, além disso, cartas diferentes que compartilham esse mesmo efeito podem ser usadas de modo a somar cada acúmulo individual e prolongar o tempo de efeito do veneno e seu dano.  

    - **Efeito Fraqueza:** Esse efeito é caracterizado por diminuir a força do seu oponente em 25% do valor total. Isso significa que qualquer carta usada pelo seu oponente tem uma redução de 25%. O tempo de duração do efeito é equivalente a quantidade de acúmulos e a cada final de turno o acúmulo é reduzido em uma unidade. Aqui vale também a máxima de usar cartas em conjuntos para prolongar o efeito dos acúmulos, mas nesse caso não há o aumento da redução de dano do inimigo.Por fim, ainda sobre ese efeito, vale ressaltar que a dimuição da força é silenciosa do ponto de vista da carta, assim sendo, o efeito final de impacto na vida reduzido é percebido apenas pelo dano na vida e não é mostrado peala carta o real valor retirado.

## Estrutura do Projeto

O projeto é dividido nas seguintes classes principais:

| Classe |Descrição| 
| :--- | :--- | 
| **App** | Ponto de entrada (Main) que gerencia o fluxo da partida e a interface com o usuário. |
| **Herói** | Representa o jogador, gerencia o inventário de cartas, vida, energia e escudo. | 
| **Inimigo** | Entidade autônoma que ataca o herói após o encerramento do turno do jogador. | 
| **CartaDano** | Define o nome, custo de energia e o valor de dano causado ao oponente.|
| **Baralho** | Estrutura responsável por armazenar todas as cartas disponíveis, gerenciar a pilha de compras e embaralhar o deck. |
| **CartaEscudo** | Define a quantidade de proteção fornecida ao herói. |
| **Carta** | Classe/Interface base que define o comportamento padrão de uma carta jogável (Polimorfismo). | 
| **Entidade** | Classe base que define o comportamento padrão de um personagem. |
|**GameManager**|Classe que centraliza todos os eventos do jogo e manipula os estados de cada etapa do jogo |
|**Efeito**| Classe/Interface base que define o comportamento padrão de uma carta jogável do tipo efeito (Polimorfismo).|
|**EfeitoVeneno**| Classe concreta que estende Efeito e implementa o efeito veneno|
|**EfeitoFraqueza**| Classe concreta que estende o efeito e implementa o efeito Fraqueza|
|**Prints**| Classe para implementar as telas durante o jogo|
|**Jogador**| Classe que organiza os heróis para o jogo com múltiplas entidades|
|**Oponente**| Classe que organiza os inimigos e permite o duelo com vários monstros em uma batalha|
|**Dados**|Classe focada em armazenar os dados de inimigos, cartas, efeitos para facilitar a organização|
|**Publisher e Subscriber**| Interfaces importantes para a implementação do padrão Observer |
|**Turno Heroi e Turno Vilão**| Classes para organizar a dinâmica de turnos entre Heróis e vilões ou inimigos|



## Como Jogar 

Ao iniciar o jogo, os embates acontecem em ciclos de turnos. O turno do jogador é dividido em duas etapas principais:

* **1. Fase de compra**

* No início do seu turno, você se depara com a pilha de compras.
* Você pode escolher e comprar até 4 cartas das opções disponíveis no topo do baralho.
* Assim que terminar sua seleção (ou atingir o limite), escolha a opção para avançar para a batalha.


* **2. Fase de ação**
Com as cartas na mão e sua Energia restaurada, você deve decidir sua estratégia:

* **Ver mão:** Lista todas as cartas que você comprou nesta rodada, mostrando seus custos de energia e efeitos.
* **Usar cartas:** Permite selecionar uma carta da mão pelo seu número.  Gasta energia. Se for uma *carta de dano*, o inimigo perderá vida, se for uma *carta de escudo*, o herói ganha proteção extra para o turno atual.
* **Encerrar turno** Finaliza suas ações. As cartas restantes na sua mão são descartadas, o baralho é reembaralhado e o Inimigo realiza o seu ataque.

* ***Dica de Ouro***: O escudo não acumula entre os turnos!

* **Ordem dos ataques:** A ordem dos ataques não é predefinida, mas sim baseada em um atributo velocidade, de modo que o ataque é sempre ordenado da entidade de maior velocidade para a de menor velocidade e, claro, respeita-se a frequẽncia de ataque, de modo que todas as entidades atacam a mesma quantidade de vezes.



## Como Executar

Para rodar o jogo, certifique-se de ter o Java instalado e um terminal com suporte a cores ANSI.


```java

javac *.java
java App
```


