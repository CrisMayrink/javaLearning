/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author cmaya
 */
public class Mago extends Personagem {

    public Mago(String nome, int nivel, int poderBase) {
        super(nome, nivel, poderBase);
    }

    @Override
    public int calcularPoderTotal() {
        return (nivel * poderBase) + 50;//bonus de magia
    }
    
    @Override
    public void usarHabilidade(){
        System.out.println(nome + " - Usar névoa negra! ");
    }
    
    @Override
    public void exibirStatus(){
        System.out.println("Mago:  " + nome +
                " Nivel: " + nivel +
                " Poder Base: " + poderBase +
                " Poder Total: " + calcularPoderTotal()     
        
        );
    }
}
