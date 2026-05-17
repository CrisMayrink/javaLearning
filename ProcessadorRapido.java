/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cmaya
 * implementa a interface Runnable para ser executado em uma thread separada
 */
public class ProcessadorRapido implements Runnable{
    private Tarefa tarefa;
    
    //construtor que recebe a tarefa

    public ProcessadorRapido(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    /*metodo run() executado qdo a thread é iniciada. trata exceções que podem ocorrer*/
    
    @Override
    public void run(){
        try{
            System.out.println("[PROCESSADOR RAPIDO] Inciando processamento da terefa" + tarefa.getId());
            tarefa.executar();
            System.out.println("[PROCESSADOR RAPIDO]Processamento da terefa" + tarefa.getId() + "finalizado.");
        }catch(TarefaInvalidaException e){
            System.err.println("[PROCESSADOR RAPIDO] Erro: " + e.getMessage());
        }
    }
    
}


