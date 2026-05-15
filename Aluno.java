/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author cmaya
 */
public class Aluno extends PessoaFisica {
    String matricula;
    
    public Aluno ( String nome , String CPF , String matricula ) {
        super ( nome , CPF );
        this.matricula = matricula;
    }
    public void setMatricula ( String matricula ) {
        this.matricula = matricula;
    }
    public String getMatricula ( ) {
        return matricula;
    }
    @Override
    public boolean equals ( Object obj ) {
        if ( ( this.CPF == ( ( Aluno ) obj ).CPF ) && ( this.matricula == ( ( Aluno ) obj ).matricula ) )
            return true;
        else
            return false;
    }
    @Override
    public int hashCode ( ) {
        return ( this.CPF + this.matricula ).hashCode();
    }
    @Override
    public String toString () {
        return String.format ( "Classe: <%s> | Hash: %s" , "Aluno" , Integer.toHexString ( hashCode() ).toUpperCase() );
    }
}