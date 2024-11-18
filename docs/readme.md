## POO_GX - PROJECT NAME

> Include here one or two paragraphs explaining the main idea of the project, followed by a sentence identifying who the authors are.

**Example**:

In this exciting platform game you can help KangarooBoy save the world, by collecting all the coins throughout ten different levels in which you will […].

This project was developed by *John Doe* (*john.doe*@umaia.pt) and *Jane Doe* (*jane.doe*@umaia.pt) for POO 2023⁄24.

### IMPLEMENTED FEATURES

> This section should contain a list of implemented features and their descriptions. In the end of the section, include two or three screenshots that illustrate the most important features.



**Examples**:

- **Jumping** - The game character will jump when the space bar key is pressed.
- **Getting hidden coins** - When the game character hits a platform from below (by jumping beneath it) it will smash that segment of the platform and will get any coins that may exist hidden there.

### PLANNED FEATURES

> This section is similar to the previous one but should list the features that are not yet implemented. Instead of screenshots you should include GUI mock-ups for the planned features.

UNCIONALIDADE DO JOGO EM MODO CLÁSSICO
O Modo Clássico é a essência do jogo Snake e oferece uma experiência simples
Aqui estão os principais detalhes e funcionalidades deste modo:
Objetivo do Jogo
O objetivo principal do Modo Clássico é controlar a cobra, fazendo-a crescer ao comer alimentos (geralmente representados por maçãs ou itens similares) enquanto evita colidir com as paredes e com o próprio corpo da cobra.
O jogo termina quando a cobra colide com um obstáculo (como uma parede) ou com ela própria (quando a cauda toca a cabeça).
Controlo
Setas Direcionais: O jogador controla a direção da cobra (cima, baixo, esquerda ou direita) usando as teclas de movimento.
A cobra continua a mover-se na direção indicada até que o jogador mude de direção.
Mecânica do Jogo
Crescimento da Cobra:
Cada vez que a cobra come um alimento (geralmente uma maçã ou item equivalente), ela cresce em tamanho. Isso torna o jogo mais difícil à medida que o jogador precisa de mais espaço para se movimentar.
Velocidade Progressiva:
A velocidade da cobra aumenta conforme o jogo avança. Isso torna o controle mais difícil à medida que o tempo passa, forçando o jogador a ser mais ágil e preciso.
Limites da Arena:
A arena do jogo é cercada por paredes. Se a cobra colidir com uma dessas paredes, o jogo termina.
Colisão com a Própria Cobra:
Se a cobra colidir com seu próprio corpo (ou seja, a cabeça toca em qualquer parte da cauda), o jogo também termina.
Pontos:
O jogador ganha pontos toda vez que a cobra come um alimento. Quanto mais alimentos a cobra comer, maior será a sua pontuação.
Dificuldade Progressiva:
À medida que a cobra cresce, o jogo torna-se mais difícil, uma vez que a área disponível para manobras diminui.
A velocidade também tende a aumentar conforme o jogador progride, tornando a navegação mais desafiadora.
Funções e Características
Pontuação:
A pontuação é calculada com base no número de alimentos que a cobra come. Cada alimento consumido vale uma quantidade de pontos.
O jogador pode tentar alcançar a maior pontuação possível, o que é registado no quadro de líderes.
Modo de Jogo Simples:
Não há elementos externos como power-ups ou obstáculos móveis neste modo, o foco está apenas em controlar a cobra e evitar colisões.
Ecrã Fim de Jogo:
Quando o jogo termina, seja por colisão com uma parede ou com o próprio corpo, é exibida um ecrã de fim de jogo com a pontuação final. O jogador pode optar por jogar novamente.
Erã Inicial e Menus:
O jogo começa com uma tela inicial simples, onde o jogador pode iniciar o jogo ou aceder o menu de configurações. A navegação no menu é feita com as teclas direcionais.
Exemplo de Fluxo de Jogo
Início: O jogador começa com uma cobra de tamanho pequeno no centro da tela.
Crescimento: À medida que a cobra come alimentos que aparecem aleatoriamente na tela, ela cresce.
Aumento de Dificuldade: Com o crescimento da cobra, a velocidade aumenta.
Desafio: O espaço para manobras vai diminuindo, tornando mais difícil evitar colisões.
Fim do Jogo: O jogo termina quando a cobra bate numa parede ou colide com a sua própria cauda.
Visuais e Estilo
Design Simples e Retro: O estilo visual é geralmente simples, com gráficos retro, que são típicos do jogo Snake original. A cobra é representada por um conjunto de quadrados ou segmentos, e o alimento por um pequeno ícone (como uma maçã ou cubo).
Cor da Cobra: Normalmente, a cobra começa com uma cor única (por exemplo, verde), mas em versões mais modernas, pode haver opções de personalização, como cores e skins diferentes.
Exemplo de Gameplay no Modo Clássico
Ecrã do Jogo:  A comida aparece aleatoriamente e o objetivo do jogador é capturá-la sem colidir com as bordas ou com a sua própria cauda.
Pontuação: A pontuação é exibida em um canto da tela e aumenta conforme a cobra come mais alimentos.
Conclusão
O Modo Clássico do jogo Snake é uma experiência nostálgica que combina simplicidade e desafio. Ele é perfeito para quem procura uma jogabilidade direta e viciante, com o objetivo de alcançar a maior pontuação possível enquanto controla uma cobra crescente.

### DESIGN

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:

- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

**Example of one of such subsections**:

------

#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE

**Problem in Context**

There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game (an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.). This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.

**The Pattern**

We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].

**Implementation**

The following figure shows how the pattern’s roles were mapped to the application classes.

![img](https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg)

These classes can be found in the following files:

- [Character](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/Character.java)
- [JumpAbilityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/JumpAbilityState.java)
- [DoubleJumpState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/DoubleJumpState.java)
- [HelicopterState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/HelicopterState.java)
- [IncreasedGravityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/IncreasedGravityState.java)

**Consequences**

The use of the State Pattern in the current design allows the following benefits:

- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.
- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

**Example of such a subsection**:

------

#### DATA CLASS

The `PlatformSegment` class is a **Data Class**, as it contains only fields, and no behavior. This is problematic because […].

A way to improve the code would be to move the `isPlatformSegmentSolid()` method to the `PlatformSegment` class, as this logic is purely concerned with the `PlatformSegment` class.

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.

**Example**:

- John Doe: 40%
- Jane Doe: 60%
