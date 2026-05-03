package Entities;

import java.util.Objects;


public abstract class Funcionario {
        private String nome;
        private String identificador;
        private double salario;

    protected Funcionario(String nome, String identificador, double salario) {
        this.nome = nome;
        this.identificador = identificador;
        this.salario = salario;
    }
              
     
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
       if (salario < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo!");
          }
    }    
    public abstract double calcularBonus();

    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", identificador=" + identificador + ", salario=" + salario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.identificador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        return Objects.equals(this.identificador, other.identificador);
    }
    
        
    
}
    

