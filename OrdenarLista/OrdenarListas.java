/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package studingsistweb.ordenarlistas;

import entities.Aluno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenarListas {

    public static void main(String[] args) {
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno("Maria", 5.5));
        alunos.add(new Aluno("João", 8.5));        
        alunos.add(new Aluno("Ana", 7.5));
        alunos.add(new Aluno("Pedro", 9.5));
        alunos.add(new Aluno("Ricardo", 6.5));        
                
        System.out.println("Alunos antes da ordenação: ");
        for (Aluno aluno : alunos){
            System.out.println(aluno);
        }
        
        Collections.sort(alunos);
        
        System.out.println("\nAlunos depois da ordenação por nota: " );
        for (Aluno  aluno: alunos){
            System.out.println(aluno);
        }
    }
}
