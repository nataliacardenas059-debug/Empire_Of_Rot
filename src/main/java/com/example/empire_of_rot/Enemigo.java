package com.example.empire_of_rot;

public abstract class Enemigo extends Combatiente{
    public Enemigo (String nombre, int vida, int dano, double acertividad){
        super (nombre, vida, dano, acertividad, null);
    }
    @Override
    public String atacar(Combatiente jugador){
        double probabilidad = random.nextDouble();
        if(probabilidad <= acertividad){
            jugador.dañoCausado(dano);
            jugador.puntos -= 0.5;
            return nombre + " atacó causando " + dano + " de daño";
        }else {
            return nombre + " fallo el ataque";
        }
    }
    
    @Override
    public String toString(){
        return nombre + " | Vida: " + vida + "\nDano: " + dano;
    }
}
