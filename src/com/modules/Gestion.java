/*
class qui comprent tous les fonction utils
 */
package com.modules;


import javafx.scene.control.Alert;
import javafx.stage.Stage;


/**
 * le: 24/01/2017
 *
 * @author khero
 */
public class Gestion {



    //nouvelle alert d'erreur qui est toujour au top de la fenetre

    public static void messageBoxErreurOnTOP(String titre, String message, Stage stage) {
        Alert messageBox = new Alert(Alert.AlertType.ERROR);
        messageBox.setTitle("Getprim Alerte");
        messageBox.setHeaderText(titre);
        messageBox.setContentText(message);
        messageBox.initOwner(stage);
        messageBox.show();
    }
  public static void messageBoxErreur(String titre, String message) {
        Alert messageBox = new Alert(Alert.AlertType.ERROR);
        messageBox.setTitle("Getprim Alerte");
        messageBox.setHeaderText(titre);
        messageBox.setContentText(message);
        messageBox.show();
    }


    //message alert ok toujour on top
    public static void messageBoxOkOnTop(String titre, String message,Stage stage) {
        Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
        messageBox.setTitle("Getprim information");
        messageBox.setHeaderText(titre);
        messageBox.setContentText(message);
        messageBox.initOwner(stage);
        messageBox.show();
    }
}
