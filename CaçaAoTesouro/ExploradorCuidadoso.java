/**
 *
 * @author cmaya
 * implementa a interface Runnable para ser executado em uma thread separada
 */
public class ExploradorCuidadoso extends Explorador implements Runnable{
      
    //construtor que recebe a tarefa

    public ExploradorCuidadoso(String nome, int tipo, int prioridade, String tarefa) {
        super(nome, tipo, prioridade, tarefa);
    }
   
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (tarefa == null || tarefa.trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para Explorador Cuidadoso!");
        }
        System.out.println("Executando cuidadosamente a tarefa: " + tarefa);
    }
        

    /*metodo run() executado qdo a thread é iniciada. trata exceções que podem ocorrer*/
    
    @Override
    public void run(){
      
        try{
             executarTarefa();
        }catch(TarefaInvalidaException e){
           System.err.println("[EXPLORADOR CUIDADOSO] Erro: " + e.getMessage());
           
        }
    }
}
