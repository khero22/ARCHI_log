package ARCHI_log.GUI;

import com.modules.Client;
import com.modules.Gestion;
import com.modules.Init;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class 20/01/2019
 *
 * @author KHERO
 */
public class NouveauClientController implements Initializable {

    Init init = new Init();
    @FXML
    private TextField inClient;
    @FXML
    private Button btnEnregistrer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void enregistrer(ActionEvent event) throws IOException {
        init.NouveauClient(compteur(), inClient.getText());
        //recupere la scene depui le id du boutton pour fermer la fenetre
        Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
        stage.close();
        Gestion.messageBoxOkOnTop("Nouveau Client", "Votre Client a été creer avec succé.", stage);
        
    }

    //methode pour retourner le dernier enregistrement et incrementer pour avois le nouveau ID
    private int compteur() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        ArrayList<Integer> index = new ArrayList<Integer>();
        try {
            clients = init.chargerListeClients();
            for (Client c : clients) {
                index.add(c.getID());
            }
        } catch (ParseException ex) {
            Logger.getLogger(NouveauClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int tempCompteur = index.size();
        tempCompteur++;
        return tempCompteur;
    }
}
