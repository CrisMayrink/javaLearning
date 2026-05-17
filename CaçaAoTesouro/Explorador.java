

/**
 *
 * @author cmaya
 */
public abstract class Explorador {
    protected String nome;
    protected int tipo;
    protected int prioridade;
    protected String tarefa;

    protected Explorador(String nome, int tipo, int prioridade, String tarefa) {
        this.nome = nome;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
    }

    public abstract void executarTarefa()throws TarefaInvalidaException;
    
    public void  exibirStatus(){
        System.out.println("Explorador{" 
                + "nome= " + nome 
                + ", tipo= " + tipo 
                + ", prioridade= " + prioridade 
                + ", tarefa= " + tarefa + "}");
    }
    
    public void getNome(String nome) {
        this.nome = nome;
    }

    public void getTipo(int tipo) {
        this.tipo = tipo;
    }

    public void getPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public void getTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
    
    
}
