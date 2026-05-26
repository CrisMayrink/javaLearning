import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Classe main da tarefa evoluindo caça ao tesouro unidade 3
 * Demonstra o uso de threads, Callable, Runnable e ForkJoin
 * @author cmaya
 */
public class ExplorandoMain {
    
    public static void main(String[] args) throws Exception {
         

        System.out.println("====== SIMULADOR DE CAÇA AO TESOURO ======");
        System.out.println("== Demonstrando threads, callable e runnable, forkjoin ==\n");
        
        // Criando lista de exploradores
        ArrayList<Explorador> exploradores = new ArrayList<>();
        
        // Instanciando dois Rastreadores
        exploradores.add(new Rastreador("Ricardo", 3, 8, new Missao("Descobrir mapa do tesouro perdido", "Peru", 3)));
        exploradores.add(new Rastreador("Augusto", 2, 9, new Missao("Localizar equipe para a expedição", "Peru", 3)));
        
        // Instanciando dois Saqueadores
        exploradores.add(new Saqueador("Fabricio", 4, 9, new Missao("Reunir equipamentos para expedição", "Peru", 3)));
        exploradores.add(new Saqueador("Leonardo", 4, 8, new Missao("Reunir mantimentos para expedição", "Peru", 3)));
        
        /* Executor com duas threads*/
        ExecutorService executor = Executors.newFixedThreadPool(2);
         
        // Submeter exploradores ao executor
        List<Future<Double>> resultados = new ArrayList<>();
        for (Explorador e : exploradores) {
            resultados.add(executor.submit((Callable<Double>) e));
        }

        // Aguardar resultados e preencher array de pontos
        double[] pontos = new double[exploradores.size()];
        for (int i = 0; i < resultados.size(); i++) {
            double valor = resultados.get(i).get(); // Future.get() retorna Double
            pontos[i] = valor;
            
            System.out.println("Explorador: " + exploradores.get(i).getNome());
            System.out.println("Especialidade: " + exploradores.get(i).getEspecialidade());
            System.out.println("Missão: " + exploradores.get(i).getMissao().getDescricao());
            System.out.println("Pontos obtidos: " + valor + "\n");
        }
            
        executor.shutdown();

        // Consolidar pontos com Fork/Join
        ForkJoinPool pool = new ForkJoinPool();
        SomaPontos soma = new SomaPontos(pontos, 0, pontos.length);
        double total = pool.invoke(soma);

        System.out.println("\nPontuação total consolidada: " + total);
    }
}
