package ahorcado;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdministradorPalabras {
    private List<String> palabras;
    private Random random;

    public AdministradorPalabras() {
        this.palabras = new ArrayList<>();
        this.random = new Random();
        
        palabras.add("CORRER");
        palabras.add("PROGRAMACION");
        palabras.add("UNITEC");
        palabras.add("INGENIERIA");
        palabras.add("CODIGO");
    }

    public void agregarPalabra(String palabra) throws PalabraDuplicadaException {
        if (palabra == null || palabra.trim().isEmpty()) {
            return;
        }
        
        String palabraLimpia = palabra.trim().toUpperCase();
        
        if (palabras.contains(palabraLimpia)) {
            throw new PalabraDuplicadaException("La palabra '" + palabraLimpia + "' ya existe en la lista.");
        }
        
        palabras.add(palabraLimpia);
    }

    public String obtenerPalabraAzar() {
        if (palabras.isEmpty()) {
            return "AHORCADO";
        }
        int indice = random.nextInt(palabras.size());
        return palabras.get(indice);
    }

    public List<String> getPalabras() {
        return palabras;
    }
}