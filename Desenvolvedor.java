package Entidades;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author cmaya
 */
public class Desenvolvedor extends Colaborador implements Auditavel {
    //lista para armazenar atividades registradas
    private List<String> atividades;

    public Desenvolvedor(String nome, int matricula, double salario) {
        super(nome, matricula, salario);
        //implementa a list de atividades
        this.atividades = new ArrayList<>();
    }
    /* implementação especifica da execução de tarefa para desenvolvedor
    indica que esta codificando uma nova funcionalidade
    */
    @Override
    public void executarTarefa(){
        System.out.println(getNome() + "está codificando uma nova funcionalidade...");
        System.out.println(">> Nova funcionalidade implementada com sucesso!");
        registrarAtividade("codificou uma nova funcionalidade no sistema.");
    }
    @Override
    public void registrarAtividade(String atividade){
        if (atividade == null || atividade.trim().isEmpty()){
            System.out.println("Erro: Atividade não pode ser nula ou vazia.");
        }else{
            atividades.add(atividade);
            System.out.println("Atividade registrada: " + atividade);
        }
    }

    @Override
    public void auditar() {
        System.out.println("=== AUDITORIA DE ATIVIDADES ===");
        System.out.println("Atividades do Desenvolvedor " + getNome() + ":");
        System.out.println("Total de atividades registradas:  " + atividades.size());
        
        if(atividades.isEmpty()){
            System.out.println("Nenhuma atividade registrada.");
        } else{
            System.out.println("Atividades: ");
            for (int i = 0; i< atividades.size(); i++) {
                System.out.println((i + 1) + "."  + atividades.get(i));
            }    
        }
    }
}
