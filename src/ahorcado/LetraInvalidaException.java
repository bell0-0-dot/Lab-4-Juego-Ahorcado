/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado;

/**
 *
 * @author vasqu
 */
public class LetraInvalidaException extends Exception{

    public LetraInvalidaException(char letra) {
         super("Error. La letra ingresa no pueder ser un simbolo o numero");
    }
    
    
   
    
}
