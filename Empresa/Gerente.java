package Entities;

public class Gerente extends Funcionario{

    public Gerente(String nome, String identificador, double salario) {
        super(nome, identificador, salario);
    }
    
    
    @Override
    public double calcularBonus() {
        return getSalario() * 0.20;
        
    }
    
}
