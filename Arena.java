/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author cmaya
 */
public class Arena {

    // Realiza batalhas entre dois grupos
    public void batalharGrupos(Grupo g1, Grupo g2) {
        int tamanho = Math.min(g1.getMembros().size(), g2.getMembros().size());

        System.out.println("Iniciando batalhas entre grupos...");

        for (int i = 0; i < tamanho; i++) {
            Personagem p1 = g1.getMembros().get(i);
            Personagem p2 = g2.getMembros().get(i);

            System.out.println("Batalha " + (i + 1) + ": " + p1.getNome() + " vs " + p2.getNome());
            g1.batalhar(p1, p2);
            System.out.println("-------------------------");
        }
    }
}

