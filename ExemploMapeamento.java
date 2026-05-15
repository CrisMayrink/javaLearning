/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package studingsistweb.exemplomapeamento;

/**
 *
 * @author cmaya
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExemploMapeamento { 
    public static void main(String[] args) { 
        List<Integer> numeros = List.of(1, 2, 3, 4, 5); 
        
        /*n -> n * n É UMA LAMBDA EXPRESSION entrada do numero n, saída é o quadrado n * n
        O método map aplica essa função a cada elemento da lista e retorna uma nova lista com os resultados.
        */
        List<Integer> quadradoNumeros = map(numeros, n-> n * n); 
        System.out.println("Números Originais: " + numeros); 
        System.out.println("Números ao Quadrado: " + quadradoNumeros); 
    } 
    public static <T, R> List<R> map(List<T> lista, Function<T, R> mapa) { 
        List<R> resultado = new ArrayList<>(); 
        for (T item : lista) { 
            R itemMapeado = mapa.apply(item); 
            resultado.add(itemMapeado); 
        } 
        return resultado; 
    } 
}
    
