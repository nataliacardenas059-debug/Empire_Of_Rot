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
    private TextArea txTop;

    public void setDatos(Combatiente jugador, int enemigosDerrotados, boolean victoria){
        LblJugador.setText("Jugador: " + jugador.getNombre());
        LblPuntos.setText("Puntos: " + jugador.getPuntos());
        LblEnemigos.setText("Enemigos derrotados: " + enemigosDerrotados);

        if(victoria){
            LblResultado.setText("VICTORIA");
        }else{
            LblResultado.setText("GAME OVER ");
        }
        guardarTop(jugador);

        mostrarTop();
    }

    public void guardarTop(Combatiente jugador){
        ArrayList<String> Puntajes = new ArrayList<>();
        try{
            File archivo = new File("Top5.txt");
            if(!archivo.exists()){
                archivo.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;

            while((linea = reader.readLine())!= null){
                if(!linea.trim().isEmpty()){
                    Puntajes.add(linea);
                }
            }
            reader.close();

            Puntajes.add(jugador.getNombre() + " - " + jugador.getPuntos());
            Collections.sort(Puntajes, (a,b) ->{
                double puntosA = Double.parseDouble(a.split("-")[1].trim());
                double puntosB = Double.parseDouble(b.split("-")[1].trim());
                return Double.compare(puntosB, puntosA);
            });

            if(Puntajes.size() > 5){
                Puntajes = new ArrayList<>(Puntajes.subList(0,5));
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            for(String p : Puntajes){
                writer.write(p);
                writer.newLine();
            }

            writer.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarTop(){
        txTop.clear();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Top5.txt"));
            String linea;
            int posicion = 1;

            while((linea = reader.readLine()) != null){
                txTop.appendText(posicion + ". " + linea + "\n");
                posicion++;
            }
            reader.close();

        }catch( IOException e){
            txTop.setText("No existe top.");
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
