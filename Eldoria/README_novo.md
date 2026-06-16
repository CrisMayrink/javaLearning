# Eldoria - Sistema de Batalha RPG com Polimorfismo

Este projeto demonstra conceitos fundamentais de **programação orientada a objetos**, **polimorfismo**, **herança**, **collections** e **interfaces comparáveis** através de um sistema de batalha entre personagens de RPG em um mundo fictício chamado Eldoria.

## 📋 Estrutura do Projeto

### Classe Base Abstrata

#### **PersonagemSClass.java** (`entities`)
Classe abstrata que define a estrutura de todos os personagens:

- **Atributos protegidos:**
  - `nome`: Identificação do personagem
  - `nivel`: Nível de experiência (1-10+)
  - `poderBase`: Poder fundamental do personagem

- **Implementações de Interface:**
  - `Comparable<Personagem>`: Permite comparar personagens por nível

- **Métodos abstratos (implementados pelas subclasses):**
  - `calcularPoderTotal()`: Calcula poder total com bonificadores específicos
  - `usarHabilidade()`: Ativa habilidade especial do personagem
  - `exibirStatus()`: Exibe informações formatadas

- **Métodos concretos:**
  - **Setters com validação:** Garantem integridade dos dados
    - `setNome()`: Valida string não-nula e não-vazia
    - `setNivel()`: Valida nível > 0
    - `setPoderBase()`: Valida poder base > 0
  - **Getters:** Acesso seguro aos atributos
  - `compareTo()`: Compara personagens por nível
  - `toString()`: Representação em string

```java
public abstract class Personagem implements Comparable<Personagem> {
    protected String nome;
    protected int nivel;
    protected int poderBase;
    
    public abstract int calcularPoderTotal();
    public abstract void usarHabilidade();
    public abstract void exibirStatus();
    
    @Override
    public int compareTo(Personagem outro) {
        return Integer.compare(this.nivel, outro.nivel);
    }
}
```

### Subclasses Especializadas

#### **Guerreiro.java** (`entities`)
Especialista em combate corpo-a-corpo:
- **Cálculo de poder:** `(nivel * poderBase) + 30` (bônus fixo de força)
- **Habilidade:** "Golpe Mortal" - ataque devastador
- **Especialidade:** Maior poder base, bônus fixo

```java
public class Guerreiro extends Personagem {
    @Override
    public int calcularPoderTotal() {
        return (nivel * poderBase) + 30;
    }
    
    @Override
    public void usarHabilidade() {
        System.out.println(nome + " -  Usar Golpe mortal!");
    }
}
```

#### **Mago.java** (`entities`)
Especialista em magia:
- **Cálculo de poder:** `(nivel * poderBase) + 50` (bônus fixo de magia)
- **Habilidade:** "Névoa Negra" - feitiço defensivo/ofensivo
- **Especialidade:** Maior bônus de poder total (+50)

```java
public class Mago extends Personagem {
    @Override
    public int calcularPoderTotal() {
        return (nivel * poderBase) + 50;
    }
    
    @Override
    public void usarHabilidade() {
        System.out.println(nome + " - Usar névoa negra!");
    }
}
```

#### **Arqueiro.java** (`entities`)
Especialista em combate à distância:
- **Cálculo de poder:** `(nivel * poderBase) + (nivel * 5)` (bônus escalonado)
- **Habilidade:** "Flecha Fantasma" - projétil mágico
- **Especialidade:** Bônus escalável com nível (5 pontos por nível)

```java
public class Arqueiro extends Personagem {
    @Override
    public int calcularPoderTotal() {
        return (nivel * poderBase) + (nivel * 5);
    }
    
    @Override
    public void usarHabilidade() {
        System.out.println(nome + " - Usar flexa fantasma!");
    }
}
```

### Gerenciamento de Grupos

#### **Grupo.java** (`entities`)
Classe para gerenciar coleções de personagens:

- **Estrutura interna:**
  - `ArrayList<Personagem>`: Lista dinâmica de membros

- **Métodos principais:**
  - `adicionarPersonagem()`: Adiciona com validação nula
  - `listarPersonagens()`: Exibe todos com poder total
  - `getMembros()`: Acesso à lista
  - `setMembros()`: Define nova lista com validação
  - `batalhar()`: Comparação de poder entre dois personagens
  - `ordenarPorNivel()`: Ordena usando Collections.sort() e Comparable

```java
public class Grupo {
    private ArrayList<Personagem> membros;
    
    public void adicionarPersonagem(Personagem p) {
        if (p != null) {
            membros.add(p);
        }
    }
    
    public void batalhar(Personagem a, Personagem b) {
        int poderA = a.calcularPoderTotal();
        int poderB = b.calcularPoderTotal();
        
        if (poderA > poderB) {
            System.out.println(a.getNome() + " venceu!");
        } else if (poderB > poderA) {
            System.out.println(b.getNome() + " venceu!");
        } else {
            System.out.println("Empate!");
        }
    }
    
    public void ordenarPorNivel() {
        Collections.sort(membros);
    }
}
```

### Sistema de Combate

#### **Arena.java** (`entities`)
Classe responsável por organizar batalhas entre grupos:

- **Método principal:**
  - `batalharGrupos()`: Realiza combates 1v1 entre membros de dois grupos
    - Limita batalhas ao tamanho do menor grupo
    - Compara personagens em ordem de índice
    - Delega cálculo para `Grupo.batalhar()`

```java
public class Arena {
    public void batalharGrupos(Grupo g1, Grupo g2) {
        int tamanho = Math.min(g1.getMembros().size(), g2.getMembros().size());
        
        System.out.println("Iniciando batalhas entre grupos...");
        
        for (int i = 0; i < tamanho; i++) {
            Personagem p1 = g1.getMembros().get(i);
            Personagem p2 = g2.getMembros().get(i);
            
            System.out.println("Batalha " + (i + 1) + ": " + p1.getNome() + " vs " + p2.getNome());
            g1.batalhar(p1, p2);
            System.out.println("-------------------------");
        }
    }
}
```

### Programa Principal

#### **Principal.java** (`studingsistweb.desafio`)
Classe main que demonstra o sistema completo:

**Fluxo de execução:**

1. **Criação do Grupo 1:**
   - Mago "Mayrink" (nível 10, poder base 30)
   - Arqueiro "Arthur" (nível 7, poder base 25)

2. **Criação do Grupo 2:**
   - Guerreiro "Lector" (nível 8, poder base 40)
   - Mago "Kovalic" (nível 9, poder base 28)

3. **Listagem de Personagens:** Exibe todos com poder total

4. **Batalhas na Arena:** Executa 2 combates (Mayrink vs Lector, Arthur vs Kovalic)

5. **Demonstração de Métodos:**
   - `exibirStatus()`: Mostra informações de cada personagem
   - `usarHabilidade()`: Demonstra habilidades especiais

```java
public class Desafio {
    public static void main(String[] args) {
        // Criação e adição de personagens
        Grupo grupo1 = new Grupo();
        grupo1.adicionarPersonagem(new Mago("Mayrink", 10, 30));
        grupo1.adicionarPersonagem(new Arqueiro("Arthur", 7, 25));
        
        // Batalhas e demonstrações
        Arena arena = new Arena();
        arena.batalharGrupos(grupo1, grupo2);
    }
}
```

## 🎯 Conceitos Demonstrados

### 1️⃣ Herança Polimórfica
- Classe abstrata `Personagem` define contrato
- Subclasses `Guerreiro`, `Mago`, `Arqueiro` implementam comportamentos específicos
- Mesmo método `calcularPoderTotal()` com lógicas diferentes

### 2️⃣ Polimorfismo em Tempo de Execução (Runtime)
```java
Personagem p1 = new Mago("Mayrink", 10, 30);
Personagem p2 = new Guerreiro("Lector", 8, 40);
// p1 e p2 são Personagem, mas invocam métodos específicos
```

### 3️⃣ Interface Comparable
- Implementação de `compareTo()` para ordenação
- Integração com `Collections.sort()`

### 4️⃣ Collections (ArrayList)
- Armazenamento dinâmico de objetos
- Iteração com enhanced for loop
- Operações de busca e ordenação

### 5️⃣ Validação e Encapsulamento
- Setters com validação de entrada
- Proteção contra dados inválidos
- Getters controlados

### 6️⃣ Abstração
- Métodos abstratos forçam implementação nas subclasses
- Classe abstrata não pode ser instanciada diretamente

### 7️⃣ Métodos toString e Comparable
- Override para representação customizada
- Facilita debugging e exibição

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac Eldoria/entities/*.java Eldoria/studingsistweb/desafio/*.java

# Executar o programa principal
java studingsistweb.desafio.Desafio
```

**Saída esperada:**
```
=== Grupo 1 ===
Personagens do grupo:
Mayrink (Nível:  10, Poder Base: 30) | Poder Total: 380
Arthur (Nível:  7, Poder Base: 25) | Poder Total: 210

=== Grupo 2 ===
Personagens do grupo:
Lector (Nível:  8, Poder Base: 40) | Poder Total: 350
Kovalic (Nível:  9, Poder Base: 28) | Poder Total: 302

=== Batalhas na Arena ===
Iniciando batalhas entre grupos...
Batalha 1: Mayrink vs Lector
Mayrink venceu! Poder total: 380
-------------------------
Batalha 2: Arthur vs Kovalic
Covalic venceu! Poder total: 302
-------------------------

=== Teste com exibirStatus() ===
Mago:  Mayrink Nivel: 10 Poder Base: 30 Poder Total: 380
Arqueiro:  Arthur Nivel: 7 Poder Base: 25 Poder Total: 210
Guerreiro:  Lector Nivel: 8 Poder Base: 40 Poder Total: 350
Mago:  Kovalic Nivel: 9 Poder Base: 28 Poder Total: 302

=== Teste com usarHabilidade() ===
Mayrink - Usar névoa negra!
Arthur - Usar flexa fantasma!
Lector -  Usar Golpe mortal!
Kovalic - Usar névoa negra!
```

## 📊 Cálculo de Poder Total por Classe

| Classe | Fórmula | Exemplo (Nível 10, Base 30) | Bônus |
|--------|---------|------------------------------|-------|
| **Guerreiro** | `(nível × base) + 30` | (10 × 30) + 30 = **330** | +30 fixo |
| **Mago** | `(nível × base) + 50` | (10 × 30) + 50 = **350** | +50 fixo |
| **Arqueiro** | `(nível × base) + (nível × 5)` | (10 × 30) + (10 × 5) = **350** | +5 por nível |

## 📁 Estrutura de Arquivos

```
Eldoria/
├── entities/
│   ├── PersonagemSClass.java    (classe abstrata base)
│   ├── Guerreiro.java           (subclasse especializada)
│   ├── Mago.java                (subclasse especializada)
│   ├── Arqueiro.java            (subclasse especializada)
│   ├── Grupo.java               (gerenciador de coleção)
│   └── Arena.java               (sistema de combate)
├── studingsistweb/desafio/
│   └── Principal.java           (programa principal)
└── README.md                    (este arquivo)
```

## 📝 Notas de Aprendizado

- **Herança vs Composição:** Este projeto usa herança (é-um); use composição (tem-um) quando apropriado
- **DRY Principle:** Código comum em `Personagem` evita repetição
- **SOLID - Open/Closed:** Fácil adicionar novos tipos de personagens sem modificar código existente
- **Polimorfismo:** Permite tratar todos os personagens uniformemente
- **Collections Framework:** `ArrayList` é mais flexível que arrays simples
- **Comparable:** Permite ordenação natural de objetos

## 🎮 Extensões Possíveis

1. **Adicionar novos tipos de personagens:**
   - Paladino: `(nível × poderBase) + 40`
   - Assassino: `(nível × poderBase) + (nível × 7)`

2. **Melhorias no sistema de combate:**
   - Pontos de vida (HP)
   - Atributos secundários (defesa, esquiva)
   - Sistema de turnos

3. **Persistência:**
   - Salvar/carregar personagens em arquivo
   - Banco de dados de campeões

4. **Interface gráfica:**
   - Visualização de batalhas
   - Menu de criação de personagens

## 🔄 Padrões de Design Utilizados

- **Template Method:** Classe abstrata define estrutura, subclasses preenchem detalhes
- **Strategy:** Diferentes estratégias de cálculo de poder (Guerreiro, Mago, Arqueiro)
- **Factory Pattern:** Potencial para criar personagens dinamicamente

---

**Criado em:** javaLearning  
**Tipo:** Exemplo Educacional - RPG com Polimorfismo  
**Conceitos:** Herança, Polimorfismo, Classes Abstratas, Interfaces, Collections, ArrayList, Comparable
