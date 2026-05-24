package com.example.empire_of_rot;
import java.util.Random;

public class Soldado extends Combatiente {
    
    public Soldado(String nombre){
        super(nombre,
                new Random().nextInt(41) + 110,
                new Random().nextInt(11) + 15,
                0.80,
                new Arma("Rifle", 20, "Balas")
        );
    }
    
    @Override
    public void actuar(Combatiente enemigo){
        atacar(enemigo);
    }
    
    @Override
    public String habilidadEspecial(Combatiente enemigo){
        if(!arma.usarMunicion(3)){
            return "No hay suficientes balas";

        }
        return "Rafaga activada" + atacarEspecial(enemigo, 20);
    }
}
