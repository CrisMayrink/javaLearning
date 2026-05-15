
package studingsistweb.pincipal;

import Entidades.Aluno;
import Entidades.PessoaFisica;

/**
 *
 * @author cmaya
 */
public class Principal {
    public static void main ( String args [ ] ) {
        PessoaFisica PF1 = new PessoaFisica ( "Ricardo" , "12345678998" );
        PessoaFisica PF2 = new PessoaFisica ( "Ricardo" , "98774112336" );
        PessoaFisica PF3 = new PessoaFisica ( "Carlos" , "98774112336" );
        Aluno A1 = new Aluno ( "Ricardo" , "12345678998" , "AL001" );
        Aluno A2 = new Aluno ( "Ricardo" , "12345678998" , "AL001" );
        Aluno A3 = new Aluno ( "Carlos" , "12345678998" , "AL002" );
        //PF1 = PF2 ?
        System.out.println ( "Comparando PF1 e PF2" );
        System.out.println ( "  PF1.equals ( PF2 ) -> " + PF1.equals ( PF2 ) );
        //PF1 = PF3 ?
        System.out.println ( "Comparando PF1 e PF3" );
        System.out.println ( "  PF1.equals ( PF3 ) -> " + PF1.equals ( PF3 ) );
        //PF2 = PF3 ?
        System.out.println ( "Comparando PF2 e PF3" );
        System.out.println ( "  PF2.equals ( PF3 ) -> " + PF2.equals ( PF3 ) );
        System.out.println ( "  PF2 == PF3 -> " + ( PF2 == PF3 ) );
        //A1 = A2 ?
        System.out.println ( "Comparando A1 e A2" );
        System.out.println ( "  A1.equals ( A2 ) -> " + A1.equals ( A2 ) );
        System.out.println ( "  A1 == A2 -> " + ( A1 == A2 ) );
        //A1 = A3 ?
        System.out.println ( "Comparando A1 e A3" );
        System.out.println ( "  A1.equals ( A3 ) -> " + A1.equals ( A3 ) );
        //PF2 = PF3 ?
        System.out.println ( "Comparando A2 e A3" );
        System.out.println ( "  A2.equals ( A3 ) -> " + A2.equals ( A3 ) );
        //PF1 = A1 ?
        System.out.println ( "Comparando PF1 e A1" );
        System.out.println ( "  PF1.equals ( A1 ) -> " + PF1.equals ( A1 ) );
        System.out.println ( "  PF1 == A1 -> " + ( PF1 == A1 ) );
        //A1 = PF1 ?
        System.out.println ( "Comparando PF1 e A1" );
        System.out.println ( "    PF1.equals ( A1 ) -> " + PF1.equals ( A1 ) );
        //Imprime o identificador do objeto
        System.out.println ( "[PF1]: "+PF1 + " [PF2]: " + PF2 + " [PF3]: " + PF3 );
        System.out.println ( "[A1]: " + A1 + " [A2]: " + A2 + " [A3]: " + A3 );
    }    
}
