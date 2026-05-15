/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author cmaya
 */
public class Gerente extends Colaborador implements Auditavel {
    //lista para armazenar atividades registradas
    private List<String> atividades;

    public Gerente(String nome, int matricula, double salario) {
        super(nome, matricula, salario);
        //implementa a list de atividades
        this.atividades = new ArrayList<>();
    }

    public List<String> getAtividades() {
        return atividades;
    }
     
    
    
    /* implementação especifica da execução de tarefa para Gerente
    indica que esta coordenando uma reunião com a equipe
    */
    @Override
    public void executarTarefa(){
        System.out.println(getNome() + "está coordenando uma reunião com a equipe!");
        System.out.println(">> Definindo agenda da reunião ...");
        System.out.println(">> Apresentando objetivos...");
        System.out.println(">> Delegando tarefas ...");
        System.out.println(">> Reunião finalizada com sucesso!");
        registrarAtividade("Gerenciou a equipe.");
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
        System.out.println("Atividades do gerente " + getNome() + ":");
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
