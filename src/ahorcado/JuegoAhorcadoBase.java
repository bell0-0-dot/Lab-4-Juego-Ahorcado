
package ahorcado;

import java.util.List;


public abstract class JuegoAhorcadoBase implements JuegoAhorcado{
    protected String palabraSecreta;
    protected String palabraIngresada;
    protected int intentos;
    protected List<Character> letrasIngresadas;
    protected boolean[]intentosAcertados;
    
    public JuegoAhorcadoBase(){
        this.intentos=6;
    }
    
    protected abstract void actualizarPalabraIngresada(char letra);
    protected abstract boolean verificarLetra(char letra);
    protected abstract boolean esGanador();

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public void setPalabraSecreta(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
    }

    public String getPalabraIngresada() {
        return palabraIngresada;
    }

    public void setPalabraIngresada(String palabraIngresada) {
        this.palabraIngresada = palabraIngresada;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public List<Character> getLetrasIngresadas() {
        return letrasIngresadas;
    }

    public void setLetrasIngresadas(List<Character>letrasIngresadas) {
        this.letrasIngresadas = letrasIngresadas;
    }

   

    public boolean[] getIntentosAcertados() {
        return intentosAcertados;
    }

    public void setIntentosAcertados(boolean[] intentosAcertados) {
        this.intentosAcertados = intentosAcertados;
    }
 
    
}
