package studingsistweb.empresa2;

import Entities.Desenvolvedor;
import Entities.Funcionario;
import Entities.Gerente;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 *
 * @author cmaya
 */
public class Empresa2 {

   public static void main(String[] args) {
        Locale.setDefault(Locale.US);                
        List<Funcionario> funcionarios = new ArrayList<>();
        
        funcionarios.add(new Gerente("João", "G001", 20000));
        funcionarios.add(new Desenvolvedor("Maria", "D001", 10000));
        // Para cada funcionário
        for (Funcionario f : funcionarios) {
            // Exibe informações
            System.out.println(f.toString());

            // Calcula e mostra bônus
            System.out.println("Bônus: " + f.calcularBonus());

            // Verifica tipo real com instanceof
            if (f instanceof Gerente) {
                System.out.println("O funcionário " + f.getNome() + " é um Gerente.");
            } else if (f instanceof Desenvolvedor) {
                System.out.println("O funcionário " + f.getNome() + " é um Desenvolvedor.");
            }

            System.out.println("--------------------------------------------------");
        }

        // Comparação com equals()
        Funcionario f1 = new Gerente("João", "G001", 20000);
        Funcionario f2 = new Gerente("Maria", "D001", 10000);
        
        System.out.println("Comparando f1 e f2: " + (f1.equals(f2) ? "São iguais" : "São diferentes"));
    }
}
