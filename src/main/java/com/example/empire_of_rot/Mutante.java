package com.example.empire_of_rot;
import java.util.Random;

public class Mutante extends Enemigo{
    public Mutante (){
        super("Mutante",
                new Random().nextInt(41) + 100,
                new Random().nextInt(21) + 25,
                0.85
        );
    }
    
    @Override
    public void actuar (Combatiente enemigo){
        double action = random.nextDouble();
        if(action <= 0.1){
            habilidadEspecial(enemigo);
        }else{
            atacar(enemigo);
        }
    } 
    
    @Override
    public String habilidadEspecial(Combatiente enemigo){
        double probabilidad =  random.nextDouble();
        if(probabilidad <= 0.3){
            int danoEspecial = dano + 20;
            enemigo.dañoCausado(danoEspecial);
            enemigo.puntos -=0.5;

            return "ESCUPE ACIDO\n" + nombre + "quemo horriblemente al jugador.\n" + "Daño Causado: " + danoEspecial;
        }else{
            return nombre + " Intento escupir acido, pero falló";
        }
    }
    
}
