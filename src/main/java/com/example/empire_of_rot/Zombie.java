package com.example.empire_of_rot;
import java.util.Random;

public class Zombie extends Enemigo {
    public Zombie(){
        super("Zombie",
                new Random().nextInt(31) + 90,
                new Random().nextInt(11) + 5,
                0.3);
    }
    
    @Override
    public void actuar (Combatiente enemigo){
        double action = random.nextDouble();
        if(action <= 0.5){
            habilidadEspecial(enemigo);
        }else{
            atacar(enemigo);
        }
    } 
    
    @Override
    public String habilidadEspecial(Combatiente enemigo){
        double probabilidad =  random.nextDouble();
        if(probabilidad <= 0.7){
            int danoEspecial = dano + 15;
            enemigo.dañoCausado(danoEspecial);
            enemigo.puntos -=0.5;

            return "MUERDE BRUTALMENTE\n" + nombre + " mordio al jugador.\n" + "Daño Causado: " + danoEspecial;
        }else{
            return nombre + " Intento morder, pero falló.\n";
        }
    }
    
}
