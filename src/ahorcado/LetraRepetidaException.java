
package ahorcado;

public class LetraRepetidaException extends Exception{

    public LetraRepetidaException(char letra) {
        super("La letra ya fue ingresada");
    }
    
    
}
