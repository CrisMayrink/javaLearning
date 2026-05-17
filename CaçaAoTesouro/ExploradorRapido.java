/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cmaya
 * implementa a interface Runnable para ser executado em uma thread separada
 */
public class ExploradorRapido extends Explorador implements Runnable{
      
    //construtor que recebe a tarefa

    public ExploradorRapido(String nome, int tipo, int prioridade, String tarefa) {
        super(nome, tipo, prioridade, tarefa);
    }
   
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (tarefa == null || tarefa.trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para Explorador Rápido!");
        }
        System.out.println("Executando rapidamente a tarefa: " + tarefa);
    }
        

    /*metodo run() executado qdo a thread é iniciada. trata exceções que podem ocorrer*/
    
    @Override
    public void run(){
      
        try{
             executarTarefa();
        }catch(TarefaInvalidaException e){
           System.err.println("[EXPLORADOR RAPIDO] Erro: " + e.getMessage());
           
        }
    }
}


