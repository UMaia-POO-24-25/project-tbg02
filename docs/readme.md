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
- **Colisão com o corpo da cobra**: O jogo termina se a cobra colidir consigo mesma.  
- **Pontuação**: O jogo mantém uma pontuação que aumenta à medida que a cobra come as frutas.  
- **Tela de início e fim**: Tela inicial com as opções de iniciar o jogo e uma tela de fim com a pontuação final exibida.  
- **Dificuldade crescente**: A velocidade da cobra aumenta à medida que a pontuação do jogador sobe.  
- **Modo de reinício**: O jogador pode reiniciar o jogo após ter acabado.  
- **Efeitos sonoros**: Sons para ações importantes, como comer uma fruta ou colidir com um obstáculo.  
- **Menu Iniciar**: Inclui opções de início, pontuações máximas e informações **About**.  
- **Tipos de Items**: Inclui vários tipos de itens a serem recolhidos.  

---

## 🕹️ Examples

- **Movimentação da cobra**  
  A cobra move-se continuamente na direção atual. Você pode alterar sua direção usando as teclas de seta (cima, baixo, esquerda, direita).  

- **Comer comida**  
  Quando a cobra colide com o ícone da comida, ela cresce de tamanho e a pontuação do jogador aumenta. A comida aparece em uma nova posição aleatória.  

- **Game Over ao colidir**  
  O jogo termina se a cobra colidir com as paredes do campo ou com o próprio corpo.  

- **Pontuação crescente**  
  A pontuação aumenta cada vez que a cobra come comida. Quanto mais tempo o jogador sobreviver, maior será a pontuação.  

- **Velocidade crescente**  
  À medida que o jogador avança e a cobra cresce, a velocidade do jogo aumenta, tornando-o mais desafiador.  

---

## 🌟 PLANNED FEATURES

O **Modo Clássico** é a essência do jogo Snake e oferece uma experiência simples e viciante.  

### 🎯 Objetivo do Jogo

O objetivo principal do **Modo Clássico** é controlar a cobra, fazendo-a crescer ao comer alimentos (geralmente representados por maçãs ou itens similares) enquanto evita colidir com as paredes e com o próprio corpo da cobra.  

O jogo termina quando:  
- A cobra colide com um obstáculo (como uma parede).  
- A cobra colide consigo mesma (quando a cauda toca a cabeça).

- Implementaçoes futuras no jogo, quando termina aparece a pontuaçao maxiam de cada jogador 

![Implementaçoes](https://github.com/user-attachments/assets/fb569632-e1a4-4306-9213-132640c3bc33)

---

## UML



---

## 🎮 Controlo

- **Setas direcionais**: O jogador controla a direção da cobra usando as teclas direcionais. A cobra continua a mover-se na direção indicada até que o jogador mude de direção.  

---

## 📋 Mecânica do Jogo

- **Crescimento da Cobra**: Cada vez que a cobra come um alimento, ela cresce em tamanho, dificultando o movimento no campo.  
- **Velocidade Progressiva**: A velocidade da cobra aumenta conforme o jogo avança, tornando o controlo mais desafiador.  
- **Limites da Arena**: Colidir com uma parede termina o jogo.  
- **Colisão com a Própria Cobra**: Se a cobra colidir consigo mesma, o jogo termina.  

---

## 🏆 Pontuação

- O jogador ganha pontos ao consumir alimentos.  
- A pontuação aumenta com o progresso, registando o máximo alcançado.  

---

## 📌 Design

- **Estilo Retro Simples**: Gráficos minimalistas com uma aparência nostálgica.  
- **Ecrã de Jogo**: Inclui as pontuações (atual e máxima) exibidas no canto superior.  

---

## 📈 Fluxo de Jogo

1. **Início**: A cobra começa pequena no centro da tela.  
2. **Crescimento**: A cobra cresce ao comer itens que aparecem aleatoriamente.  
3. **Aumento de Dificuldade**: A velocidade aumenta e o espaço disponível diminui.  
4. **Fim do Jogo**: O jogo termina ao colidir com uma parede ou com a própria cauda.  

---

## 🎨 Exemplo de Gameplay

- **Ecrã Inicial**: Opções de iniciar o jogo e exibição de pontuações máximas.  
- **Durante o Jogo**: A cobra move-se continuamente e coleta itens para aumentar a pontuação.  
- **Ecrã de Fim**: Mostra a pontuação final com a opção de reiniciar.  

---

## 📚 Conclusão

O **Modo Clássico** do jogo Snake oferece uma experiência nostálgica, combinando simplicidade e desafio. É perfeito para quem procura uma jogabilidade direta e viciante, com o objetivo de alcançar a maior pontuação possível.  
