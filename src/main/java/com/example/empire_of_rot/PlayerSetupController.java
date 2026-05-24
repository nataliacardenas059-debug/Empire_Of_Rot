package com.example.empire_of_rot;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class PlayerSetupController {
    private Combatiente jugador;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> comboCombatiente;

    @FXML
    private Label LblError;

    @FXML
    public void initialize(){
        comboCombatiente.setItems(FXCollections.observableArrayList("Cazador","Soldado","Arquero"));
    }

    @FXML
    public void iniciarPartida(ActionEvent event) throws Exception {
        String nombre = txtNombre.getText();
        String tipo = comboCombatiente.getValue();

        if(nombre.isEmpty() || !nombre.matches(".*[a-zA-Z].*") || tipo == null){
            LblError.setText("El nombre debe tener al menos una letra");
            return;
        }

        LblError.setText("");

        switch(tipo){
            case "Cazador":
                jugador = new Cazador(nombre);
                break;

            case "Soldado":
                jugador = new Soldado(nombre);
                break;

            case "Arquero":
                jugador = new Arquero(nombre);
                break;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("combat-view.fxml"));
        Parent root = loader.load();

        CombatController controller = loader.getController();
        controller.setJugador(jugador);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
