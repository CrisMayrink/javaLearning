
public interface Identificador {
   
    final int TAMANHO_MAX = 21;
    boolean validarID (String id);
    void formatarID (int tipo);
    void atualizarID (String id);
    String recuperarID ();
}
