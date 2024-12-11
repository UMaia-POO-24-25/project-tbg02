# 🐍 POO_GX - SNAKE

O jogo **Snake** é um clássico dos videojogos, onde o jogador controla uma cobra que se move continuamente pela tela. O objetivo é comer a comida que aparece aleatoriamente, o que faz com que a cobra cresça a cada pedaço consumido. O desafio consiste em evitar colisões com as paredes e com o corpo da própria cobra, enquanto tenta alcançar a maior pontuação possível. O jogo torna-se mais difícil à medida que a cobra cresce em tamanho e a sua velocidade aumenta.

Este projeto está implementado em **Java**, proporcionando uma experiência interativa. O jogo inclui controlos suaves, aumento de dificuldade e um sistema de pontuação.

**Este projeto foi implementado por alunos da Licenciatura em Informática:**  
- **Diogo Teixeira**  
- **João Rebelo**  
- **José Cardoso**

---

## ✅ IMPLEMENTED FEATURES

- **Jogo Snake**: O jogador controla uma cobra que se move pela tela, recolhendo frutas e crescendo a cada fruta consumida.  
- **Movimento contínuo**: A cobra move-se continuamente numa direção até que o jogador altere a sua direção.  
- **Controlo de direção**: O jogador pode controlar a direção da cobra utilizando as teclas de seta (cima, baixo, esquerda, direita).  
- **Aumento de tamanho da cobra**: A cobra cresce cada vez que come uma fruta.  
- **Colisão com a parede**: O jogo termina se a cobra colidir com as paredes da tela.  
- **Pontuação**: O jogo mantém uma pontuação que aumenta à medida que a cobra come as frutas. 
- **Tela de início e fim**: Tela inicial com as opções de iniciar o jogo e uma tela de fim com a pontuação final exibida.  
- **Dificuldade crescente**: A velocidade da cobra aumenta à medida que a pontuação do jogador sobe.  
- **Modo de reinício**: O jogador pode reiniciar o jogo após ter acabado.
- **Efeitos sonoros**: Sons para ações importantes, como comer uma fruta ou colidir com um obstáculo.  
- **Tipos de Items**: Inclui vários tipos de itens a serem recolhidos.  

---

## 🕹️ Planned Features

- **Ranking/highscore**: Implementação de highscore e gravar o mesmo, com introdução do nome.
 ![alt text](https://github.com/user-attachments/assets/fb569632-e1a4-4306-9213-132640c3bc33)

- **Colisão com o corpo da cobra**: O jogo termina se a cobra colidir consigo mesma.
  
- **Menu Iniciar**: Inclui opções de início, pontuações máximas e Ranking.
  


--- 

### 🎯 Game Objectives

O objetivo principal do **Modo Clássico** é controlar a cobra, fazendo-a crescer ao comer alimentos (geralmente representados por maçãs ou itens similares) enquanto evita colidir com as paredes e com o próprio corpo da cobra.  

O jogo termina quando:  
- A cobra colide com um obstáculo (como uma parede).  
- A cobra colide consigo mesma (quando a cauda toca a cabeça).

- Implementaçoes futuras no jogo, quando termina aparece a pontuaçao máxima de cada jogador 

---

## UML

### Methods

![alt text](https://github.com/UMaia-POO-24-25/project-tbg02/blob/main/docs/img/methods.jpg)

---

## 🎮 Controls

- **Setas direcionais**: O jogador controla a direção da cobra usando as teclas direcionais. A cobra continua a mover-se na direção indicada até que o jogador mude de direção.  

---

## 📋 game Mechanics

- **Crescimento da Cobra**: Cada vez que a cobra come um alimento, ela cresce em tamanho, dificultando o movimento no campo.  
- **Velocidade Progressiva**: A velocidade da cobra aumenta conforme o jogo avança, tornando o controlo mais desafiador.  
- **Limites da Arena**: Colidir com uma parede termina o jogo.  
- **Colisão com a Própria Cobra**: Se a cobra colidir consigo mesma, o jogo termina.  

---

## 🏆 Score

- O jogador ganha pontos ao consumir alimentos.  
- A pontuação aumenta com o progresso, registando o máximo alcançado.  

---

## 📌 Design

- **Estilo Retro Simples**: Gráficos minimalistas com uma aparência nostálgica.  
- **Ecrã de Jogo**: Inclui as pontuações (atual e máxima) exibidas no canto superior.  

---
## 📌 Design Patterns

### Padrão Singleton:

A classe Som utiliza uma instância estática de Clip, que é uma forma de implementar o padrão Singleton. Isto garante que exista apenas uma instância de Clip a ser usada para a reprodução de som, sendo partilhada por toda a aplicação.

![alt text](https://github.com/UMaia-POO-24-25/project-tbg02/blob/main/docs/img/singleton.jpg)

### Padrão Factory:

A classe DefaultTerminalFactory na GameView é um exemplo do padrão Factory. Este padrão abstrai a criação de objetos terminais, permitindo diferentes configurações sem expor a lógica de instanciamento ao cliente.

### Padrão Observer:

A classe GameView observa os eventos de teclas pressionadas e atualiza o estado do jogo em conformidade. Isto é uma forma de implementar o padrão Observer, onde a vista do jogo observa eventos de entrada e reage a eles.

![alt text](https://github.com/UMaia-POO-24-25/project-tbg02/blob/main/docs/img/observer.png)

### Padrão Command:

O tratamento de teclas pressionadas na classe GameView pode ser interpretado como uma implementação do padrão Command. Cada tecla pressionada corresponde a um comando (ex.: mover para cima, mover para baixo), que é executado para alterar o estado do jogo.

---

## 📈 Game Flow

1. **Início**: A cobra começa pequena no centro da tela.  
2. **Crescimento**: A cobra cresce ao comer itens que aparecem aleatoriamente.  
3. **Aumento de Dificuldade**: A velocidade aumenta e o espaço disponível diminui.  
4. **Fim do Jogo**: O jogo termina ao colidir com uma parede ou com a própria cauda.  

---

## 🎨 Gameplay

- **Ecrã Inicial**: Opções de iniciar o jogo e exibição de pontuações máximas.
  ![alt text](https://github.com/UMaia-POO-24-25/project-tbg02/blob/main/docs/img/menuiniciar.png)
  
- **Durante o Jogo**: A cobra move-se continuamente e coleta itens para aumentar a pontuação.  
  ![alt text](https://github.com/UMaia-POO-24-25/project-tbg02/blob/main/docs/img/ingame.png)

- **Ecrã de Fim**: Mostra a pontuação final com a opção de reiniciar.  
  ![alt text](https://github.com/UMaia-POO-24-25/project-tbg02/blob/main/docs/img/EndGame.png)

---

## Coverage Test

![alt text](https://github.com/UMaia-POO-24-25/project-tbg02/blob/main/docs/img/Coverage1.png)

---

## 📚 Conclusion

O **Modo Clássico** do jogo Snake oferece uma experiência nostálgica, combinando simplicidade e desafio. É perfeito para quem procura uma jogabilidade direta e viciante, com o objetivo de alcançar a maior pontuação possível.  

---

## Self-Evaluation

- Diogo Teixeira: 33,3%
- João Rebelo: 33,3%
- José Cardoso: 33,3%
