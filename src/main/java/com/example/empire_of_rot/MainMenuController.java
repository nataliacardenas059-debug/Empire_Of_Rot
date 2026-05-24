package com.example.empire_of_rot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainMenuController {
    @FXML
    private TextArea txtTop;

    public void Jugar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("player-setup.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void salir(){
        System.exit(0);
    }

    @FXML
    public void initialize(){
        mostrarTop();
    }

    public void mostrarTop(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Top5.txt"));
            String linea;
            while((linea = reader.readLine()) != null){
                txtTop.appendText(linea + "\n");
            }

        }catch(Exception e){
            txtTop.setText("No hay puntajes");
        }
    }
}
