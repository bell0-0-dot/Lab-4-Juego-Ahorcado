package ahorcado;

import java.util.ArrayList;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    public JuegoAhorcadoFijo(String palabra) {
        super();
        this.letrasIngresadas = new ArrayList<>();
        inicializarPalabraSecreta(palabra);
        
        StringBuilder guion = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            guion.append("_");
        }
        this.palabraIngresada = guion.toString();
    }

    @Override   
    public void inicializarPalabraSecreta(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) {
            this.palabraSecreta = "JAVA";
        } else {
            this.palabraSecreta = palabra.trim().toUpperCase();
        }
    }

    @Override
    public void actualizarPalabraIngresada(char letra) {
        char[] ingresadas = palabraIngresada.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                ingresadas[i] = letra;
            }
        }
        this.palabraIngresada = new String(ingresadas);
    }

    @Override
    public boolean verificarLetra(char letra) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            char letraSecreta = palabraSecreta.charAt(i);
            if (letraSecreta == letra) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean esGanador() {
        return palabraIngresada.equalsIgnoreCase(palabraSecreta);
    }

    @Override
    public void jugar(char letra) throws LetraInvalidaException, LetraRepetidaException {
        letra = Character.toUpperCase(letra);

        if (!Character.isLetter(letra)) {
            throw new LetraInvalidaException(letra);
        }

        if (letrasIngresadas.contains(letra)) {
            throw new LetraRepetidaException(letra);
        }

        letrasIngresadas.add(letra);

        if (verificarLetra(letra)) {
            actualizarPalabraIngresada(letra);
        } else {
            intentos--;
        }
    }
}