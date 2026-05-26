

/**
 evoluindo usando forkJoin e agendador
 * @author cmaya
 */
public abstract class Explorador {
    //atributos
    protected String nome;
    protected String especialidade;
    protected int nivel;
    protected int energia;
    protected Missao missao;//objeto da classe Missao
    
//construtor
    protected Explorador(String nome, String especialidade, int nivel, int energia, Missao missao) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.energia = energia;
        this.missao = missao;
    }
    //getters
    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public int getNivel() {
        return nivel;
    }

    public int getEnergia() {
        return energia;
    }

    public Missao getMissao() {
        return missao;
    }
    
    
    // metodo abstrato que será sobrescrito pela logica das subclasses
    public abstract Double executarMissao() throws TarefaInvalidaException;
    
    // Evolução de nível
    public void subirNivel() {
        nivel++;
    }
    //exibição dos dados do  explorador
    @Override
    public String toString() {
        return "Explorador{" +
               "nome='" + nome + '\'' +
               ", especialidade='" + especialidade + '\'' +
               ", nivel=" + nivel +
               ", energia=" + energia +
               ", missao='" + missao + '\'' +
               '}';
    }          
    
}
