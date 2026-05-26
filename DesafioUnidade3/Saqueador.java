import java.util.concurrent.Callable;

public class Saqueador extends Explorador implements Callable<Double> {
    public Saqueador(String nome, int nivel, int energia, Missao missao) {
        super(nome, "Rastreador", nivel, energia, missao);
    }

    @Override
    public Double executarMissao() throws TarefaInvalidaException {
        if (getMissao() == null || getMissao().getDescricao().trim().isEmpty()) {
            throw new TarefaInvalidaException("Missão inválida para Rastreador!");
        }

        System.out.println(getNome() + " está rastreando cuidadosamente em " + getMissao().getLocal());

        double pontos = getMissao().getDificuldade() * 2.0 + getNivel();
        return pontos;
    }

    @Override
    public Double call() {
        try {
            return executarMissao();
        } catch (TarefaInvalidaException e) {
            System.err.println("Erro no Rastreador: " + e.getMessage());
            return 0.0;
        }
    }
}
