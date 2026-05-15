
package studingsistweb.colabore;

import Entidades.Colaborador;
import Entidades.Desenvolvedor;
import Entidades.Gerente;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import Entidades.Auditavel;
/**
 *
 * @author cmaya
 */
public class Colabore{
    
    public static void main(String[] args) {
        Locale.setDefault (Locale.US); 
        System.out.println();
        System.out.println("====SISTEMA DE COLABORADORES GODTECH ===");
        System.out.println("\n>> Demonstração de polimorfismo, classes abstratas e casting");
        
        //criando uma arraylist "colaboradores"
        List<Colaborador> colaboradores = new ArrayList<>();
        
        //adicionando colaboradores
        colaboradores.add(new Desenvolvedor("Carlos Santos", 1001, 8000.00));
        colaboradores.add(new Desenvolvedor("Cristina Mayrink", 1002, 7000.00));
        colaboradores.add(new Gerente("Manoel Ferreira", 1000, 12000.00));
        
        System.out.println("\n>> Colaboradores cadastrados com sucesso.");
        
        //demonstrando polimorfismo
        for(int i = 0; i < colaboradores.size(); i++){
            Colaborador colaborador = colaboradores.get(i);//variavel do tipo da superclasse
            
            System.out.println("\n=== COLABORADOR " + (i + 1) + " ===");
            //exibindo dados iniciais
            
            System.out.println();
            colaborador.exibirDados();
            
            //aumentando salario 
            System.out.println("\n>> Aplicando aumento salarial...");
            if(i == 0){
                colaborador.aumentarSalario(15.0);//15%
            }else if (i == 1){
                colaborador.aumentarSalario(12.0); //12%
            }else {
                colaborador.aumentarSalario(10.00);//10%
            }
            
            //executando tarefa - polimorfismo dinamico
            System.out.println("\n>> Salário após reajuste: ");
            colaborador.exibirDados();
            // repetindo o separador "=" 60 vezes e pulando a linha
            System.out.println("\n" + "=".repeat(60) + "\n");
        }
        //demonstrando o uso de interface e casting
        System.out.println("=== DEMONSTRAÇÃO DE INTERFACE E CASTING ===\n");
        
        //encontrando o gerente na lista
        Gerente gerente = null;
        /*vai percorrer a lista e fazer a pergunta se o objeto referenciado pela 
        * variavel colab é uma instancia de gerente.*/

        for(Colaborador colab : colaboradores){
            if (colab instanceof Gerente){
                gerente = (Gerente) colab; //casting explicito para recuperar o tipo gerente
                //após recuperar o tipo gerente, a variavel gerente está referenciando o obj gerente da lista
                break;

            }
        }
        //se a variavel gerente não for nula, vai ser executado o codigo abaixo
        if (gerente != null){
            
            System.out.println(">> Gerente encontrado: " + gerente.getNome());
            
            //usando interface auditavel atraves da referencia da interface:
            Auditavel auditavel = gerente; //upcasting implicito
            
            System.out.println("\n>> Registrando atividades atraves da interface Auditavel: ");
            auditavel.registrarAtividade("Reunião de planejamento estratégico.");
            auditavel.registrarAtividade("Avaliação de desempenho da equipe.");
            auditavel.registrarAtividade("Aprovação de novos projetos.");
            
            System.out.println("\n>> Executando auditoria");
            auditavel.auditar();
            
            //Demonstrando upcasting e downcasting
            System.out.println("\n>> Aplicando bônus através de casting:  ");
            System.out.println("Salário antes do bônus: R$ "+ String.format("%.2f", gerente.getSalario()));
            
            //upcasting para gerente - tratado como colaborador
            Colaborador colaboradorRef = gerente;
            
            //downcasting recuperando o tipo gerente
            Gerente gerenteRef = (Gerente)colaboradorRef;
            
            
            //invocando o metodo protegido da subclasse
            gerenteRef.atribuirBonus(2000.00);
            
            System.out.println("\n>> Dados finais do gerente: ");
            gerente.exibirDados();
        }
        System.out.println("\n==== Fim do Sistema de Colaboradores =====");
    }
}
