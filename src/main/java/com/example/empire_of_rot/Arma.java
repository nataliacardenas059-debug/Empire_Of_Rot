package com.example.empire_of_rot;
import java.util.Random;

public class Arma {
    private String nombre;
    private int dano;
    private int municion;
    private String tipoMunicion;
    
    
    Random random = new Random();
    
    public Arma(String nombre, int dano, String tipoMunicion){
        this.nombre = nombre;
        this.dano = dano;
        this.tipoMunicion = tipoMunicion;
        
        this.municion = random.nextInt(8) + 2;
    }
    
    //Metodo para realizar el ataque, primero comprueba que si haya municiones,y descuenta una unidad al disparar.
    public boolean disparar(){
        if(municion > 0){
            municion--;
            return true;
        }
        return false;
    }
    
    //Recarga el arma de los combatientes con una cantidad aleatoria de municion.
    public void recargar(){
        municion += random.nextInt(4) + 6;
        System.out.println("Arma recargada");
    }
    
    public int getDano(){
        return dano;
    }
    
    public int getMunicion(){
        return municion;
    }
    
    //Se verifica y descuenta la cantidad de munición necesaria.
    public boolean usarMunicion(int cantidad){
        if(municion >= cantidad){
            municion -= cantidad;
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return nombre + " | " + tipoMunicion + ": " + municion;
    }
}
