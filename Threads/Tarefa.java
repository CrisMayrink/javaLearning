

/**
 *
 * @author cmaya
 */
public class Tarefa {
    private int id;
    private String descricao;

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    
    public void executar() throws TarefaInvalidaException {
        if (descricao == null || descricao.trim().isEmpty()) {
        throw new TarefaInvalidaException(
            "Tarefa com ID " + id + " possui descrição inválida: " + descricao
        );
    }

        System.out.println("[" + Thread.currentThread().getName() + "] Executando a tarefa " 
            + id + ": " + descricao + ".");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            
            System.out.println("[" + Thread.currentThread().getName() + "] Tarefa " + id  +  " foi interrompida.");
            Thread.currentThread().interrupt();
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Tarefa " + id  +  "  concluida.");    
                        
    }
    
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
