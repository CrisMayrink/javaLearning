# CaçaAoTesouro - Simulador de Exploração com Threads

Este projeto demonstra conceitos fundamentais de **multithreading**, **prioridades de threads**, **tipos de threads (user e daemon)** e **exceções personalizadas** em Java.

## 📋 Estrutura do Projeto

### Classe Base

#### **Explorador.java**
Classe abstrata que define o modelo para todos os exploradores:
- **Atributos protegidos:**
  - `nome`: Identificação do explorador
  - `tipo`: Tipo de exploração (1 = rápido, 2 = cuidadoso)
  - `prioridade`: Nível de prioridade da tarefa (0-10)
  - `tarefa`: Descrição da atividade a executar

- **Métodos:**
  - `executarTarefa()`: Método abstrato que deve ser implementado pelas subclasses
  - `exibirStatus()`: Exibe informações do explorador
  - `getNome()`, `getTipo()`, `getPrioridade()`, `getTarefa()`: Setters para os atributos

```java
public abstract class Explorador {
    protected String nome;
    protected int tipo;
    protected int prioridade;
    protected String tarefa;
    
    public abstract void executarTarefa() throws TarefaInvalidaException;
    public void exibirStatus() { ... }
}
```

### Subclasses de Explorador

#### **ExploradorRapido.java**
Implementa um explorador que executa tarefas rapidamente:
- Estende `Explorador`
- Implementa `Runnable` para execução em thread separada
- Valida se a tarefa não é nula ou vazia
- Lança `TarefaInvalidaException` se a tarefa for inválida
- Método `run()`: Trata exceções durante a execução em thread

```java
public class ExploradorRapido extends Explorador implements Runnable {
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (tarefa == null || tarefa.trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para Explorador Rápido!");
        }
        System.out.println("Executando rapidamente a tarefa: " + tarefa);
    }
}
```

#### **ExploradorCuidadoso.java**
Implementa um explorador que executa tarefas com cuidado:
- Estende `Explorador`
- Implementa `Runnable` para execução em thread separada
- Validação similar ao `ExploradorRapido`
- Lança `TarefaInvalidaException` com mensagem específica
- Método `run()`: Trata exceções durante a execução em thread

```java
public class ExploradorCuidadoso extends Explorador implements Runnable {
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (tarefa == null || tarefa.trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para Explorador Cuidadoso!");
        }
        System.out.println("Executando cuidadosamente a tarefa: " + tarefa);
    }
}
```

### Exceção Personalizada

#### **TarefaInvalidaException.java**
Exceção customizada para validação de tarefas:
- Estende `Exception`
- Recebe uma mensagem descritiva do erro
- Utilizada para tratamento específico de erros de validação

```java
public class TarefaInvalidaException extends Exception {
    public TarefaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
```

### Programa Principal

#### **ExplorandoMain.java**
Classe main que executa a simulação de caça ao tesouro:

**Funcionalidades principais:**
1. **Configuração de encoding UTF-8** para suporte a caracteres especiais
2. **Criação de exploradores:**
   - 2 × ExploradorRapido (Ricardo, Augusto)
   - 2 × ExploradorCuidadoso (Cris, Rafa)

3. **Gerenciamento de threads:**
   - Wrapping dos exploradores em objetos `Thread`
   - Configuração de prioridades:
     - `ExploradorRapido`: `Thread.MAX_PRIORITY` (10)
     - `ExploradorCuidadoso`: `Thread.MIN_PRIORITY` (1)

4. **Tipos de threads:**
   - `tRapido2` é configurada como **daemon** (`setDaemon(true)`)
   - Threads daemon não impedem que a JVM encerre

5. **Execução:**
   - Inicia todas as threads simultaneamente
   - Demonstra concorrência e prioridades

```java
Thread tRapido1 = new Thread(rapido1);
tRapido1.setPriority(Thread.MAX_PRIORITY);
tRapido1.start();
```

### Arquivo de Ajuda

#### **Threads.java**
Arquivo auxiliar (template padrão gerado pela IDE).

## 🎯 Conceitos Demonstrados

### 1️⃣ Polimorfismo
- `Explorador` como classe base abstrata
- `ExploradorRapido` e `ExploradorCuidadoso` com comportamentos específicos
- Implementação de `Runnable` interface

### 2️⃣ Multithreading
- Implementação de `Runnable` para código concorrente
- Criação de threads com `new Thread(runnable)`
- Método `start()` para iniciar execução assíncrona

### 3️⃣ Prioridades de Threads
- Definição com `setPriority()`
- `Thread.MAX_PRIORITY` (10) e `Thread.MIN_PRIORITY` (1)
- A JVM tenta favorecer threads com prioridade maior

### 4️⃣ Threads Daemon
- Threads daemon não impedem encerramento da JVM
- Configuradas com `setDaemon(true)`
- Úteis para tarefas de limpeza e monitoramento

### 5️⃣ Exceções Personalizadas
- Criação de classes que estendem `Exception`
- Tratamento específico de erros de negócio
- Validação com `try-catch` durante execução em thread

### 6️⃣ Validação de Dados
- Verificação de nulidade: `tarefa == null`
- Verificação de string vazia: `tarefa.trim().isEmpty()`
- Lançamento de exceção para casos inválidos

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac CaçaAoTesouro/*.java

# Executar o programa principal
java ExplorandoMain
```

**Saída esperada:**
```
====== SIMULADOR DE CAÇA AO TESOURO ======
== Demonstrando threads, prioridades ==

Executando rapidamente a tarefa: Explorar cavernas
Executando rapidamente a tarefa: Mapear floresta
Executando cuidadosamente a tarefa: Analisar fósséis
[EXPLORADOR CUIDADOSO] Erro: Tarefa inválida para Explorador Cuidadoso!

===== Caça ao tesouro finalizada ====
```

> **Nota:** A ordem exata de execução pode variar devido à natureza concorrente das threads.

## 📊 Fluxo de Execução

```
ExplorandoMain
    ├── Cria ExploradorRapido(Ricardo, 1, 10, "Explorar cavernas")
    ├── Cria ExploradorRapido(Augusto, 1, 9, "Mapear floresta")
    ├── Cria ExploradorCuidadoso(Cris, 2, 5, "Analisar fósséis")
    ├── Cria ExploradorCuidadoso(Rafa, 2, 4, "") ← tarefa vazia!
    │
    ├── Envolve em Threads com prioridades:
    │   ├── tRapido1: MAX_PRIORITY (10), user thread
    │   ├── tRapido2: MAX_PRIORITY (10), daemon thread ← será interrompida
    │   ├── tCuidadoso1: MIN_PRIORITY (1), user thread
    │   └── tCuidadoso2: MIN_PRIORITY (1), user thread
    │
    └── Inicia todas as threads
        ├── Ricardo e Augusto executam com prioridade alta
        ├── Cris executa com prioridade baixa
        └── Rafa gera exceção (tarefa vazia)
```

## 📁 Estrutura de Arquivos

```
CaçaAoTesouro/
├── Explorador.java              (classe abstrata base)
├── ExploradorRapido.java        (subclasse - executa rápido)
├── ExploradorCuidadoso.java     (subclasse - executa com cuidado)
├── ExplorandoMain.java          (programa principal)
├── TarefaInvalidaException.java  (exceção personalizada)
├── Threads.java                 (template auxiliar)
├── README.md                    (este arquivo)
└── erro github 17-05.png        (documentação de erro)
```

## 📝 Notas de Aprendizado

- **Threads são leves:** Java permite criar muitas threads facilmente
- **Prioridades são sugestões:** Não garantem ordem de execução
- **Daemon threads:** Úteis para tarefas de background
- **Exceções em threads:** Use try-catch dentro do método `run()` para evitar travamentos
- **Thread safety:** Este exemplo é simples; projetos reais precisam de sincronização
- **Runnable vs Thread:** Implementar `Runnable` é melhor que estender `Thread` (composição > herança)

## ⚠️ Considerações Importantes

1. **Ordem não determinística:** A ordem de saída pode variar entre execuções
2. **Tarefa vazia:** `ExploradorCuidadoso(Rafa, 2, 4, "")` causará `TarefaInvalidaException`
3. **Daemon vs User:** `tRapido2` é daemon, podendo ser interrompida se não for rápida
4. **Encoding:** UTF-8 é configurado para suportar caracteres especiais (acentos, etc.)

---

**Criado em:** javaLearning  
**Tipo:** Exemplo Educacional - Multithreading  
**Conceitos:** Threads, Prioridades, Exceções, Polimorfismo, Runnable Interface
