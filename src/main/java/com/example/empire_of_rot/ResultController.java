package com.example.empire_of_rot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ResultController {
    @FXML
    private Label LblResultado;

    @FXML
    private Label LblJugador;

    @FXML
    private Label LblPuntos;

    @FXML
    private Label LblEnemigos;

    @FXML
    private Label lblTop1;

    @FXML
    private Label lblTop2;

    @FXML
    private Label lblTop3;

    @FXML
    private Label lblTop4;

    @FXML
    private Label lblTop5;

    public void setDatos(Combatiente jugador, int enemigosDerrotados, boolean victoria){
        LblJugador.setText("Jugador: " + jugador.getNombre());
        LblPuntos.setText("Puntos: " + jugador.getPuntos());
        LblEnemigos.setText("Enemigos derrotados: " + enemigosDerrotados);

        if(victoria){
            LblResultado.setText("\uD83C\uDFC6 VICTORIA");
        }else{
            LblResultado.setText("☠ GAME OVER ");
        }
        guardarTop(jugador);

        mostrarTop();
    }

    public void guardarTop(Combatiente jugador){
        try{
            ArrayList<String> Puntajes = new ArrayList<>();

            File archivo = new File("Top5.txt");

            if(archivo.exists()){
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea;

                while((linea = reader.readLine())!= null){
                   Puntajes.add(linea);
                }
                reader.close();
            }

            Puntajes.add(jugador.getNombre() + " - " + jugador.getPuntos());
            Puntajes.sort((a,b) ->{
                double puntosA = Double.parseDouble(a.split("-")[1].trim());
                double puntosB = Double.parseDouble(b.split("-")[1].trim());
                return Double.compare(puntosB, puntosA);
            });

            if(Puntajes.size() > 5){
                Puntajes = new ArrayList<>(Puntajes.subList(0,5));
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("Top5.txt"));
            for(String p : Puntajes){
                writer.write(p);
                writer.newLine();
            }
            writer.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarTop() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Top5.txt"));
            String[] tops = new String[5];
            String linea;
            int i = 0;

            while ((linea = reader.readLine()) != null && i < 5) {
                tops[i] = linea;
                i++;
            }
            reader.close();

            lblTop1.setText(i > 0 ? tops[0] : "---");
            lblTop2.setText(i > 1 ? tops[1] : "---");
            lblTop3.setText(i > 2 ? tops[2] : "---");
            lblTop4.setText(i > 3 ? tops[3] : "---");
            lblTop5.setText(i > 4 ? tops[4] : "---");

        } catch (IOException e) {
            lblTop1.setText("---");
            lblTop2.setText("---");
            lblTop3.setText("---");
            lblTop4.setText("---");
            lblTop5.setText("---");

        }
    }

    @FXML
    public void volverAJugar(ActionEvent event)throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    public void salir(){
        System.exit(0);
    }
}
