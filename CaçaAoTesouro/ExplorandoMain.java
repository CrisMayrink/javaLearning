import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *classe main da tarefa CAÇA AO TESOURO
 * demonstra o uso de threads, prioridades, tipos user e daemon e execeções personalisadas.
 * @author cmaya
 */
public class ExplorandoMain {
    
    public static void main(String[] args){
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.out, true, StandardCharsets.UTF_8));    

        System.out.println("====== SIMULADOR DE CAÇA AO TESOURO ======");
        System.out.println("== Demonstrando threads, prioridades ==\n");
        
        //criando uma lista de EXPLORADORES
        ArrayList<Thread> threads = new ArrayList<>();
        
        // Instanciando dois ExploradorRapido
        ExploradorRapido rapido1 = new ExploradorRapido("Ricardo", 1, 10, "Explorar cavernas");
        ExploradorRapido rapido2 = new ExploradorRapido("Augusto", 1, 9, "Mapear floresta");

        // Instanciando dois ExploradorCuidadoso
        ExploradorCuidadoso cuidadoso1 = new ExploradorCuidadoso("Cris", 2, 5, "Analisar fóssei                 s");
        ExploradorCuidadoso cuidadoso2 = new ExploradorCuidadoso("Rafa", 2, 4, "");
        
        
        //é preciso de uma lista de threads para executar as tarefas
        Thread tRapido1 = new Thread(rapido1);
        Thread tRapido2 = new Thread(rapido2);
        Thread tCuidadoso1 = new Thread(cuidadoso1);
        Thread tCuidadoso2 = new Thread(cuidadoso2);
        
        //criando  prioridades
        tRapido1.setPriority(Thread.MAX_PRIORITY);
        tRapido2.setPriority(Thread.MAX_PRIORITY);
        tCuidadoso1.setPriority(Thread.MIN_PRIORITY);
        tCuidadoso2.setPriority(Thread.MIN_PRIORITY);
        
        //configurando uma thread daemon
        tRapido2.setDaemon(true);
        
        //adicionando à lista
        threads.add(tRapido1);
        threads.add(tRapido2);
        threads.add(tCuidadoso1);
        threads.add(tCuidadoso2);
        
        // Iniciando todas as threads
        for (Thread t : threads) {
            t.start();
        }
        
        System.out.println("\n ===== Caça ao tesouro finalizada ====");
    }
         
        
}
