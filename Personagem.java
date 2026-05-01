package entities;
public abstract class Personagem implements Comparable<Personagem> {
    protected String nome;
    protected int nivel;
    protected int poderBase;

    protected Personagem(String nome, int nivel, int poderBase) {
        this.nome = nome;
        this.nivel = nivel;
        this.poderBase = poderBase;
    }

    // Getters
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getPoderBase() { return poderBase; }

    // Setters com validação
    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public void setNivel(int nivel) {
        if (nivel > 0) {
            this.nivel = nivel;
        }
    }

    public void setPoderBase(int poderBase) {
        if (poderBase > 0) {
            this.poderBase = poderBase;
        }
    }

    // Método abstrato: cada subclasse define sua própria lógica
    public abstract int calcularPoderTotal();
    
    public abstract void usarHabilidade();            

    public abstract void exibirStatus();
    
    
    @Override
    public int compareTo(Personagem outro) {
        return Integer.compare(this.nivel, outro.nivel);
    }

    @Override
    public String toString() {
        return nome + " (Nível:  " + nivel + ", Poder Base: " + poderBase + ")";
    }
}


