/**
 *
 * @author cmaya
 * implementa a interface Runnable para ser executado em uma thread separada
 */
public class ProcessadorLento implements Runnable{
    private Tarefa tarefa;
    
    //construtor que recebe a tarefa

    public ProcessadorLento(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    /*metodo run() executado qdo a thread é iniciada. trata exceções que podem ocorrer*/
    
    @Override
    public void run(){
        try{
            System.out.println("[PROCESSADOR LENTO] Inciando processamento da terefa" + tarefa.getId());
            tarefa.executar();
            System.out.println("[PROCESSADOR LENTO]Processamento da terefa" + tarefa.getId() + "finalizado.");
        }catch(TarefaInvalidaException e){
            System.err.println("[PROCESSADOR LENTO] Erro: " + e.getMessage());
        }
    }
    
}
