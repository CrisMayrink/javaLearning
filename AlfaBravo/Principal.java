
package pctAlfa;

import pctBravo.iExterna;
class Concreta implements iAlfa, iExterna.iInterna {
    public String getNome () {
        return iAlfa.NOM
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                ;
    }
}
public class Principal {
    public static void main(String[] args) {
        Concreta conc = new Concreta();
        System.out.println(conc.getNome()); // saída: iInterna
    }
}
