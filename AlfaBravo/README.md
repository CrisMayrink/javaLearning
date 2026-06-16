# AlfaBravo - Exemplo de Interfaces e Herança em Java

Este projeto demonstra conceitos fundamentais de **interfaces**, **herança de interfaces** e **resolução de nomes** em Java.

## 📋 Estrutura do Projeto

### Interfaces Principais

#### **iExterna.java** (`pctBravo`)
Interface externa que define:
- Constante `NOME = "iExterna"`
- Método abstrato `getNome()`
- Interface interna aninhada `iInterna` com constante `NOME = "iInterna"`

```java
public interface iExterna {
    final String NOME = "iExterna";
    public String getNome();
    interface iInterna {
        final String NOME = "iInterna";
    }
}
```

#### **iAlfa.java** (`pctAlfa`)
Interface que estende `iInterna`:
- Herda de `iExterna.iInterna`
- Constante `NOME = "iAlfa"`
- Demonstra herança entre interfaces

```java
public interface iAlfa extends iInterna {
    final String NOME = "iAlfa";
}
```

#### **List.java**
Interface básica com:
- Constante `NOME = "teste"`
- Método abstrato `getNome()`

### Classes de Implementação

#### **Base.java**
Classe que implementa `List`:
- Implementa o método `getNome()` que retorna `List.NOME`

#### **Concreta.java** (em `Principal.java`)
Classe que implementa múltiplas interfaces:
- Implementa `iAlfa` e `iExterna.iInterna`
- Método `getNome()` retorna `iInterna.NOME` = **"iInterna"**
- Demonstra resolução de nomes quando há múltiplas interfaces com constantes homônimas

### Arquivos Principais

#### **Principal.java** (`pctAlfa`)
Programa que demonstra:
- Implementação múltipla de interfaces
- Resolução de constantes com nomes conflitantes
- Saída esperada: `iInterna`

#### **Principal_1.java** (`pctAlfa`)
Versão simplificada do Principal com a mesma lógica.

## 🎯 Conceitos Demonstrados

### 1️⃣ Herança de Interfaces
- `iAlfa` estende `iExterna.iInterna`
- Interfaces podem estender outras interfaces

### 2️⃣ Interfaces Aninhadas
- `iInterna` é uma interface dentro de `iExterna`
- Acessível como `iExterna.iInterna`

### 3️⃣ Implementação Múltipla
- `Concreta` implementa tanto `iAlfa` quanto `iExterna.iInterna`

### 4️⃣ Resolução de Nomes
- Quando múltiplas interfaces definem `NOME`, Java resolve para `iInterna.NOME`
- Ordem de implementação: `implements iAlfa, iExterna.iInterna`

## 🚀 Como Executar

```bash
# Compilar
javac AlfaBravo/*.java

# Executar
java pctAlfa.Principal
java pctAlfa.Principal_1
```

**Saída esperada:**
```
iInterna
iInterna
```

## 📚 Estrutura de Pacotes

```
AlfaBravo/
├── pctAlfa/
│   ├── Principal.java
│   ├── Principal_1.java
│   └── iAlfa.java
├── pctBravo/
│   └── iExterna.java
├── Base.java
├── List.java
└── README.md
```

## 📝 Notas de Aprendizado

- Interfaces são contratos que definem o comportamento esperado
- Uma classe pode implementar múltiplas interfaces
- Constantes em interfaces são implicitamente `public static final`
- Interfaces aninhadas permitem melhor organização de código relacionado
- A resolução de nomes segue uma ordem clara quando há conflitos

---

**Criado em:** javaLearning  
**Tipo:** Exemplo Educacional  
**Conceitos:** Interfaces, Herança, Polimorfismo
