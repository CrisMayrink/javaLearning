/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.text.DecimalFormat;

/**
 *
 * @author cmaya
 */
public abstract class Colaborador {
    private String nome;
    private int matricula;
    private double salario;

    protected Colaborador(String nome, int matricula, double salario) {
    this.nome = nome;
    this.matricula = matricula;
    setSalario(salario); // validação centralizada
}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }
    
    public double getSalario() {
        return salario;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public void setSalario(double salario) {
    if (salario < 0) {
        throw new IllegalArgumentException("O salário não pode ser negativo!");
    }
    this.salario = salario;
    }


    public void aumentarSalario(double percentual) {
        if (percentual <= 0) {
            throw new IllegalArgumentException("O percentual deve ser positivo!");
         }
        double aumento = salario * (percentual / 100);
        salario += aumento;
        System.out.println(String.format("Salário de %s aumentado em %.2f%%. Novo Salário: R$ %.2f",
            nome, percentual, salario));
    }
    public abstract void executarTarefa();

    public void exibirDados() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("=== Dados do Colaborador ===");
        System.out.println("Nome: " + nome 
            + " | Matrícula: " + matricula
            + " | Salário: R$ " + df.format(salario));
        System.out.println("Tipo:  " + this.getClass().getSimpleName());
        System.out.println("============================");
    }
     public void atribuirBonus(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do bônus deve ser positivo!");
        } else{
            salario += valor;
            System.out.println("Bônus de R$ " + String.format(" %.2f", valor) + 
                    "atribuido a : " +  nome + ". Novo salário: R$  " + 
                    String.format(" %.2f", salario));
        }
    } 
    
}
