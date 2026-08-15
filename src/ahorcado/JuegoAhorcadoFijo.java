
package ahorcado;

import java.util.ArrayList;
import java.util.List;
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase{
    

    public JuegoAhorcadoFijo(String palabra) {
        inicializarPalabraSecreta(palabra);
        letrasIngresadas=new ArrayList<>();
        String guion="";
        for (int i = 0; i < palabraSecreta.length(); i++) {
            guion=guion+"_";
        }
        this.palabraIngresada=guion;
    }
    
    
    

    @Override
    protected void actualizarPalabraIngresada(char letra) {
        char[]ingresadas=palabraIngresada.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if(palabraSecreta.charAt(i)==letra){
                ingresadas[i]=letra;
            }
        }
        palabraIngresada=new String (ingresadas);
        
    }

    @Override
    protected boolean verificarLetra(char letra) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            char letraSecreta=palabraSecreta.charAt(i);
            if(letraSecreta==letra){
                return true;
                
            }
        }
        return false;
        
    }

    @Override
    protected boolean esGanador() {
        return palabraIngresada.equalsIgnoreCase(palabraSecreta);
      
    }

    @Override
    public void inicializarPalabraSecreta(String palabra) {
        this.palabraSecreta=palabra.toLowerCase();
    }

    @Override
    public void jugar(char letra) throws LetraInvalidaException, LetraRepetidaException{
        if(!Character.isLetter(letra)){
            throw new LetraInvalidaException(letra);
            
        }
        
        if(letrasIngresadas.contains(letra)){
            throw new LetraRepetidaException(letra);
        }
        letrasIngresadas.add(letra);
        if(verificarLetra(letra)){
             actualizarPalabraIngresada(letra);
        }else{
            intentos--;
        }
        
       
    }

    
}
