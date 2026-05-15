/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author cmaya
 */
public class Arqueiro extends Personagem {

    public Arqueiro(String nome, int nivel, int poderBase) {
        super(nome, nivel, poderBase);
    }
    @Override
    public int calcularPoderTotal(){
        return (nivel * poderBase)+ (nivel*5); //bonus de precisão
    }
    @Override
    public void usarHabilidade(){
        System.out.println(nome + " - Usar flexa fantasma! ");
    }
    
    @Override
    public void exibirStatus(){
        System.out.println("Arqueiro:  " + nome +
                " Nivel: " + nivel +
                " Poder Base: " + poderBase +
                " Poder Total: " + calcularPoderTotal()     
        
        );
    }
}
