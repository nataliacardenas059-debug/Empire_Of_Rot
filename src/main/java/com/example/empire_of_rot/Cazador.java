package com.example.empire_of_rot;
import java.util.Random;

public class Cazador extends Combatiente {
    
    public Cazador (String nombre){
        super(nombre,
                new Random().nextInt(31) + 90,
                new Random().nextInt(16) + 20,
                0.70,
                new Arma("Escopeta", 30, "Cartuchos"));
    }
    @Override
    public void actuar(Combatiente enemigo){
        atacar(enemigo);
    }
    
    @Override
    public String habilidadEspecial(Combatiente enemigo){
        if(!arma.usarMunicion(2)){
           return "sin munición: No hay suficientes cartuchos.\n";
        }
        return "Doble disparo " + atacarEspecial(enemigo, 10) + "\n" + atacarEspecial(enemigo, 10);
    }
}
