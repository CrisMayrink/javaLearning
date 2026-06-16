# DesafioUnidade3 - Evoluindo Caça ao Tesouro com Executor e ForkJoin

Este projeto é uma evolução do simulador de caça ao tesouro que demonstra conceitos avançados de **concorrência**, **ExecutorService**, **Callable**, **Future** e **Fork/Join** em Java.

## 📋 Estrutura do Projeto

### Classes Base

#### **Explorador.java**
Classe abstrata base para todos os tipos de exploradores:
- **Atributos protegidos:**
  - `nome`: Identificação do explorador
  - `especialidade`: Tipo de especialização (Rastreador, Saqueador)
  - `nivel`: Nível de experiência (1-10)
  - `energia`: Nível de energia disponível
  - `missao`: Objeto Missao contendo detalhes da tarefa

- **Métodos:**
  - `executarMissao()`: Método abstrato para executar a missão (retorna Double - pontuação)
  - `subirNivel()`: Incrementa o nível do explorador
  - `toString()`: Exibe informações formatadas do explorador
  - **Getters:** Para acessar todos os atributos

```java
public abstract class Explorador {
    protected String nome;
    protected String especialidade;
    protected int nivel;
    protected int energia;
    protected Missao missao;
    
    public abstract Double executarMissao() throws TarefaInvalidaException;
    public void subirNivel() { nivel++; }
}
```

#### **Missao.java**
Classe imutável que representa uma missão:
- **Atributos finais:**
  - `descricao`: Descrição da tarefa
  - `local`: Local onde será realizada (ex: "Peru")
  - `dificuldade`: Nível de dificuldade (1-10)

```java
public final class Missao {
    private final String descricao;
    private final String local;
    private final int dificuldade;
    
    public Missao(String descricao, String local, int dificuldade) { ... }
}
```

### Subclasses de Explorador

#### **Rastreador.java**
Implementa um explorador especializado em rastreamento:
- Estende `Explorador`
- Implementa `Callable<Double>` para retornar pontuação
- **Cálculo de pontos:** `dificuldade * 2.0 + nivel`
- Validação de missão nula ou vazia
- Método `call()`: Executa a missão de forma thread-safe retornando Double ou 0.0 em caso de erro

```java
public class Rastreador extends Explorador implements Callable<Double> {
    @Override
    public Double executarMissao() throws TarefaInvalidaException {
        if (getMissao() == null || getMissao().getDescricao().trim().isEmpty()) {
            throw new TarefaInvalidaException("Missão inválida para Rastreador!");
        }
        double pontos = getMissao().getDificuldade() * 2.0 + getNivel();
        return pontos;
    }
    
    @Override
    public Double call() {
        try {
            return executarMissao();
        } catch (TarefaInvalidaException e) {
            System.err.println("Erro no Rastreador: " + e.getMessage());
            return 0.0;
        }
    }
}
```

#### **Saqueador.java**
Implementa um explorador especializado em coleta de recursos:
- Estende `Explorador`
- Implementa `Callable<Double>` para retornar pontuação
- **Cálculo de pontos:** `dificuldade * 2.0 + nivel` (similar ao Rastreador)
- Validação de missão nula ou vazia
- Método `call()`: Tratamento de exceções com retorno seguro

```java
public class Saqueador extends Explorador implements Callable<Double> {
    // Mesma estrutura do Rastreador
}
```

### Processamento com Fork/Join

#### **SomaPontos.java**
Implementa um algoritmo de divide-and-conquer usando RecursiveTask:
- Estende `RecursiveTask<Double>` do framework Fork/Join
- **Estratégia de divisão:**
  - Se tamanho ≤ 2: soma direta
  - Se tamanho > 2: divide no meio e processa recursivamente

- **Fluxo de execução:**
  1. `fork()`: Abre nova thread para lado esquerdo
  2. `compute()`: Calcula lado direito na thread atual
  3. `join()`: Aguarda conclusão e combina resultados

```java
public class SomaPontos extends RecursiveTask<Double> {
    protected double[] pontos;
    protected int pontosIniciais;
    protected int pontosFinais;
    
    @Override
    protected Double compute() {
        int tamanho = pontosFinais - pontosIniciais;
        if (tamanho <= 2) {
            // Soma direta
            double soma = 0;
            for (int i = pontosIniciais; i < pontosFinais; i++) {
                soma += pontos[i];
            }
            return soma;
        } else {
            // Divisão recursiva
            int meio = (pontosIniciais + pontosFinais) / 2;
            SomaPontos esquerda = new SomaPontos(pontos, pontosIniciais, meio);
            SomaPontos direita = new SomaPontos(pontos, meio, pontosFinais);
            
            esquerda.fork();
            Double somaDireita = direita.compute();
            Double somaEsquerda = esquerda.join();
            
            return somaDireita + somaEsquerda;
        }
    }
}
```

### Exceção Personalizada

#### **TarefaInvalidaException.java**
Exceção customizada para validação de missões:
- Estende `Exception`
- Recebe mensagem descritiva do erro

### Programa Principal

#### **ExplorandoMain.java**
Classe main que orquestra toda a simulação:

**Funcionalidades principais:**

1. **Criação de Exploradores:**
   - 2 × Rastreador (Ricardo, Augusto)
   - 2 × Saqueador (Fabricio, Leonardo)
   - Todos com especialidades, níveis e missões específicas

2. **Executor Service (Thread Pool):**
   ```java
   ExecutorService executor = Executors.newFixedThreadPool(2);
   ```
   - Pool de 2 threads para executar tarefas concorrentemente
   - Cada explorador é submetido como `Callable<Double>`

3. **Submission e Coleta de Resultados:**
   ```java
   List<Future<Double>> resultados = new ArrayList<>();
   for (Explorador e : exploradores) {
       resultados.add(executor.submit((Callable<Double>) e));
   }
   ```
   - `submit()` retorna `Future` que permite recuperar o resultado
   - `Future.get()` bloqueia até que a tarefa seja concluída

4. **Consolidação com Fork/Join:**
   ```java
   ForkJoinPool pool = new ForkJoinPool();
   SomaPontos soma = new SomaPontos(pontos, 0, pontos.length);
   double total = pool.invoke(soma);
   ```
   - Usa paralelismo para somar pontos de múltiplos exploradores
   - `invoke()` aguarda conclusão e retorna resultado

## 🎯 Conceitos Demonstrados

### 1️⃣ Interface Callable vs Runnable
- **Runnable:** Sem retorno (`void run()`)
- **Callable:** Com retorno genérico (`V call()`)
- Exploradores implementam `Callable<Double>` para retornar pontuação

### 2️⃣ ExecutorService e Thread Pool
- Gerenciamento automático de threads
- `newFixedThreadPool(2)`: Pool de tamanho fixo
- Alternativas: `newCachedThreadPool()`, `newSingleThreadExecutor()`

### 3️⃣ Future - Resultado Assíncrono
- Representa o resultado de uma computação assíncrona
- `Future.get()`: Bloqueia até obter resultado
- Permite tratamento de exceções e timeouts

### 4️⃣ Fork/Join Framework
- Padrão **divide-and-conquer** para paralelismo eficiente
- `RecursiveTask<T>`: Para tarefas que retornam valor
- `fork()`: Iniciar subtarefa em nova thread
- `compute()`: Computação atual
- `join()`: Aguardar e combinar resultados

### 5️⃣ Imutabilidade
- Classe `Missao` é `final` com atributos `final`
- Segura para acesso multi-thread sem sincronização

### 6️⃣ Exceções Personalizadas
- Validação de entrada com exceções customizadas
- Tratamento seguro em contexto de threads

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac DesafioUnidade3/*.java

# Executar o programa principal
java ExplorandoMain
```

**Saída esperada:**
```
====== SIMULADOR DE CAÇA AO TESOURO ======
== Demonstrando threads, callable e runnable, forkjoin ==

Ricardo está rastreando cuidadosamente em Peru
Augusto está rastreando cuidadosamente em Peru
Fabricio está rastreando cuidadosamente em Peru
Leonardo está rastreando cuidadosamente em Peru

Explorador: Ricardo
Especialidade: Rastreador
Missão: Descobrir mapa do tesouro perdido
Pontos obtidos: 9.0

Explorador: Augusto
Especialidade: Rastreador
Missão: Localizar equipe para a expedição
Pontos obtidos: 8.0

Explorador: Fabricio
Especialidade: Rastreador
Missão: Reunir equipamentos para expedição
Pontos obtidos: 13.0

Explorador: Leonardo
Especialidade: Rastreador
Missão: Reunir mantimentos para expedição
Pontos obtidos: 12.0

Pontuação total consolidada: 42.0
```

## 📊 Fluxo de Execução

```
ExplorandoMain
    ├── Cria 4 exploradores (2 Rastreadores + 2 Saqueadores)
    │
    ├── ExecutorService com 2 threads (Pool)
    │   ├── Thread 1: Executa Ricardo
    │   ├── Thread 2: Executa Augusto
    │   ├── Thread 1: Executa Fabricio (após Ricardo)
    │   └── Thread 2: Executa Leonardo (após Augusto)
    │
    ├── Coleta resultados via Future.get()
    │   └── pontos[] = [9.0, 8.0, 13.0, 12.0]
    │
    ├── Shut down do executor
    │
    ├── ForkJoinPool para consolidação
    │   ├── SomaPontos divide array no meio
    │   ├── Fork esquerda: [9.0, 8.0]
    │   ├── Compute direita: [13.0, 12.0]
    │   ├── Join: 9.0 + 8.0 = 17.0
    │   ├── Soma: 25.0 + 17.0 = 42.0
    │   └── Resultado final: 42.0
    │
    └── Exibe pontuação total consolidada
```

## 📁 Estrutura de Arquivos

```
DesafioUnidade3/
├── Explorador.java                (classe abstrata base)
├── Missao.java                    (classe imutável de missão)
├── Rastreador.java               (subclasse - implementa Callable)
├── Saqueador.java                (subclasse - implementa Callable)
├── SomaPontos.java               (RecursiveTask para Fork/Join)
├── ExplorandoMain.java           (programa principal)
├── TarefaInvalidaException.java   (exceção personalizada)
├── Threads.java                  (template auxiliar)
└── README.md                     (este arquivo)
```

## 📝 Notas de Aprendizado

- **Callable vs Runnable:** Usar Callable quando precisar retornar um valor
- **Future:** Permite obter resultado sem bloquear desnecessariamente
- **ExecutorService:** Gerencia threads automaticamente - sempre usar `shutdown()`
- **Fork/Join:** Ideal para operações recursivas e dividir-para-conquistar
- **Imutabilidade:** Essencial para segurança em ambientes multi-thread
- **Thread pool:** Melhor que criar threads manualmente (overhead menor)

## ⚠️ Considerações Importantes

1. **Tamanho do Pool:** Com 2 threads e 4 tarefas, algumas esperam na fila
2. **Fork/Join Threshold:** Limite de 2 elementos evita overhead excessivo
3. **Cleanup:** `executor.shutdown()` deve sempre ser chamado
4. **Escalabilidade:** Para muitos exploradores, aumentar pool size (com cuidado)
5. **Ponto flutuante:** Usar `Double` em vez de `double` para valores assíncrono

## 🔄 Comparação: CaçaAoTesouro vs DesafioUnidade3

| Aspecto | CaçaAoTesouro | DesafioUnidade3 |
|---------|---------------|-----------------|
| **Threads** | Simples (Runnable) | Avançado (Callable + Fork/Join) |
| **Retorno** | Nenhum | Double (pontuação) |
| **Agendador** | Nenhum | ExecutorService (Thread Pool) |
| **Consolidação** | Nenhuma | Fork/Join para soma |
| **Complexidade** | Básica | Intermediária-Avançada |

---

**Criado em:** javaLearning  
**Tipo:** Exemplo Educacional - Programação Concorrente Avançada  
**Conceitos:** Callable, Future, ExecutorService, Fork/Join, RecursiveTask, Thread Pool
