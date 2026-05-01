/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author cmaya
 */
public class Guerreiro extends Personagem{

    public Guerreiro(String nome, int nivel, int poderBase) {
        super(nome, nivel, poderBase);
    }
    @Override
    public int calcularPoderTotal(){
        return (nivel*poderBase) + 30;
    }
    @Override
    public void usarHabilidade(){
        System.out.println(nome + " -  Usar Golpe mortal! ");
    }
    
    @Override
    public void exibirStatus(){
        System.out.println("Guerreiro:  " + nome +
                " Nivel: " + nivel +
                " Poder Base: " + poderBase +
                " Poder Total: " + calcularPoderTotal()     
        
        );
    }
}
