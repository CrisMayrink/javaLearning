# Threads - Simulador de Servidor Multitarefa

Este projeto demonstra conceitos avançados de **multithreading**, **prioridades de threads**, **threads daemon**, **interrupção de threads** e **tratamento de exceções** através de uma simulação de servidor que processa múltiplas tarefas concorrentemente.

## 📋 Estrutura do Projeto

### Modelo de Dados

#### **Tarefa.java**
Classe que representa uma unidade de trabalho a ser processada:

- **Atributos:**
  - `id`: Identificador único da tarefa (1-6)
  - `descricao`: Descrição da atividade a executar

- **Métodos:**
  - `executar()`: Executa a tarefa com validação
    - Valida se descrição é nula ou vazia
    - Lança `TarefaInvalidaException` se inválida
    - Simula processamento com `Thread.sleep(1000)` (1 segundo)
    - Trata `InterruptedException` para interrupções
    - Obtém nome da thread atual com `Thread.currentThread().getName()`
  - `getId()`, `getDescricao()`: Getters para acesso aos atributos

```java
public class Tarefa {
    private int id;
    private String descricao;
    
    public void executar() throws TarefaInvalidaException {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new TarefaInvalidaException(
                "Tarefa com ID " + id + " possui descrição inválida"
            );
        }
        
        System.out.println("[" + Thread.currentThread().getName() + "] Executando a tarefa " 
            + id + ": " + descricao);
        try {
            Thread.sleep(1000); // Simula processamento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

### Exceção Personalizada

#### **TarefaInvalidaException.java**
Exceção customizada para validação de tarefas:
- Estende `Exception`
- Recebe mensagem descritiva do erro
- Utilizada quando tarefa tem descrição inválida (nula ou vazia)

### Processadores de Tarefas

#### **ProcessadorRapido.java**
Processador que executa tarefas com velocidade alta:

- Implementa `Runnable` para execução em thread separada
- Recebe `Tarefa` no construtor
- Método `run()`: 
  - Executa tarefa
  - Trata `TarefaInvalidaException` com mensagem prefixada "[PROCESSADOR RAPIDO]"
  - Usa `System.err` para erros
  - Fornece feedback de início e fim de processamento

```java
public class ProcessadorRapido implements Runnable {
    private Tarefa tarefa;
    
    public ProcessadorRapido(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("[PROCESSADOR RAPIDO] Iniciando processamento da tarefa " + tarefa.getId());
            tarefa.executar();
            System.out.println("[PROCESSADOR RAPIDO] Processamento da tarefa " + tarefa.getId() + " finalizado.");
        } catch(TarefaInvalidaException e) {
            System.err.println("[PROCESSADOR RAPIDO] Erro: " + e.getMessage());
        }
    }
}
```

#### **ProcessadorLento.java**
Processador que executa tarefas com velocidade reduzida:

- Implementa `Runnable` para execução em thread separada
- Recebe `Tarefa` no construtor
- Método `run()`: 
  - Executa tarefa com mensagens prefixadas "[PROCESSADOR LENTO]"
  - Tratamento idêntico ao ProcessadorRapido
  - Diferencia-se apenas pela prioridade atribuída na thread

```java
public class ProcessadorLento implements Runnable {
    private Tarefa tarefa;
    
    @Override
    public void run() {
        try {
            System.out.println("[PROCESSADOR LENTO] Iniciando processamento da tarefa " + tarefa.getId());
            tarefa.executar();
            System.out.println("[PROCESSADOR LENTO] Processamento finalizado.");
        } catch(TarefaInvalidaException e) {
            System.err.println("[PROCESSADOR LENTO] Erro: " + e.getMessage());
        }
    }
}
```

### Programa Principal

#### **ServidorMultitarefaMain.java**
Classe main que orquestra toda a simulação de servidor:

**Funcionalidades principais:**

1. **Configuração de Encoding UTF-8:**
   ```java
   System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
   System.setErr(new PrintStream(System.out, true, StandardCharsets.UTF_8));
   ```

2. **Criação de Tarefas:**
   - Tarefa 1: "Processar requisição HTTP" ✓
   - Tarefa 2: "Conectar ao banco de dados..." ✓
   - Tarefa 3: "" (inválida - demonstra exceção)
   - Tarefa 4: "Enviar e-mail de notificação..." ✓
   - Tarefa 5: "Gerar relatório mensal" ✓
   - Tarefa 6: `null` (inválida - demonstra exceção)

3. **Configuração de Threads:**
   - Alternância entre ProcessadorRapido (índices pares) e ProcessadorLento (índices ímpares)
   - Atribuição de prioridades:
     - ProcessadorRapido: `Thread.MAX_PRIORITY` (10)
     - ProcessadorLento: `Thread.MIN_PRIORITY` (1)
   - Threads a partir do índice 4 são configuradas como **daemon**

4. **Exibição de Informações (pré-execução):**
   - Nome da thread
   - Prioridade
   - Status daemon
   - Estado atual

5. **Execução das Threads:**
   - Inicia todas as threads sequencialmente
   - Exibe estado após inicialização

6. **Sincronização com `join()`:**
   - Aguarda conclusão apenas de threads não-daemon
   - Tratamento de `InterruptedException`
   - Threads daemon podem ser interrompidas se programa terminar

7. **Verificação de Estado Final:**
   - Estado das threads (RUNNABLE, TERMINATED, etc.)
   - Status "vivo" (`isAlive()`)

```java
public class ServidorMultitarefaMain {
    public static void main(String[] args) {
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(new Tarefa(1, "Processar requisição HTTP"));
        tarefas.add(new Tarefa(2, "Conectar ao banco de dados..."));
        tarefas.add(new Tarefa(3, "")); // Inválida
        tarefas.add(new Tarefa(4, "Enviar e-mail..."));
        tarefas.add(new Tarefa(5, "Gerar relatório"));
        tarefas.add(new Tarefa(6, null)); // Inválida
        
        List<Thread> threads = new ArrayList<>();
        
        for(int i = 0; i < tarefas.size(); i++) {
            Thread thread;
            if(i % 2 == 0) {
                thread = new Thread(new ProcessadorRapido(tarefas.get(i)), "Thread-Rápida" + (i+1));
                thread.setPriority(Thread.MAX_PRIORITY);
            } else {
                thread = new Thread(new ProcessadorLento(tarefas.get(i)), "Thread-Lenta" + (i+1));
                thread.setPriority(Thread.MIN_PRIORITY);
            }
            
            if(i >= 4) {
                thread.setDaemon(true);
            }
            threads.add(thread);
        }
        
        // Iniciar threads
        for (Thread thread: threads) {
            thread.start();
        }
        
        // Aguardar conclusão (exceto daemon)
        for(Thread thread : threads) {
            if (!thread.isDaemon()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println("Interrupção: " + e.getMessage());
                }
            }
        }
    }
}
```

## 🎯 Conceitos Demonstrados

### 1️⃣ Interface Runnable
- Implementação para tornar classe executável em thread
- Método `run()` contém lógica a ser executada
- Melhor que estender `Thread` (composição > herança)

### 2️⃣ Threads Concorrentes
- Múltiplas threads executando simultaneamente
- Cada processador em sua própria thread
- Compartilhamento de tempo de CPU

### 3️⃣ Prioridades de Threads
```java
thread.setPriority(Thread.MAX_PRIORITY);  // 10
thread.setPriority(Thread.MIN_PRIORITY);  // 1
```
- JVM tenta favorecer threads com prioridade maior
- Não garante ordem de execução
- 10 níveis de prioridade disponíveis

### 4️⃣ Threads Daemon
```java
thread.setDaemon(true);
```
- Threads daemon não impedem encerramento da JVM
- Úteis para tarefas de apoio (logging, limpeza)
- Se programa terminar, daemon é interrompida
- `thread.isDaemon()` verifica status

### 5️⃣ Sincronização com `join()`
```java
thread.join();  // Bloqueia até thread terminar
```
- Aguarda conclusão de thread específica
- Gera `InterruptedException` se interrompida
- Essencial para coordenar threads dependentes

### 6️⃣ Estados de Thread
- **NEW:** Criada, mas não iniciada
- **RUNNABLE:** Pronta ou executando
- **WAITING/TIMED_WAITING:** Aguardando/com timeout
- **BLOCKED:** Bloqueada por lock
- **TERMINATED:** Finalizada

### 7️⃣ Acesso ao Contexto de Thread
```java
Thread.currentThread().getName()     // Nome da thread
Thread.currentThread().interrupt()   // Interromper
thread.getState()                    // Estado atual
thread.isAlive()                     // Ainda executando?
```

### 8️⃣ Tratamento de InterruptedException
```java
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```
- Exceção gerada quando thread é interrompida durante sleep
- Importante restaurar status de interrupção

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac Threads/*.java

# Executar o programa principal
java ServidorMultitarefaMain
```

**Saída esperada:**
```
====== SIMULADOR DE SERVIDOR MULTITAREFA ======
== Demonstrando threads, prioridades e exceções personalizadas ==

Thread Thread-Rápida1 configurada como USER
Thread Thread-Lenta2 configurada como USER
Thread Thread-Rápida3 configurada como USER
Thread Thread-Lenta4 configurada como USER
Thread Thread-Rápida5 configurada como DAEMON
Thread Thread-Lenta6 configurada como DAEMON

=== INFORMAÇÕES DAS THREADS ===
Thread: Thread-Rápida1 | Prioridade: 10 | Daemon: false | Status: NEW
Thread: Thread-Lenta2 | Prioridade: 1 | Daemon: false | Status: NEW
Thread: Thread-Rápida3 | Prioridade: 10 | Daemon: false | Status: NEW
Thread: Thread-Lenta4 | Prioridade: 1 | Daemon: false | Status: NEW
Thread: Thread-Rápida5 | Prioridade: 10 | Daemon: true | Status: NEW
Thread: Thread-Lenta6 | Prioridade: 1 | Daemon: true | Status: NEW

=== INICIANDO A EXECUÇÃO DAS THREADS ===
Thread: Thread-Rápida1 iniciada - Status: RUNNABLE
Thread: Thread-Lenta2 iniciada - Status: RUNNABLE
Thread: Thread-Rápida3 iniciada - Status: RUNNABLE
Thread: Thread-Lenta4 iniciada - Status: RUNNABLE
Thread: Thread-Rápida5 iniciada - Status: RUNNABLE
Thread: Thread-Lenta6 iniciada - Status: RUNNABLE

=== AGUARDANDO A CONCLUSÃO DAS THREADS ===
[PROCESSADOR RAPIDO] Iniciando processamento da tarefa 1
[PROCESSADOR LENTO] Iniciando processamento da tarefa 2
[PROCESSADOR RAPIDO] Iniciando processamento da tarefa 3
[PROCESSADOR RAPIDO] Erro: Tarefa com ID 3 possui descrição inválida: 
[PROCESSADOR LENTO] Iniciando processamento da tarefa 4
[PROCESSADOR RAPIDO] Iniciando processamento da tarefa 5
[PROCESSADOR LENTO] Erro: Tarefa com ID 6 possui descrição inválida: null
[Thread-Rápida1] Executando a tarefa 1: Processar requisição HTTP.
[Thread-Lenta2] Executando a tarefa 2: Conectar ao banco de dados....
[Thread-Lenta4] Executando a tarefa 4: Enviar e-mail de notificação....
[Thread-Rápida5] Executando a tarefa 5: Gerar relatório mensal.
[Thread-Rápida1] Tarefa 1 concluida.
[Thread-Lenta2] Tarefa 2 concluida.
[Thread-Lenta4] Tarefa 4 concluida.
[Thread-Rápida5] Tarefa 5 concluida.

Thread Thread-Rápida1 finalizada - Status: TERMINATED
Thread Thread-Lenta2 finalizada - Status: TERMINATED
Thread Thread-Rápida3 finalizada - Status: TERMINATED
Thread Thread-Lenta4 finalizada - Status: TERMINATED

==== Estado final das Threads ====
Thread: Thread-Rápida1 | Estado: TERMINATED | Viva: false
Thread: Thread-Lenta2 | Estado: TERMINATED | Viva: false
Thread: Thread-Rápida3 | Estado: TERMINATED | Viva: false
Thread: Thread-Lenta4 | Estado: TERMINATED | Viva: false
Thread: Thread-Rápida5 | Estado: TERMINATED | Viva: false
Thread: Thread-Lenta6 | Estado: TERMINATED | Viva: false

=====SERVIDOR MULTITAREFA FINALIZADO ====
```

## 📊 Fluxo de Execução

```
ServidorMultitarefaMain
    ├── Cria 6 tarefas (2 inválidas)
    │
    ├── Cria 6 threads:
    │   ├── Thread-Rápida1: ProcessadorRapido, MAX_PRIORITY, USER
    │   ├── Thread-Lenta2: ProcessadorLento, MIN_PRIORITY, USER
    │   ├── Thread-Rápida3: ProcessadorRapido, MAX_PRIORITY, USER
    │   ├── Thread-Lenta4: ProcessadorLento, MIN_PRIORITY, USER
    │   ├── Thread-Rápida5: ProcessadorRapido, MAX_PRIORITY, DAEMON
    │   └── Thread-Lenta6: ProcessadorLento, MIN_PRIORITY, DAEMON
    │
    ├── Exibe informações pré-execução
    │
    ├── Inicia todas as threads
    │
    ├── Execução paralela:
    │   ├── Thread-Rápida1 processa tarefa 1 ✓
    │   ├── Thread-Lenta2 processa tarefa 2 ✓
    │   ├── Thread-Rápida3 processa tarefa 3 ✗ (exceção)
    │   ├── Thread-Lenta4 processa tarefa 4 ✓
    │   ├── Thread-Rápida5 processa tarefa 5 ✓
    │   └── Thread-Lenta6 processa tarefa 6 ✗ (exceção)
    │
    ├── Aguarda conclusão de threads não-daemon com join()
    │   └── Threads daemon podem ser interrompidas
    │
    └── Exibe estado final e encerra
```

## 📁 Estrutura de Arquivos

```
Threads/
├── Tarefa.java                      (unidade de trabalho)
├── TarefaInvalidaException.java      (exceção personalizada)
├── ProcessadorRapido.java           (processador rápido)
├── ProcessadorLento.java            (processador lento)
├── ServidorMultitarefaMain.java     (programa principal)
└── README.md                        (este arquivo)
```

## 📝 Notas de Aprendizado

### Padrão Strategy
- `ProcessadorRapido` e `ProcessadorLento` são estratégias diferentes
- Ambos implementam `Runnable` (interface comum)
- Diferem apenas em prioridade (configurada na thread)

### Prioridades não Garantem Ordem
```
for(int i = 0; i < 6; i++) {
    // Mesmo com prioridades diferentes,
    // ordem de execução é não-determinística
    thread.setPriority(...);
    thread.start();
}
```

### Join vs Sleep
- `Thread.sleep()`: Pausa thread específica
- `Thread.join()`: Aguarda termino de outra thread
- Essencial para coordenação entre threads

### Daemon Threads
- Úteis para tarefas de suporte (logging, garbage collection)
- Não impedem encerramento da JVM
- Se programa terminar, daemon é simplesmente parada

### Validação em Runnable
- Sempre validar entrada no `run()`
- Usar try-catch para exceções personalizadas
- Usar `Thread.currentThread()` para contexto

## ⚠️ Considerações Importantes

1. **Race Conditions:** Múltiplas threads modificando dados compartilhados
2. **Deadlock:** Threads bloqueadas esperando um pela outra
3. **InterruptedException:** Sempre restaurar status com `interrupt()`
4. **Resource Cleanup:** Garantir liberação de recursos mesmo com exceção
5. **Debugging:** Ordem de saída pode variar entre execuções

## 🔄 Comparação: CaçaAoTesouro vs Threads

| Aspecto | CaçaAoTesouro | Threads |
|---------|---------------|---------|
| **Tarefas** | 4 exploradores | 6 tarefas |
| **Prioridades** | MAX/MIN | MAX/MIN (mais granular) |
| **Daemon** | 1 thread | 2 threads |
| **Exceções** | TarefaInvalidaException | TarefaInvalidaException |
| **Sincronização** | Nenhuma | `join()` para coordenação |
| **Feedback** | Status do explorador | Nome da thread em cada operação |
| **Processadores** | N/A | ProcessadorRapido/Lento |

## 💡 Extensões Possíveis

1. **Pool de Threads:**
   - Usar `ExecutorService` para gerenciar threads
   - Controlar número máximo de threads concorrentes

2. **Fila de Tarefas:**
   - Implementar `BlockingQueue` para tarefas
   - Processadores retiram tarefas da fila

3. **Métricas:**
   - Tempo de execução de cada tarefa
   - Taxa de sucesso/falha
   - Throughput (tarefas/segundo)

4. **Prioridades Dinâmicas:**
   - Ajustar prioridade baseado em carga
   - Realocar recursos em tempo real

5. **Timeouts:**
   - Cancelar tarefas que excedem tempo limite
   - Usar `Future` com timeout

---

**Criado em:** javaLearning  
**Tipo:** Exemplo Educacional - Multithreading Avançado  
**Conceitos:** Threads, Runnable, Prioridades, Daemon Threads, join(), InterruptedException, Estados de Thread, Exceções Personalizadas
