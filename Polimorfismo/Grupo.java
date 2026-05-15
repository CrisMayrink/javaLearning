package entities;

import java.util.ArrayList;
import java.util.Collections;


public class Grupo {
    private ArrayList<Personagem> membros;

    public Grupo() {
        this.membros = new ArrayList<>();
    }

    // Getter da lista de membros
    public ArrayList<Personagem> getMembros() {
        return membros;
    }

    // Setter da lista de membros
    public void setMembros(ArrayList<Personagem> membros) {
        if (membros != null) {
            this.membros = membros;
        }
    }

    // Adiciona um personagem ao grupo
    public void adicionarPersonagem(Personagem p) {
        if (p != null) {
            membros.add(p);
        }
    }

    // Lista todos os personagens do grupo
    public void listarPersonagens() {
        System.out.println("Personagens do grupo:");
        for (Personagem p : membros) {
            System.out.println(p + " | Poder Total: " + p.calcularPoderTotal());
        }
    }

    // Realiza batalha entre dois personagens
    public void batalhar(Personagem a, Personagem b) {
        int poderA = a.calcularPoderTotal();
        int poderB = b.calcularPoderTotal();

        if (poderA > poderB) {
            System.out.println(a.getNome() + " venceu! Poder total: " + poderA);
        } else if (poderB > poderA) {
            System.out.println(b.getNome() + " venceu! Poder total: " + poderB);
        } else {
            System.out.println("Empate! Ambos têm poder total: " + poderA);
        }
    }

    // Ordena personagens por nível
    public void ordenarPorNivel() {
        Collections.sort(membros);
    }
}
