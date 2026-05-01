<img width="1833" height="953" alt="image" src="https://github.com/user-attachments/assets/b28b8705-65ad-42f3-a4e0-27af0a13eb7c" />

DESAFIO - NIVEL NOVATO - Tema 2 - Abstrações e Mecanismos de Reutilização em Java -
OBS o desafio do link GitHub não está disponivel para acesso.


O que você vai fazer

Você criará um sistema de cadastro e simulação de habilidades para personagens do reino de Eldoria, utilizando conceitos de orientação a objetos com herança, encapsulamento e coleções.

 

O objetivo é criar uma hierarquia de classes para representar diferentes tipos de personagens e utilizar uma lista de personagens para simular suas ações.

Requisitos funcionais

    Crie uma classe abstrata chamada Personagem com os seguintes atributos encapsulados:
     

    nome (String)
     
    classe (String): Ex: Mago, Guerreiro, Curandeiro.
     
    nivel (int)
     
    pontosDeVida (int)
     
    poderBase (double)

 

2. Implemente em personagem:
 

    Um construtor que inicialize todos os atributos.
     
    Um método abstrato usarHabilidade().
     
    Um método exibirStatus(), que imprime todos os atributos com formatação clara.

 

3. Crie, pelo menos, duas subclasses, como:
 

    Mago
     
    Guerreiro

4. No método main, você deverá:
 

    Criar uma lista (ArrayList) de personagem.
     
    Adicionar ao menos dois magos e dois guerreiros à lista.
     
    Percorrer a lista com um laço e, para cada personagem:
     
        Chamar exibirStatus().
         
        Chamar usarHabilidade().
 

Nessas subclasses, implemente a lógica específica do método usarHabilidade().
