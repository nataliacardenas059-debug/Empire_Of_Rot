package com.example.empire_of_rot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainMenuController {
    @FXML
    private TextArea txtTop;

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
            String[] tops = new String[5];
            String linea;
            int i = 0;

            while((linea = reader.readLine()) != null && i<5){
                tops[i] = linea;
                i++;
            }
            reader.close();

            lblTop1.setText(i > 0 ? tops[0] : "---");
            lblTop2.setText(i > 1 ? tops[1] : "---");
            lblTop3.setText(i > 2 ? tops[2] : "---");
            lblTop4.setText(i > 3 ? tops[3] : "---");
            lblTop5.setText(i > 4 ? tops[4] : "---");

        }catch( IOException e){
            lblTop1.setText("---");
            lblTop2.setText("---");
            lblTop3.setText("---");
            lblTop4.setText("---");
            lblTop5.setText("---");

        }
    }
}
