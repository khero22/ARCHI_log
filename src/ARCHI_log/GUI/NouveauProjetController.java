package ARCHI_log.GUI;

import com.modules.Annee;
import com.modules.Categorie;
import com.modules.Client;
import com.modules.ComboBoxAutoComplete;
import com.modules.Gestion;
import com.modules.Init;
import com.modules.NomChamps;
import com.modules.Projet;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class 20/01/2019
 *
 * @author KHERO
 */
public class NouveauProjetController implements Initializable {

    Init init = new Init();

    @FXML
    private ComboBox<Annee> comAnnee;
    @FXML
    private ComboBox<Categorie> comCategorie;
    @FXML
    private ComboBox<Client> comClient;
    @FXML
    private TextArea txtProjet;
    @FXML
    private Button btnEnregistrer;

    private final Map<Integer, Integer> mapNumeroProjet = new HashMap<>();
    private final Map<Integer, Integer> mapAnneeCategorie = new HashMap<>();
    private final Map<Integer, Integer> mapCategorieNumero = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //-----CHARGEMENT --------------------------------------------
            //annee
            comAnnee.setItems(init.chargerListeAnnee());
            comAnnee.getSelectionModel().selectLast();
            //categorie
            comCategorie.setItems(init.chargerListeCategorie());
            comCategorie.getSelectionModel().selectLast();
            //client
            comClient.setItems(init.chargerListeClients());
            comClient.getSelectionModel().selectLast();
            //------------------------------------------------------------
        } catch (ParseException ex) {
            Logger.getLogger(NouveauProjetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        new ComboBoxAutoComplete<Client>(comClient);
        new ComboBoxAutoComplete<Categorie>(comCategorie);
    }

    @FXML
    private void enregistrer(ActionEvent event) throws IOException, ParseException {
        
        init.NouveauProjet(compteur(),
                comAnnee.getValue().getID(),
                comCategorie.getValue().getID(),
                comClient.getValue().getID(),
                CalculNumeroProjetEtEnregistrer(comAnnee.getValue().getID(),comCategorie.getValue().getID()),
                txtProjet.getText());
        //recupere la scene depui le id du boutton pour fermer la fenetre
        Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
        stage.close();
        Gestion.messageBoxOkOnTop("Nouveau Projet", "Votre Projet a été creer avec succé.", stage);
    }

    //methode pour retourner le dernier enregistrement et incrementer pour avois le nouveau ID
    private int compteur() {
        ObservableList<Projet> projets = FXCollections.observableArrayList();
        try {
            projets = init.chargerListeProjets();
        } catch (ParseException ex) {
            Logger.getLogger(NouveauProjetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int tempCompteur = projets.size();
        tempCompteur++;
        return tempCompteur;
    }

    //methode pour calculer le numero du projet suivant sa categorie puis enregistrer le numero dans json categorie
    private int CalculNumeroProjetEtEnregistrer(int IDanne,int IDctegorie) throws ParseException {
        List<Integer> numeros = new ArrayList<>();
        for (Projet pa : init.chargerListeProjets()) {
           if(pa.getAnnee()==IDanne && pa.getCategorie()==IDctegorie ){
               numeros.add(pa.getNumero());
           }
        }
        System.out.println(comAnnee.getValue().getID());
        System.out.println(comCategorie.getValue().getID());
        System.out.println(numeros);
        int tempNumero = numeros.size();
        tempNumero++;
        return tempNumero;
    }
}
