package com.example.empire_of_rot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;

public class CombatController {
    private Combatiente jugador;
    private ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
    private Enemigo enemigoActual;
    private int indiceEnemigo = 0;
    private int enemigosDerrotados = 0;

    @FXML
    private Label LblJugador;

    @FXML
    private Label LblVidaJugador;

    @FXML
    private Label LblMunicion;

    @FXML
    private Label LblPuntos;

    @FXML
    private Label LblEnemigo;

    @FXML
    private Label LblVidaEnemigo;

    @FXML
    private TextArea txtCombate;

    @FXML
    private ProgressBar barraVidaJugador;

    @FXML
    private ProgressBar barraVidaEnemigo;

    @FXML
    public void initialize(){
        txtCombate.appendText("\nEL COMBATE COMIENZA\n");
    }

    @FXML
    public void atacar(){
        String resultado = jugador.atacar(enemigoActual);
        txtCombate.appendText(resultado + "\n");
        actualizarBarras();
        actualizarDatos();
        if(resultado.toLowerCase().contains("sin munición")){
            turnoEnemigo();
            verificarEstado();
            return;
        }
        verificarEstado();
    }

    @FXML
    public void habilidad() {
        String resultado = jugador.habilidadEspecial(enemigoActual);
        txtCombate.appendText(resultado + "\n");
        actualizarBarras();
        actualizarDatos();

        if(resultado.toLowerCase().contains("sin munición")){
            turnoEnemigo();
            verificarEstado();
            return;
        }
        verificarEstado();
    }

    @FXML
    public void recargar(){
        jugador.getArma().recargar();
        txtCombate.appendText("\n Arma recargada\n");

        actualizarDatos();
        turnoEnemigo();
    }

    @FXML
    public void huir(){
        jugador.setVida(0);
        txtCombate.appendText("\nEl jugador huyó del combate\n");

        verificarEstado();
        actualizarDatos();
        finalizarPartida(false);
    }

    public void setJugador(Combatiente jugador){
        this.jugador = jugador;
        crearEnemigos();

        enemigoActual = enemigos.get(indiceEnemigo);

        actualizarDatos();

        txtCombate.appendText("\nHa aparecido un "+ enemigoActual.getNombre() + "!\n");
    }

    public void crearEnemigos(){
        enemigos.clear();
        for(int i = 0; i<3; i++){
            int random = (int)(Math.random()*4);

            switch(random){
                case 0:
                    enemigos.add(new Zombie());
                    break;
                case 1:
                    enemigos.add(new Bestia());
                    break;
                case 2:
                    enemigos.add(new Mutante());
                    break;
                case 3:
                    enemigos.add(new Infectado());
                    break;
            }
        }
    }

    public void turnoEnemigo(){
        if(!enemigoActual.tieneVida()){
            return;
        }
        int action = (int)(Math.random()*2);
        String resultado;

        if(action == 0){
            resultado = enemigoActual.atacar(jugador);

        }else{
            resultado = enemigoActual.habilidadEspecial(jugador);
        }

        txtCombate.appendText("\n[Turno enemigo]\n" + resultado + "\n");
        actualizarBarras();
        actualizarDatos();

        if(!jugador.tieneVida()){
            finalizarPartida(false);
        }
    }

    public void actualizarDatos(){
        LblJugador.setText(getEmojiJugador() + " " + jugador.getNombre());
        LblVidaJugador.setText("Vida: " + jugador.getVida());
        LblMunicion.setText("Munición: " + jugador.getArma().getMunicion());
        LblPuntos.setText("Puntos: "+ jugador.getPuntos());

        LblEnemigo.setText(getEmojiEnemigo() + " " + enemigoActual.getNombre());
        LblVidaEnemigo.setText("Vida: " + enemigoActual.getVida());

    }

    public void verificarEstado(){
        if(!enemigoActual.tieneVida()){
            enemigosDerrotados++;
            jugador.setPuntos(jugador.getPuntos()+ 33);
            txtCombate.appendText("\nEnemigo derrotado!\n" + "+33 Puntos");
            indiceEnemigo++;
            if(indiceEnemigo < enemigos.size()){
                enemigoActual = enemigos.get(indiceEnemigo);
                txtCombate.appendText("\nHa aparecido un nuevo enemigo!\n" + "\nTipo: " + enemigoActual.getNombre() + "\n");
                actualizarDatos();
                actualizarBarras();
            }else{
                finalizarPartida(true);
                return;
            }
        }
        if(!jugador.tieneVida()){
            finalizarPartida(false);
            return;
        }

    }

    public void finalizarPartida(boolean victoria){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result-view.fxml"));
            Parent root = loader.load();
            ResultController controller = loader.getController();

            controller.setDatos(jugador,enemigosDerrotados, victoria);
            Stage stage = (Stage) txtCombate.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void actualizarBarras(){
        barraVidaJugador.setProgress(Math.max(0,jugador.getVida()/150.0));
        barraVidaEnemigo.setProgress(Math.max(0,enemigoActual.getVida()/150.0));
    }

    private String getEmojiJugador(){
        if(jugador instanceof Arquero){
            return "\uD83C\uDFF9";
        }

        if(jugador instanceof Soldado){
            return "\uD83D\uDC82";
        }

        if(jugador instanceof Cazador){
            return "\uD83E\uDDDF";
        }
        return "\uD83C\uDFAE";
    }

    private String getEmojiEnemigo(){
        if(enemigoActual instanceof Bestia){
            return "\uD83D\uDC79";
        }

        if(enemigoActual instanceof Zombie){
            return "\uD83E\uDDDF";
        }

        if(enemigoActual instanceof Mutante){
            return "\uD83E\uDDEC";
        }

        if(enemigoActual instanceof Infectado){
            return "☣";
        }
        return "\uD83D\uDC80";
    }


}
