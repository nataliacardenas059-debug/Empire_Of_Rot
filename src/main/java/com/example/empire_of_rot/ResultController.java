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
        try{
            File archivo = new File("Top5.txt");
            if(!archivo.exists()){
                archivo.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo,true));
            writer.write(jugador.getNombre() + " - " + jugador.getPuntos());
            writer.newLine();
            writer.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarTop(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Top5.txt"));
            String linea;

            while((linea = reader.readLine()) != null){
                txTop.appendText(linea + "\n");
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
