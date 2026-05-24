package com.example.empire_of_rot;
import java.util.Random;

public class Infectado extends Enemigo {
    public Infectado(){
        super("Infectado",
                new Random().nextInt(41) + 20,
                new Random().nextInt(16) + 50,
                0.65);
    }
    
    @Override
    public void actuar (Combatiente enemigo){
        double action = random.nextDouble();
        if(action <= 0.2){
            habilidadEspecial(enemigo);
        }else{
            atacar(enemigo);
        }
    } 
    
    @Override
    public String habilidadEspecial(Combatiente enemigo){
        double probabilidad =  random.nextDouble();
        if(probabilidad <= 0.25){
            int danoEspecial = dano + 20;
            enemigo.dañoCausado(danoEspecial);
            enemigo.puntos -=0.5;

            return "ATACA VELOZMENTE\n" + nombre + "Hirió gravemente al jugador.\n" + "Daño Causado: " + danoEspecial;
        }else{
            return nombre + " Intento atacar rapidamente, pero falló";
        }
    }
    
}
