package com.example.empire_of_rot;
import java.util.Random;

public abstract class Combatiente implements Atacable{
    protected String nombre;
    protected int vida;
    protected int dano;
    protected boolean vivo;
    protected double acertividad;
    protected double puntos;
    protected Arma arma;
    
    protected static int totalCombatientes = 0;
    protected Random random = new Random();
    
    public Combatiente (String nombre, int vida, int dano, double acertividad, Arma arma){
        this.nombre = nombre;
        this.vida = vida;
        this.dano = dano;
        this.vivo = true;
        this.acertividad = acertividad;
        this.arma = arma;
        this.puntos = 0;
        
        totalCombatientes++;
    }
    
    public abstract void actuar(Combatiente enemigo);
    
    public abstract String habilidadEspecial(Combatiente enemigo);
    
    //Metodo para realizar un ataque normal, verificando la municion, acertividad y suma de puntos
    public String atacar(Combatiente enemigo){
        if(!arma.disparar()){
            return "sin munición";
        }

         double probabilidad = random.nextDouble();
         if(probabilidad <= acertividad){
             int danoTotal = dano + arma.getDano();
             enemigo.dañoCausado(danoTotal);
             puntos += 1;
             return  nombre + " acertó el ataque.\n" + "El dano causado fue: " + danoTotal;
             
         }else{
             return nombre + "\nfallo el ataque.\n";
         }
        
    }
    
    //Metodo para el ataque especial, permitiendo aplicar restricciones y daño estra.
    public String atacarEspecial(Combatiente enemigo, int danoExtra){
        double probabilidad = random.nextDouble();
        if(probabilidad <= acertividad){
            int danoTotal = dano + arma.getDano() + danoExtra;
            enemigo.dañoCausado(danoTotal);
            puntos += 1;
            
            return nombre + " realizo ataque especial.\n" + "\nDano causado: " + danoTotal;

        }else{
            return nombre + "  fallo el ataque especial\n";
        }
    }
    
    //Metodo para reducir la vida del combatiente segun el daño recibido.
    public void dañoCausado(int danoRecibido){
        vida -= danoRecibido;
        if (vida <= 0){
            vida = 0;
            vivo = false;
        }
    }
    public boolean tieneVida(){
        return vida > 0;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getVida(){
        return vida;
    }
    
    public void setVida(int vida){
        if(vida < 0) vida = 0;
        this.vida = vida;
    }
    
    public int getDano(){
        return dano;
    }

    public double setPuntos(double puntos){
        this.puntos = puntos;
        return puntos;
    }
    public double getPuntos(){
        return puntos;
    }

    public Arma getArma(){
        return arma;
    }
    
    public static int getTotalCombatientes(){
        return totalCombatientes;
    }
    
    @Override
    public String toString(){
        return nombre + " | Vida: " + vida +
                "\nDaño: " + dano +
                "\nPuntos: " + puntos +
                "\nArma: " + arma ;
    }
    
}
