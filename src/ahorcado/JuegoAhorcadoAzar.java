package ahorcado;

import java.util.ArrayList;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    private AdministradorPalabras adminPalabras;

    public JuegoAhorcadoAzar(AdministradorPalabras adminPalabras) {
        super();
        this.adminPalabras = adminPalabras;
        this.letrasIngresadas = new ArrayList<>();
    }

    public void inicializarPalabraSecretayOculta() {
        inicializarPalabraSecreta(adminPalabras.obtenerPalabraAzar());
    }

    @Override
    public void inicializarPalabraSecreta(String palabra) {
        this.palabraSecreta = palabra.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            sb.append("_");
        }
        this.palabraIngresada = sb.toString();
        this.intentosAcertados = new boolean[palabraSecreta.length()];
        this.letrasIngresadas.clear();
        this.intentos = 6;
    }

    @Override
    protected void actualizarPalabraIngresada(char letra) {
        char[] caracteres = palabraIngresada.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                caracteres[i] = letra;
                intentosAcertados[i] = true;
            }
        }
        this.palabraIngresada = String.valueOf(caracteres);
    }

    @Override
    protected boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }

    @Override
    public boolean esGanador() {
        if (palabraIngresada == null) {
            return false;
        }
        return palabraIngresada.equals(palabraSecreta);
    }

    public boolean esPerdedor() {
        return intentos <= 0 && !esGanador();
    }

    public void jugar(String input) throws LetraInvalidaException, LetraRepetidaException {
        if (input == null || input.trim().isEmpty()) {
            throw new LetraInvalidaException("Debes ingresar una letra.");
        }
        jugar(input.trim().charAt(0));
    }

    @Override
    public void jugar(char letra) throws LetraInvalidaException, LetraRepetidaException {
        letra = Character.toUpperCase(letra);

        if (!Character.isLetter(letra)) {
            throw new LetraInvalidaException("El caracter ingresado no es una letra valida.");
        }

        String letraStr = String.valueOf(letra);

        if (letrasIngresadas.contains(letraStr)) {
            throw new LetraRepetidaException(letra);
        }

        letrasIngresadas.add(letraStr);

        if (verificarLetra(letra)) {
            actualizarPalabraIngresada(letra);
        } else {
            intentos--;
        }
    }
}