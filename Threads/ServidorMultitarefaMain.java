import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *classe main que simula um servidor multarefa. 
 * demonstra o uso de threads, prioridades, tipos user e daemon e execeções personalisadas.
 * @author cmaya
 */
public class ServidorMultitarefaMain {
    
    public static void main(String[] args){
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.out, true, StandardCharsets.UTF_8));    

        System.out.println("====== SIMULADOR DE SERVIDOR MULTITAREFA ======");
        System.out.println("== Demonstrando threads, prioridades e exceções personalizaas ==\n");
        
        //criando uma lista de tarefas
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(new Tarefa (1, "Processar requisição HTTP"));
        tarefas.add(new Tarefa (2, "Conectar ao banco de dados..."));
        tarefas.add(new Tarefa (3, ""));//tarefa invalida para demonstrar exceção
        tarefas.add(new Tarefa (4, "Enviar e-mail de notificação..."));
        tarefas.add(new Tarefa (5, "Gerar relatório mensal"));
        tarefas.add(new Tarefa (6, null)); //invalida
        
        //é preciso de uma lista de threads para executar as tarefas
        List<Thread> threads = new ArrayList<>();
        
        //criando tarefas com diferentes processadores e prioridades
        for(int i = 0; i < tarefas.size(); i++){
            Tarefa tarefa = tarefas.get(i);
            Thread thread;
            
            //alterna entre os processadores
            if(i%2==0){
                //processador rapido comprioridade maxima
                thread = new Thread(new ProcessadorRapido(tarefa), "Thread-Rápida" + (i+1));
                thread.setPriority(Thread.MAX_PRIORITY);
            }else{
                //procesador lento com prioridade minima
                thread = new Thread(new ProcessadorLento(tarefa), "Thread-Lenta" + (i+1));
                thread.setPriority(Thread.MIN_PRIORITY);
            }
            //configurando threads daemon - tarefas de apoio
            if(i>=4){
                thread.setDaemon(true);
                System.out.println("Thread " + thread.getName() + "configurada como DAEMON");
            }
            threads.add(thread);
        }
        
        //exibindo informações das threads antes da execução
        System.out.println("\n=== INFORMAÇÕES DAS THREADS ===");
        for (Thread thread: threads){            
            System.out.println("Thread: " + thread.getName()
            + " | Prioridade: " + thread.getPriority()
            +  " | Daemon: " + thread.isDaemon()
            + " | Status: " + thread.getState());
        }
        //iniciando todas a threads
        System.out.println("\n === INICIANDO A EXECUÇÃO DAS THREADS ===");
        for (Thread thread: threads){
            thread.start();
            System.out.println("Thread: " + thread.getName() + " iniciada -  Status: " + thread.getState());
        }
                       
        //aguardando a conclusão das threads não Daemon
        System.out.println("\n === AGUARDANDO A CONCLUSÃO DAS THREADS ===");
        for(Thread thread : threads){
            if (!thread.isDaemon()){
                try{
                    thread.join();//se não for daemon espera até o fim da execução das threds
                    System.out.println("Thread " + thread.getName() + " finalizada - Status: " + thread.getState());
                }catch (InterruptedException e){
                    System.err.println("Interrupção ao aguardar thread: " + e.getMessage());
                }
            }
        }
        //verificando o estado final das tarefas
        System.out.println("\n ==== Estado final das Threads ====");
        for(Thread thread: threads){   
            System.out.println("Thread: " + thread.getName()
                    + " | Estado: " + thread.getState()
                    +  " | Viva: : " + thread.isAlive());
        }
        
        System.out.println("\n =====SERVIDRO MULTITAREFA FINALIZADO ====");
    }
         
    
}
