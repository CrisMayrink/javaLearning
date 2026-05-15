package Entities;

public class Desenvolvedor extends Funcionario {

    public Desenvolvedor(String nome, String identificador, double salario) {
        super(nome, identificador, salario);
    }
    
    @Override
    public double calcularBonus() {
        return getSalario() * 0.10;
    }
    
    
}
