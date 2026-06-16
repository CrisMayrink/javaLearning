# Polimorfismo - Sistema de Colaboradores com Herança e Interfaces

Este projeto demonstra conceitos fundamentais de **polimorfismo**, **herança**, **interfaces**, **casting** (upcasting e downcasting), **validação de dados** e **encapsulamento** através de um sistema de gestão de colaboradores corporativos.

## 📋 Estrutura do Projeto

### Interface de Auditoria

#### **Auditavel.java** (`Entidades`)
Interface que define contrato para objetos auditáveis:

- **Métodos:**
  - `registrarAtividade(String atividade)`: Registra uma atividade realizada
  - `auditar()`: Exibe relatório de atividades

```java
public interface Auditavel {
    void registrarAtividade(String atividade);
    void auditar();
}
```

**Propósito:** Implementar padrão de **Segregation of Interfaces** - apenas tipos que precisam ser auditados implementam esta interface.

### Classe Base Abstrata

#### **Colaborador.java** (`Entidades`)
Classe abstrata que define a estrutura comum de todos os colaboradores:

- **Atributos privados:**
  - `nome`: Nome do colaborador
  - `matricula`: Identificador único
  - `salario`: Remuneração (com validação)

- **Métodos concretos (reutilizáveis):**
  - **Getters e Setters com validação:**
    - `setSalario()`: Valida se salário é negativo, lança `IllegalArgumentException`
    - `setMatricula()`: Define matrícula
    - `getNome()`, `getSalario()`, `getMatricula()`: Acesso aos dados
  
  - `aumentarSalario(double percentual)`: 
    - Aumenta salário por percentual
    - Valida se percentual é positivo
    - Exibe feedback formatado

  - `atribuirBonus(double valor)`:
    - Adiciona bônus ao salário
    - Valida se valor é positivo
    - Exibe novo salário

  - `exibirDados()`:
    - Exibe informações formatadas com `DecimalFormat`
    - Mostra tipo usando `this.getClass().getSimpleName()`
    - Uso de `String.format()` para valores monetários

- **Método abstrato:**
  - `executarTarefa()`: Força subclasses a definir tarefa específica

```java
public abstract class Colaborador {
    private String nome;
    private int matricula;
    private double salario;
    
    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo!");
        }
        this.salario = salario;
    }
    
    public abstract void executarTarefa();
    
    public void exibirDados() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("Nome: " + nome + " | Matrícula: " + matricula 
            + " | Salário: R$ " + df.format(salario)
            + " | Tipo: " + this.getClass().getSimpleName());
    }
}
```

### Subclasses Especializadas

#### **Desenvolvedor.java** (`Entidades`)
Especialização para colaboradores que desenvolvem software:

- Estende `Colaborador`
- Implementa `Auditavel` para auditoria de atividades
- **Atributos adicionais:**
  - `atividades`: `ArrayList<String>` para registrar tarefas

- **Implementações:**
  - `executarTarefa()`: "Está codificando uma nova funcionalidade"
  - `registrarAtividade()`: Adiciona atividade à lista (com validação)
  - `auditar()`: Exibe relatório de atividades do desenvolvedor

```java
public class Desenvolvedor extends Colaborador implements Auditavel {
    private List<String> atividades;
    
    @Override
    public void executarTarefa() {
        System.out.println(getNome() + " está codificando uma nova funcionalidade...");
        registrarAtividade("codificou uma nova funcionalidade no sistema.");
    }
    
    @Override
    public void registrarAtividade(String atividade) {
        if (atividade == null || atividade.trim().isEmpty()) {
            System.out.println("Erro: Atividade não pode ser nula ou vazia.");
        } else {
            atividades.add(atividade);
            System.out.println("Atividade registrada: " + atividade);
        }
    }
    
    @Override
    public void auditar() {
        System.out.println("=== AUDITORIA - Desenvolvedor " + getNome() + " ===");
        System.out.println("Total de atividades: " + atividades.size());
        for (int i = 0; i < atividades.size(); i++) {
            System.out.println((i + 1) + ". " + atividades.get(i));
        }
    }
}
```

#### **Gerente.java** (`Entidades`)
Especialização para colaboradores que gerenciam equipes:

- Estende `Colaborador`
- Implementa `Auditavel` para auditoria de atividades
- **Atributos adicionais:**
  - `atividades`: `ArrayList<String>` para registrar tarefas de gestão

- **Implementações:**
  - `executarTarefa()`: "Está coordenando uma reunião com a equipe"
  - `registrarAtividade()`: Adiciona atividade à lista (com validação)
  - `auditar()`: Exibe relatório de atividades do gerente
  - `getAtividades()`: Retorna lista de atividades

```java
public class Gerente extends Colaborador implements Auditavel {
    private List<String> atividades;
    
    @Override
    public void executarTarefa() {
        System.out.println(getNome() + " está coordenando uma reunião com a equipe!");
        System.out.println(">> Definindo agenda...");
        System.out.println(">> Apresentando objetivos...");
        System.out.println(">> Delegando tarefas...");
        registrarAtividade("Gerenciou a equipe.");
    }
    
    @Override
    public void registrarAtividade(String atividade) {
        if (atividade == null || atividade.trim().isEmpty()) {
            System.out.println("Erro: Atividade não pode ser nula ou vazia.");
        } else {
            atividades.add(atividade);
            System.out.println("Atividade registrada: " + atividade);
        }
    }
}
```

### Programa Principal

#### **MainColabore.java** (`studingsistweb.colabore`)
Classe main que demonstra polimorfismo e casting:

**Funcionalidades principais:**

1. **Configuração de Locale:**
   ```java
   Locale.setDefault(Locale.US);  // Para formatação de números
   ```

2. **Criação de Colaboradores Polimórficos:**
   ```java
   List<Colaborador> colaboradores = new ArrayList<>();
   colaboradores.add(new Desenvolvedor("Carlos Santos", 1001, 8000.00));
   colaboradores.add(new Desenvolvedor("Cristina Mayrink", 1002, 7000.00));
   colaboradores.add(new Gerente("Manoel Ferreira", 1000, 12000.00));
   ```

3. **Demonstração de Polimorfismo Dinâmico:**
   ```java
   for(Colaborador colaborador : colaboradores) {
       colaborador.exibirDados();      // Cada tipo exibe de forma diferente
       colaborador.aumentarSalario(15); // Mesmo método para todos
   }
   ```

4. **Uso de `instanceof` para Verificação de Tipo:**
   ```java
   for(Colaborador colab : colaboradores) {
       if (colab instanceof Gerente) {
           gerente = (Gerente) colab;  // Downcasting
           break;
       }
   }
   ```

5. **Upcasting Implícito:**
   ```java
   Auditavel auditavel = gerente;  // Upcast: Gerente → Auditavel
   auditavel.registrarAtividade("Reunião estratégica");
   auditavel.auditar();
   ```

6. **Downcasting Explícito:**
   ```java
   Colaborador colaboradorRef = gerente;  // Upcast: Gerente → Colaborador
   Gerente gerenteRef = (Gerente)colaboradorRef;  // Downcast: Colaborador → Gerente
   gerenteRef.atribuirBonus(2000.00);
   ```

## 🎯 Conceitos Demonstrados

### 1️⃣ Polimorfismo de Subtipo (Subtyping)
- Mesma interface (`Colaborador`), múltiplas implementações
- Comportamento diferente para cada tipo

### 2️⃣ Herança Simples
- `Desenvolvedor` e `Gerente` herdam de `Colaborador`
- Reutilização de código (DRY principle)
- Especialização de comportamento

### 3️⃣ Interfaces (Contrato)
- `Auditavel` define método que ambas subclasses implementam
- Implementação múltipla de interfaces
- Segregação de responsabilidades

### 4️⃣ Classe Abstrata
- `Colaborador` não pode ser instanciada diretamente
- Define contrato com método abstrato `executarTarefa()`
- Fornece implementação comum (métodos concretos)

### 5️⃣ Upcasting (Implícito)
```java
Colaborador colab = new Desenvolvedor(...);  // Automático
Auditavel aud = gerente;                     // Automático
```
- Sempre seguro
- Sem necessidade de casting explícito
- Tipo mais específico → tipo mais genérico

### 6️⃣ Downcasting (Explícito)
```java
Colaborador colab = new Gerente(...);
Gerente gerente = (Gerente) colab;  // Necessita casting
```
- Requer verificação `instanceof` prévia
- Pode gerar `ClassCastException` se tipo não match
- Tipo mais genérico → tipo mais específico

### 7️⃣ Instanceof para Type Checking
```java
if (colab instanceof Gerente) {
    Gerente g = (Gerente) colab;
}
```
- Verifica tipo em tempo de execução
- Padrão seguro para downcasting
- Evita exceções não tratadas

### 8️⃣ Validação com Exceções
- `IllegalArgumentException` para dados inválidos
- Validação no setter (single responsibility)
- Mensagens descritivas de erro

### 9️⃣ Formatação de Dados
- `DecimalFormat` para formatação monetária
- `String.format()` para valores com casas decimais
- Locale-aware para diferentes regiões

### 🔟 Collections Tipadas
- `List<Colaborador>`: Type-safe container
- Iterate com enhanced for loop
- Generic types para segurança em tempo de compilação

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac Polimorfismo/Entidades/*.java Polimorfismo/studingsistweb/colabore/*.java

# Executar o programa principal
java studingsistweb.colabore.Colabore
```

**Saída esperada:**
```
====SISTEMA DE COLABORADORES GODTECH ===

>> Demonstração de polimorfismo, classes abstratas e casting

>> Colaboradores cadastrados com sucesso.

=== COLABORADOR 1 ===

=== Dados do Colaborador ===
Nome: Carlos Santos | Matrícula: 1001 | Salário: R$ 8,000.00
Tipo: Desenvolvedor
============================

>> Aplicando aumento salarial...
Salário de Carlos Santos aumentado em 15.00%. Novo Salário: R$ 9,200.00

>> Salário após reajuste:
=== Dados do Colaborador ===
Nome: Carlos Santos | Matrícula: 1001 | Salário: R$ 9,200.00
Tipo: Desenvolvedor
============================

============================================================

=== COLABORADOR 2 ===
[...]

=== DEMONSTRAÇÃO DE INTERFACE E CASTING ===

>> Gerente encontrado: Manoel Ferreira

>> Registrando atividades atraves da interface Auditavel:
Atividade registrada: Reunião de planejamento estratégico.
Atividade registrada: Avaliação de desempenho da equipe.
Atividade registrada: Aprovação de novos projetos.

>> Executando auditoria
=== AUDITORIA DE ATIVIDADES ===
Atividades do gerente Manoel Ferreira:
Total de atividades registradas: 3
Atividades:
1. Gerenciou a equipe.
2. Reunião de planejamento estratégico.
3. Avaliação de desempenho da equipe.
4. Aprovação de novos projetos.

>> Aplicando bônus através de casting:
Salário antes do bônus: R$ 12,000.00
Bônus de R$ 2,000.00 atribuido a: Manoel Ferreira. Novo salário: R$ 14,000.00

>> Dados finais do gerente:
=== Dados do Colaborador ===
Nome: Manoel Ferreira | Matrícula: 1000 | Salário: R$ 14,000.00
Tipo: Gerente
============================

==== Fim do Sistema de Colaboradores =====
```

## 📊 Hierarquia de Tipos

```
Object
  ↑
  │
Colaborador (abstract)
  ↑
  ├─ Desenvolvedor implements Auditavel
  └─ Gerente implements Auditavel

Auditavel (interface)
  ↑
  ├─ Desenvolvedor
  └─ Gerente
```

## 📁 Estrutura de Arquivos

```
Polimorfismo/
├── Entidades/
│   ├── Auditavel.java           (interface)
│   ├── Colaborador.java         (classe abstrata base)
│   ├── Desenvolvedor.java       (subclasse especializada)
│   └── Gerente.java             (subclasse especializada)
├── studingsistweb/colabore/
│   └── MainColabore.java        (programa principal)
└── README.md                    (este arquivo)
```

## 📝 Notas de Aprendizado

### Diferenças: Classe Abstrata vs Interface

| Aspecto | Classe Abstrata | Interface |
|---------|-----------------|-----------|
| **Método abstrato** | Sim | Sim (até Java 8) |
| **Método concreto** | Sim | Sim (default methods) |
| **Atributos** | Sim (privados/protegidos) | Sim (public static final) |
| **Herança** | Simples (extends) | Múltipla (implements) |
| **Acesso** | Pode ter qualificadores | Implicitamente public |
| **Construtor** | Sim | Não |

### Polimorfismo na Prática
```java
// Mesmo método, comportamentos diferentes
colaborador1.executarTarefa();  // Desenvolvedor: "codificando"
colaborador2.executarTarefa();  // Gerente: "reunião"
```

### Segurança de Type-Casting
```java
// ❌ Arriscado - pode gerar ClassCastException
Gerente g = (Gerente) colaborador;

// ✅ Seguro - verifica tipo antes
if (colaborador instanceof Gerente) {
    Gerente g = (Gerente) colaborador;
}
```

### Collections Polimórficas
```java
// Uma lista contém tipos diferentes, mas relacionados
List<Colaborador> lista = new ArrayList<>();
lista.add(new Desenvolvedor(...));  // Subclasse
lista.add(new Gerente(...));        // Subclasse
// Ambos são Colaborador
```

## ⚠️ Armadilhas Comuns

1. **Downcasting sem verificação:**
   ```java
   // ❌ Pode falhar em runtime
   Gerente g = (Gerente) colaborador;
   
   // ✅ Correto
   if (colaborador instanceof Gerente) {
       Gerente g = (Gerente) colaborador;
   }
   ```

2. **Acessar métodos específicos via tipo base:**
   ```java
   Colaborador colab = new Gerente(...);
   // ❌ Não compila
   List<String> atividades = colab.getAtividades();
   
   // ✅ Necessita cast
   Gerente g = (Gerente) colab;
   List<String> atividades = g.getAtividades();
   ```

3. **Implementação vazia em interface:**
   ```java
   // Cada classe deve implementar com lógica específica
   public void auditar() {
       System.out.println("Auditando " + getNome());
   }
   ```

## 🔄 Comparação com Otros Projetos

| Projeto | Padrão | Foco |
|---------|--------|------|
| **Eldoria** | Herança simples | RPG com especialidades |
| **Polimorfismo** | Herança + Interface | Sistema corporativo |
| **Threads** | Runnable | Concorrência |
| **DesafioUnidade3** | Callable + Fork/Join | Processamento paralelo |

## 💡 Extensões Possíveis

1. **Adicionar novos tipos:**
   - `Estagiario extends Colaborador`
   - `Analista extends Colaborador implements Auditavel`

2. **Salário por tipo:**
   - Bônus específico para cada tipo de colaborador
   - Cálculo progressivo de aumento

3. **Persistência:**
   - Salvar colaboradores em arquivo/banco
   - Carregamento de dados

4. **Relatórios:**
   - Folha de pagamento
   - Produtividade por colaborador

5. **Sistema de permissões:**
   - Gerentes com mais privilégios
   - Desenvolvedores com restrições

---

**Criado em:** javaLearning  
**Tipo:** Exemplo Educacional - Polimorfismo e OOP  
**Conceitos:** Herança, Polimorfismo, Interfaces, Classes Abstratas, Upcasting, Downcasting, instanceof, Validação, Encapsulamento, Collections Polimórficas
