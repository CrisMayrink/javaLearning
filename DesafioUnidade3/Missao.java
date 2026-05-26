/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cmaya
 */
public final class Missao {
    private final String descricao;
    private final String local;
    private final int dificuldade;

    public Missao(String descricao, String local, int dificuldade) {
        this.descricao = descricao;
        this.local = local;
        this.dificuldade = dificuldade;
    }

    public String getDescricao() { return descricao; }
    public String getLocal() { return local; }
    public int getDificuldade() { return dificuldade; }

    @Override
    public String toString() {
        return "Missao{" +
                "descricao='" + descricao + '\'' +
                ", local='" + local + '\'' +
                ", dificuldade=" + dificuldade +
                '}';
    }
}
