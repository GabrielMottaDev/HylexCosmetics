# HylexCosmetics
Projeto com Cosmetics/Engenhocas para o servidor de Minecraft Hylex.

## Balões
![Alt Text](https://github.com/GabrielMottaDev/HylexCosmetics/raw/main/docs/baloes.gif)

**Proposta**: O cosmetic irá spawnar vários balões, que estarão subindo de baixo do mapa até o céu.

**Solução**: Utilizando entidades invisíveis e blocos coloridos foi possível criar o efeito dos balões.
Para isso dividi os balões em 2 tipos:
- Pequeno: Uma armor stand invisível que utiliza o bloco como capacete.
- Grande: Uma giant zombie invisível segurando o bloco em sua mão.

## Pula-Pula
![Alt Text](https://github.com/GabrielMottaDev/HylexCosmetics/raw/main/docs/pula-pula.gif)

**Proposta**: O cosmetic montará um pula-pula no local onde o jogador escolher, todos que subirem nele serão impulsionados para cima.

**Solução**: Utilizando uma forma de serialização, foi possível salvar e carregar os blocos da estrutura que compõem o pula-pula a qualquer momento.
Para a parte do pulo, apenas foi necessário verificar a existencia de jogadores na área ao redor da estrutura, e se o blocos abaixo de cada um era correspondente ao bloco representante da cama elástica (no caso, o bloco de lã).

## Fóssil
![Alt Text](https://github.com/GabrielMottaDev/HylexCosmetics/raw/main/docs/fossil.gif)

**Proposta**: O cosmetic dará ao jogador a habilidade de transformar outro jogador em um esqueleto durante um curto período de tempo.

**Solução**: Utilizando uma técnica de manipulação e interceptação de pacotes foi possível reescrever as ações do jogador para a de outra entidade (no caso, o esqueleto), mantendo todas as ações de movimento e interação.
