
package studingsistweb.desafio;

import entities.Arena;
import entities.Arqueiro;
import entities.Grupo;
import entities.Guerreiro;
import entities.Mago;

/**
 *
 * @author cmaya
 */
public class Desafio {
    public static void main(String[] args) {
        // Criando o primeiro grupo
        Grupo  grupo1 = new Grupo();
        Mago mayrink = new Mago("Mayrink", 10, 30);
        Arqueiro arthur = new Arqueiro("Arthur", 7, 25);
        grupo1.adicionarPersonagem(mayrink);
        grupo1.adicionarPersonagem(arthur);
        
        // Criando o segundo grupo
        Grupo grupo2 = new Grupo();
        Guerreiro lector = new Guerreiro("Lector", 8, 40);
        Mago kovalic = new Mago("Kovalic", 9, 28);
        grupo2.adicionarPersonagem(lector);
        grupo2.adicionarPersonagem(kovalic);

        // Listando personagens dos grupos
        System.out.println("=== Grupo 1 ===");
        grupo1.listarPersonagens();
        System.out.println("\n=== Grupo 2 ===");
        grupo2.listarPersonagens();

        // Criando a arena e iniciando batalhas
        Arena arena = new Arena();
        System.out.println("\n=== Batalhas na Arena ===");
        arena.batalharGrupos(grupo1, grupo2);
        
        // Usando exibirStatus() (implementado em cada subclasse)
        System.out.println("\n=== Teste com exibirStatus() ===");
        mayrink.exibirStatus();
        arthur.exibirStatus();
        lector.exibirStatus();
        kovalic.exibirStatus();

        // Usando usarHabilidade()
        System.out.println("\n=== Teste com usarHabilidade() ===");
        mayrink.usarHabilidade();
        arthur.usarHabilidade();
        lector.usarHabilidade();
        kovalic.usarHabilidade();
    }
}