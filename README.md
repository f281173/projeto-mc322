# RPG do Shrek 

**Matéria:** MC322  
**Membros:** Arthur Nascimento de Souza (269451) - Felipe Garcia Prado (281173)



## Visão Geral
Num lugar tão, tão distante, a paz de um pântano bucólico foi interrompida por perigos diversos. Para recuperar o seu pântano, Shrek embarca numa longa jornada, cheia de desafios e decisões que moldam a história. Este projeto é uma simulação de combate onde a lógica de programação encontra o universo de Shrek. O objetivo é simples: sobreviver aos perigos da jornada, montando estratégias a partir de um baralho de habilidades, tomando as decisões certas no mapa e gerenciando seus recursos com sabedoria.


**Funcionalidades:**
* **Sistema de Turnos:** O jogador e o inimigo alternam ações até que a vida de um chegue a zero.
* **Sistema de Dificuldade:** O jogador escolhe o nível de desafio no início do jogo (Fácil, Normal ou Difícil). Essa dificuldade dita a quantidade de monstros em batalhas comuns (ex: no modo Difícil, você pode ser emboscado por 3 inimigos genéricos simultâneos). **Atenção:** Chefões (Bosses) não são afetados pela dificuldade e lutam sozinhos.
* **Sistema de Fases:** O turno do jogador é dividido entre "Fase de Compra" e "Fase de Ação", trazendo mecânicas de deckbuilding e card games.
* **Gerenciamento de Energia:** O herói possui uma quantidade limitada de energia por turno para usar cartas.
* **Baralho e Mão Dinâmicos:** As cartas são compradas de um baralho, utilizadas a partir da mão do jogador e embaralhadas novamente ao fim do turno.
* **Deck de Cartas:** Cartas de dano, dano em área,  escudo e efeito com diferentes custos e potências.
* **Mecânica de Escudo:** Proteção que absorve o dano recebido e reseta ao final de cada rodada.
* **Interface via Console:** Interação interativa utilizando a classe Scanner.
* **Musica:** Nesse jogo ispirado no universo de Shrek temos que ao longo da partida músicas dos filmes são tocados.

---

## O Mapa e os Eventos

Durante a jornada, o jogador navegará por um mapa visual de caminhos interligados. Cada nó do mapa representa um tipo diferente de evento:

* **📍 Você está aqui:** Indica a localização atual da sua equipe.
* **🏠 Início:** O ponto de partida da aventura no pântano.
* **⚔️ Batalhas Comuns:** Embates contra grupos de inimigos genéricos (Aldeões, Lobos, etc.). A quantidade de inimigos varia de 1 a 3 dependendo da dificuldade escolhida.
* **👹 Boss/Chefão:** Lutas intensas que avançam a história contra inimigos únicos, como o Caçador, a Bruxa Velha e o Dragão.
* **📜 Recompensas:** Momentos pacíficos de exploração onde o jogador descobre novas Cartas (habilidades) para fortalecer permanentemente o seu baralho.
* **🍺 Bar/Cura:** Áreas de descanso, onde a equipe pode se curar.
* **🕳️ Armadilha:** Eventos de azar e acidentes pelo caminho que causam danos e prejudicam a equipe.
* **🔀 Decisão/Encruzilhada:** Momentos críticos onde o jogador deve escolher entre dois ou mais caminhos, alterando completamente as batalhas e recompensas da sua rota.
* **❓ Evento Surpresa:**  Pontos do mapa que podem ser recompensas ou armadilhas. Só é revelado quando o jogador se aproxima.
* **💰 Evento Loja:**  Área em que o jogador pode comprar poções. É utilizado o dinheiro ganhado em batalhas.

---





**Sobre cada tipo de Carta implementada:**

- ***Cartas de Dano:*** São cartas com diferentes custos e sua única função é atacar um monstro.Elas voltam para o Baralho após o seu uso. 
- ***Cartas de Dano em Área:*** São cartas com diferentes custos e sua  função é atacar todos os monstros vivos. Elas voltam para o Baralho após o seu uso. 
- ***Cartas de Escudo:*** São cartas que dão proteção extra para o heroi que a invocou, mas tenha em mente que o escudo é sempre resetado ao final de cada turno  
 mesmo em caso do Heroi não sofrer ataque.
- ***Cartas de Efeito:*** São cartas que têm duração maior que um turno e podem ter seus efeitos usados em qualquer momento da partida.Nesse jogo temos 3 tipos de Efeitos que cartas do tipo efeito podem usar de modo variado para surpeender o inimigo.  
    - **Efeito Veneno:** Esse efeito é caracterizado por gerar um dano de x acúmulos e a cada início de turno diminuir o seu acúmulo em uma unidade. É importante notar que o efeito Veneno deixa de existir se a quantidade de acúmulos atingir o zero e, além disso, cartas diferentes que compartilham esse mesmo efeito podem ser usadas de modo a somar cada acúmulo individual e prolongar o tempo de efeito do veneno e seu dano.  

    - **Efeito Fraqueza:** Esse efeito é caracterizado por diminuir a força do seu oponente em diferentes porcentagens do valor total. Isso significa que qualquer carta usada pelo seu oponente tem uma redução de X% (Reduçao de 25%). O tempo de duração do efeito é equivalente a quantidade de acúmulos e a cada final de turno o acúmulo é reduzido em uma unidade. Aqui vale também a máxima de usar cartas em conjuntos para prolongar o efeito dos acúmulos, mas nesse caso não há o aumento da redução de dano do inimigo.Por fim, ainda sobre ese efeito, vale ressaltar que a dimuição da força é silenciosa do ponto de vista da carta, assim sendo, o efeito final de impacto na vida reduzido é percebido apenas pelo dano na vida e não é mostrado pela carta o real valor retirado.

    - **Efeito Força:** Esse efeito é caracterizado por aumentar a força de um de seus Heróis em diferentes porcentagens do valor total. Isso significa que qualquer carta utilizada terá um aumento de X% (Aumento de 25%). O tempo de duração do efeito é equivalente a quantidade de acúmulos e a cada final de turno o acúmulo é reduzido em uma unidade. Aqui vale também a máxima de usar cartas em conjuntos para prolongar o efeito dos acúmulos, mas nesse caso não há uma adição do aumento de dano do herói. Por fim, ainda sobre ese efeito, vale ressaltar que o aumento da força é silencioso do ponto de vista da carta, assim sendo, o efeito final de impacto na vida acrescido é percebido apenas pelo dano na vida do oponente e não é mostrado pela carta o real valor retirado.



## Estrutura do Projeto

O projeto é dividido nas seguintes classes principais:

| Classe |Descrição| 
| :--- | :--- | 
| **App** | Ponto de entrada (Main) que gerencia o fluxo da partida e a interface com o usuário. |
| **Entidade** | Classe base que define o comportamento padrão de um personagem. |
| **Herói** | Representa o jogador, gerencia o inventário de cartas, vida, energia e escudo. | 
| **Inimigo** | Entidade autônoma que ataca o herói após o encerramento do turno do jogador. | 
| **MaoJogador**| Gerencia as cartas que estão fisicamente na mão do herói durante o turno.|
| **Carta** | Classe/Interface base que define o comportamento padrão de uma carta jogável (Polimorfismo). | 
| **CartaDano** | Define o nome, custo de energia e o valor de dano causado ao oponente.|
| **CartaDanoArea** | Define o nome, custo de energia e o valor de dano causado em diversos oponentes.|
| **CartaEscudo** | Define a quantidade de proteção fornecida ao herói. |
| **CartaEfeito** | Defineo tipo de efeito, custo e número de acúmulos. |
| **Baralho** | Estrutura responsável por armazenar as cartas de cada herói, gerenciar a pilha de compras e embaralhar o deck. |
|**Efeito**| Classe/Interface base que define o comportamento padrão de uma carta jogável do tipo efeito (Polimorfismo).|
|**EfeitoVeneno**| Classe concreta que estende Efeito e implementa o efeito veneno|
|**EfeitoFraqueza**| Classe concreta que estende o efeito e implementa o efeito Fraqueza|
|**EfeitoForça**| Classe concreta que estende o efeito e implementa o efeito Força|
| **Evento**| Classe base abstrata para todos os eventos dos nós do mapa |
| **Batalha / EventoLoja / EventoRecompensa**| Classes filhas de Evento que encapsulam as regras específicas de cada sala. |
| **Campanha / NoMapa**| Estrutura de Grafos  que forma o mapa do jogo. |
|**Prints**| Classe para implementar as telas durante o jogo|
|**GameManager**|Classe que centraliza todos os eventos do jogo e manipula os estados de cada etapa do jogo |
|**Jogador**| Classe que organiza os heróis para o jogo com múltiplas entidades|
|**Oponente**| Classe que organiza os inimigos e permite o duelo com vários monstros em uma batalha|
|**Dados**|Classe focada em armazenar os dados de inimigos, cartas, efeitos para facilitar a organização|
|**Publisher e Subscriber**| Interfaces importantes para a implementação do padrão Observer |
|**Turno Heroi e Turno Vilão**| Classes para organizar a dinâmica de turnos entre Heróis e vilões ou inimigos|
|**Dados**| Classe que guarda todas as informações das entidades|
| **Campanha**| Estrutura de Grafos (Nós e Caminhos) que forma o mapa e os eventos do jogo. |
|**Musica**|Gerencia a trilha sonora|


## Como Jogar 

Ao iniciar o jogo, os embates acontecem em ciclos de turnos. O turno do jogador é dividido em duas etapas principais:

* **1. Fase de compra**

* No início do seu turno, você se depara com a pilha de compras.
* Você pode escolher e comprar até 3 cartas das opções disponíveis no topo do baralho.
* Assim que terminar sua seleção (ou atingir o limite), escolha a opção para avançar para a batalha.


* **2. Fase de ação**
Com as cartas na mão e sua Energia restaurada, você deve decidir sua estratégia:

* **Ver mão:** Lista todas as cartas que você comprou nesta rodada, mostrando seus custos de energia e efeitos.
* **Usar cartas:** Permite selecionar uma carta da mão pelo seu número.  Gasta energia. Se for uma *carta de dano*, o inimigo perderá vida, se for uma *carta de escudo*, o herói ganha proteção extra para o turno atual, se efeito, a entidade sofre uma consequência.
* **Encerrar turno** Finaliza suas ações. As cartas restantes na sua mão são descartadas, o baralho é reembaralhado e o Inimigo realiza o seu ataque.

* ***Dica de Ouro***: O escudo não acumula entre os turnos!

* **Ordem dos ataques:** A ordem dos ataques não é predefinida, mas sim baseada em um atributo velocidade, de modo que o ataque é sempre ordenado da entidade de maior velocidade para a de menor velocidade e, claro, respeita-se a frequẽncia de ataque, de modo que todas as entidades atacam a mesma quantidade de vezes. No empate, o herói tem prioridade




## Como Executar

Para rodar o jogo, certifique-se de ter o Java instalado (JDK 17 ou superior recomendado).


```java

./gradlew build
./gradlew run

```



## Como gerar e pegar o HTML do Javadoc

```java

./gradlew javadoc

```
Feito o comando, vá pelo explorador de arquivos até app/build/docs/javadoc/index.html


## Como gerar os testes e pegar o HTML com a porcentagem de cobertura.

```java

./gradlew test

```

Feito o comando, vá pelo explorador de arquivos até app/build/reports/jacoco/test/html/index.html

## Padrões de projeto usados no nosso Jogo

- Padrão Strategy: Usados para que se tenha um conjunto de diferentes comportamentos sem a necessidade 
de modificação de código já existente para futuras expansões de comportamento.  

    1. Foi usado esse padrão no pacote de sistemaAcoes, que define uma interface comum com o método executar para diferentes implementações que 
    define diferentes formas de ações para o inimigo. Veja que nesse caso podemos expandir as ações sem modificar código já existente.  


    2. O principal uso do Strategy está no pacote interfaceUsuario em que estamos usando o padrão para que possa ser executado esse jogo
    tanto no terminal (onde atualmente é a única implementação totalmente funcional) e a execução em uma interface gráfica (estamos ainda implementando esse modo) de modo que todo o nosso jogo usa os métodos dessa interface sem saber qual o modo de execução naquele momento. Embora não exista ainda a implementação
    da interface gráfica por completo, o padrão todo já está implementado de modo que podemos expandir a interface gráfica sem modificar código já existente.

    Fontes:  
    [Site refactoring guru](https://refactoring.guru/pt-br/design-patterns/strategy)  
    [Artigo do Medium](https://medium.com/@eshikashah2001/understanding-the-strategy-design-pattern-in-software-engineering-8774086a1895)

- Padrão DTO (Data Transfer Object): Usado para que seja possível transferir os dados de um objeto de modo simples para que possa ser usado em outras partes do código. Esse tipo de objeto não tem regras de negócio e servem apenas para acumular informações.

    1. Foi usado na classe ResultadoAcao para que as informações sobre a excução de uma determinada ação, como por exemplo, em  qual  entidade foi o alvo, possa
    ser transferido e consumido pela interfaceusuario de modo deiferentes, a depender se será impressa no terminal ou então usada em uma interface gráfica.

    Fontes:  
    [Artigo do Medium com exemplos em java](https://medium.com/@alxkm/the-complete-guide-to-data-transfer-objects-dtos-from-basics-to-enterprise-patterns-fcddd3a6bc9a)  
    [Outro artgo do Medium](https://medium.com/@jigorsilva/entendendo-dtos-em-java-um-guia-completo-para-iniciantes-55e82264918f) 



