package ahorcado;

import java.util.ArrayList;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {

    private AdministradorPalabras adminPalabras;

    public JuegoAhorcadoAzar(AdministradorPalabras adminPalabras) {
        super();
        this.adminPalabras = adminPalabras;
        this.letrasIngresadas = new ArrayList<Character>();
    }

    public void inicializarPalabraSecretayOculta() {
        if (adminPalabras != null) {
            String palabraSeleccionada = adminPalabras.obtenerPalabraAzar();
            inicializarPalabraSecreta(palabraSeleccionada);
        } else {
            inicializarPalabraSecreta("JAVA");
        }
    }

    @Override
    public void inicializarPalabraSecreta(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) {
            this.palabraSecreta = "JAVA";
        } else {
            this.palabraSecreta = palabra.trim().toUpperCase();
        }

        StringBuilder guion = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            guion.append("_");
        }
        this.palabraIngresada = guion.toString();
        
        if (this.letrasIngresadas != null) {
            this.letrasIngresadas.clear();
        }
        this.intentos = 6;
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
            if (palabraSecreta.charAt(i) == letra) {
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

        if (letrasIngresadas.contains(Character.valueOf(letra))) {
            throw new LetraRepetidaException(letra);
        }

        letrasIngresadas.add(Character.valueOf(letra));

        if (verificarLetra(letra)) {
            actualizarPalabraIngresada(letra);
        } else {
            intentos--;
        }
    }
}