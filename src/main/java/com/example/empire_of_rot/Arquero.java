package com.example.empire_of_rot;
import java.util.Random;

public class Arquero extends Combatiente {
    public Arquero (String nombre){
        super(nombre,
                new Random().nextInt(21) + 85,
                new Random().nextInt(16) + 18,
                0.85,
                new Arma("Arco", 25, "Flechas"));
    }
    @Override
    public void actuar(Combatiente enemigo){
        atacar(enemigo);
    }
    
    @Override
    public String habilidadEspecial(Combatiente enemigo){
        if(!arma.usarMunicion(4)){
            return "No hay suficientes flechas" ;

        }
        return "LLUVIA DE FLECHAS" + atacarEspecial(enemigo, 30) ;

    }
    
}
