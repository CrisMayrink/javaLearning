/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author cmaya
 */
public class PessoaFisica {
    protected String CPF;
    protected String nome;
        
    public PessoaFisica ( String nome , String CPF ) {
        this.nome = nome;
        setCPF ( CPF );
    }
    public void setNome ( String nome ) {
        this.nome = nome;
    }
    public String getNome ( ) {
        return nome;
    }
    public void setCPF ( String CPF ) {
        if ( validarCPF ( CPF ) )
            this.CPF = CPF;
        else
            System.out.println ( "CPF invalido!" );
    }
    public String getCPF ( ) {
        return CPF;
    }
    @Override
    public boolean equals ( Object obj ) {
        if ( this.CPF == ( ( PessoaFisica ) obj ).CPF )
            return true;
        else
            return false;
    }
    @Override
    public int hashCode ( ) {
        return CPF.hashCode();
    }
    @Override
    public String toString () {
        return String.format ( "Classe: <%s> | Hash: %s" , "PessoaFisica" , Integer.toHexString ( this.hashCode() ).toUpperCase() );
    }    
    private boolean validarCPF ( String CPF ) {
        
        
        //verifica sequência de dígitos iguais e tamanho (11 dígitos)
        if ( ! ( CPF.chars ( ).allMatch ( Character::isDigit ) ) ) {
            System.out.println ( "CPF possui caracteres não numéricos!" );
            return false;
        } 
        else if ( ( CPF.length() != 11 ) || CPF.matches( "[0]{11}|[1]{11}|[2]{11}|[3]{11}|[4]{11}|[5]{11}|[6]{11}|[7]{11}|[8]{11}|[9]{11}" ) )
            return false;
        return true;
    }
}

