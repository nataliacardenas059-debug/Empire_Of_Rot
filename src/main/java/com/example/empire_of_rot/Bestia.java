package com.example.empire_of_rot;
import java.util.Random;

public class Bestia extends Enemigo {
    public Bestia(){
        super("Bestia",
                new Random().nextInt(51) + 120,
                new Random().nextInt(21) + 20,
                0.75);
    }
    
    @Override
    public void actuar (Combatiente enemigo){
        double action = random.nextDouble();
        if(action <= 0.4){
            habilidadEspecial(enemigo);
        }else{
            atacar(enemigo);
        }
    } 
    
    @Override
    public String habilidadEspecial(Combatiente enemigo){
        double probabilidad =  random.nextDouble();
        if(probabilidad <= 0.65){
            int danoEspecial = dano + 20;
            enemigo.dañoCausado(danoEspecial);
            enemigo.puntos -=0.5;

            return "\nGOLPEA BRUTALMENTE\n" + nombre + " destruyó brutalmente al jugador.\n" + "Daño Causado: " + danoEspecial;
        }else{
            return nombre + " Intento golpe brutal, pero falló";
        }



    }
    
}
