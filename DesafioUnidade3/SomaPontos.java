import java.util.concurrent.RecursiveTask;
/**
 *
 * @author cmaya
 */
public class SomaPontos extends RecursiveTask<Double>{
   //o recursiveTask tem que ser declarado com double para evitar erros no join
    //atributos
    protected double[] pontos;
    protected int pontosIniciais;
    protected int pontosFinais;
    
    //construtor
    public SomaPontos(double[] pontos, int pontosIniciais, int pontosFinais) {
        this.pontos = pontos;
        this.pontosIniciais = pontosIniciais;
        this.pontosFinais = pontosFinais;
    }
     @Override
        protected Double compute() {
            int tamanho = pontosFinais - pontosIniciais;
            if (tamanho <= 2) {
                double soma = 0;
                for (int i = pontosIniciais; i < pontosFinais; i++) {
                    soma += pontos[i];
                }
                return soma;
            } else {
                int meio = (pontosIniciais + pontosFinais) / 2;
                SomaPontos esquerda = new SomaPontos(pontos, pontosIniciais, meio);
                SomaPontos direita = new SomaPontos(pontos, meio, pontosFinais);

                
                //execução em paralelo,fork abre varias theads que vão se encontrar de novo no join
                esquerda.fork();
                Double somaDireita = direita.compute();
                Double somaEsquerda = esquerda.join();

                return somaDireita + somaEsquerda;
        }
        
    }
            
}
