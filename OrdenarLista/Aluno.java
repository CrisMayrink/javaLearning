/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author cmaya
 */
public class Aluno implements Comparable<Aluno>{
    String nome;
    double notaFinal;

    public Aluno(String nome, double notaFinal) {
        this.nome = nome;
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return "Aluno{" + "nome=" + nome + ", notaFinal=" + notaFinal + '}';
    }

    @Override
    public int compareTo(Aluno outroAluno) {
        //ordena em ordem crscente pela nota final
        if (this.notaFinal < outroAluno.notaFinal){
            return -1;
        }else if(this.notaFinal > outroAluno.notaFinal){
            return 1;
        }else{
            return 0;
        }
       
    }
      
    
}
