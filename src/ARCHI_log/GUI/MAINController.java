/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARCHI_log.GUI;

import com.modules.Annee;
import com.modules.Categorie;
import com.modules.Client;
import com.modules.ComboBoxAutoComplete;
import com.modules.Gestion;
import com.modules.Projet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.modules.Init;
import com.modules.LanceurFenetres;
import com.modules.NomChamps;
import com.modules.ProjetParser;
import com.modules.Variables;
import ARCHI_log.ARCHI_log;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KHERO
 */
public class MAINController implements Initializable {

    ARCHI_log util = new ARCHI_log();

    @FXML
    private Label txtStatus;
    @FXML
    private TableColumn<ProjetParser, String> Annee;
    @FXML
    private TableColumn<ProjetParser, String> Categorie;
    @FXML
    private TableColumn<ProjetParser, String> Numero;
    @FXML
    private TableColumn<ProjetParser, String> Client;
    @FXML
    private TableColumn<ProjetParser, String> Projet;
    @FXML
    private TextField txtRecherche;
    @FXML
    private ComboBox<Annee> comboAnnee;
    @FXML
    private ComboBox<String> comboCategorie;
    @FXML
    private ComboBox<Client> comboClient;

    private ObservableList<Projet> mesProjets = FXCollections.observableArrayList();
    private ObservableList<ProjetParser> mesProjetParsers = FXCollections.observableArrayList();
    Init init;
    @FXML
    private TableView<ProjetParser> tableProjets;

    FilteredList<ProjetParser> items;
    FilteredList<Projet> mesProjetsFiltrer;
    @FXML
    private Button btnDeleteFiltre;

    private final Map<Integer, Integer> mapAnnees = new HashMap<>();
    private final Map<Integer, String> mapClients = new HashMap<>();
    private final Map<Integer, String> mapCategorie = new HashMap<>();
    @FXML
    private Button btnActualiser;

    SortedList<ProjetParser> sortedData;
    int monAnnee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //------INITIALISATION --------------------------------------------------------------------
        init = new Init();
        //reglage des cellules de la tableView
        Annee.setCellValueFactory(new PropertyValueFactory<ProjetParser, String>("Annee"));
        Categorie.setCellValueFactory(new PropertyValueFactory<ProjetParser, String>("Categorie"));
        Client.setCellValueFactory(new PropertyValueFactory<ProjetParser, String>("Client"));
        Numero.setCellValueFactory(new PropertyValueFactory<ProjetParser, String>("numero"));
        Projet.setCellValueFactory(new PropertyValueFactory<ProjetParser, String>("Projet"));
        Annee.setMinWidth(60);
        Annee.setStyle("-fx-font-size:14;-fx-font-weight:bold;-fx-background-color:  #D4E9BF;");
        Categorie.setMinWidth(250);
        Categorie.setStyle("-fx-font-size:14;-fx-font-weight:bold;");
        Client.setMinWidth(200);
        Client.setStyle("-fx-font-size:14;-fx-font-weight:bold;");
        Numero.setMinWidth(60);
        Numero.setStyle("-fx-font-size:14;-fx-font-weight:bold;");
        Projet.setMinWidth(600);
        Projet.setStyle("-fx-font-size:14;-fx-font-weight:bold;");
        //------------------------------------------------------------------------------------------
        try {
            // ------CREATION ------------------------------------------
            //Projet un=init.NouveauProjet(2, 0, 0, 2, 23, "teste 04");
            //Annee annee=init.NouvelleAnnee(1, 2018);
            //Categorie cat=init.NouvelleCategorie(2, "essai de convenance");
            //Client cl=init.NouveauClient(2, "EURL");
            //------------------------------------------------------------
            //-----CHARGEMENT --------------------------------------------

            mapperLesJsonFiles();
            comboAnnee.setItems(init.chargerListeAnnee());
            comboAnnee.getSelectionModel().selectLast();
            comboCategorie.setItems(init.chargerListeCategorieString());
            comboCategorie.getSelectionModel().selectLast();
            comboCategorie.setDisable(true);
            comboClient.setItems(init.chargerListeClients());
            comboClient.getSelectionModel().selectLast();
            comboClient.setDisable(true);
            //mesProjetsFiltrer = init.chargerListeProjets().filtered(null);
            chargerLesProjets();
            txtStatus.setText("Chargement efectué avec succée.");
            //------------------------------------------------------------
        } catch (ParseException ex) {
            Logger.getLogger(MAINController.class.getName()).log(Level.SEVERE, null, ex);

            Gestion.messageBoxErreur("GETPRIM PROJET", ex.toString());
        }

        //-------RECHERCHE------------------------------------------------------------------------------
        txtRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            this.items.setPredicate(p -> p.getAnnee() == comboAnnee.getValue().getAnnee());
            this.items.setPredicate(projet -> {
                // If filter text est vide, affiche tous les projets
                if (newValue == null || newValue.isEmpty()) {
                    txtStatus.setText("PAS de critere de recherche.");
                    return true;
                }
                if (projet.getProjet().contains(newValue) && projet.getAnnee() == comboAnnee.getValue().getAnnee()) {
                     txtStatus.setText("[ Filtre ] -> Liste Total des Projets Trouvez pour l'annee: [ "+comboAnnee.getValue().getAnnee()+
                                   " ] avec le critere de recherche : "+newValue+" .");
                    return true; // Filter marche
                }
                return false; // ne contien pas de critere
            });
        });//---------------------------------------------------------------------------------------------
        //----------FILTRE PAR ANNEE ----------------------------------------------------------------------------
        comboAnnee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Annee>() {
            @Override
            public void changed(ObservableValue<? extends Annee> observable, Annee oldValue, Annee newValue) {

                items.setPredicate(sortedData -> sortedData.getAnnee() == newValue.getAnnee());
               txtStatus.setText("[ Filtre ] -> Nombre Total des Projets pour l'annee: [ "+newValue.getAnnee()+ " ] est de : "+tableProjets.getItems().size());

            }
        }); //----------------------------------------------------------------------------------------------------

        new ComboBoxAutoComplete<Client>(comboClient); //autocompletion des client
    }

    @FXML
    private void Quitter(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void NewProjet(ActionEvent event) throws IOException {
        LanceurFenetres fenetre = new LanceurFenetres("GUI/NouveauProjet.fxml");
        util.AffichageFenetre(fenetre);
    }

    @FXML
    private void NewClient(ActionEvent event) throws IOException {
        LanceurFenetres fenetre = new LanceurFenetres("GUI/NouveauClient.fxml");
        util.AffichageFenetre(fenetre);
    }

    @FXML
    private void NewCategorie(ActionEvent event) throws IOException {
        LanceurFenetres fenetre = new LanceurFenetres("GUI/NouvelleCategorie.fxml");
        util.AffichageFenetre(fenetre);
    }

    @FXML
    private void NewAnnee(ActionEvent event) throws IOException {
        LanceurFenetres fenetre = new LanceurFenetres("GUI/NouvelleAnnee.fxml");
        util.AffichageFenetre(fenetre);
    }

    @FXML
    private void About(ActionEvent event) {
        //recupere la scene depui le id du boutton pour fermer la fenetre
        Stage stage = (Stage) btnActualiser.getScene().getWindow();
        Gestion.messageBoxOkOnTop(NomChamps.NOM_APP, "Logiciel de Gestion des projets du Laboratoire GETPRIM \n"
                                                    +"_____________________________________________________________\n"
                                                    + "creer par NAIB kheireddine contact: knaib22@gmail.com \n"
                                                    +"_____________________________________________________________\n"
                                                    + "version: " + Variables.VERSION + " date derniere modification: " + Variables.DATE, stage);
    }

    public ObservableList<ProjetParser> ParceProjet(ObservableList<Projet> PR) throws ParseException {
        ObservableList<ProjetParser> parcer = FXCollections.observableArrayList();

        for (Projet p : PR) {
            try {
                ProjetParser pp = new ProjetParser(mapAnnees.get(p.getAnnee()),
                        mapCategorie.get(p.getCategorie()),
                        mapClients.get(p.getClient()), p.getNumero(),
                        p.getProjet());
                parcer.add(pp);
            } catch (IndexOutOfBoundsException e) {
                Logger.getLogger(MAINController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return parcer;
    }//-----------------------------------------------------------------------------------------

    @FXML
    private void DeleteFiltrer(ActionEvent event) {
        txtRecherche.clear();
        this.items.setPredicate(null);
        txtStatus.setText("[ Tous les Filtres Effacer ..] -> Nombre Total des Projets est de : "+tableProjets.getItems().size());
    }

    //methode pour mapper tous les annees , clients et categorie dans des map
    private void mapperLesJsonFiles() throws ParseException {

        for (Annee a : init.chargerListeAnnee()) {
            mapAnnees.put(a.getID(), a.getAnnee());
        }
        for (Client c : init.chargerListeClients()) {
            mapClients.put(c.getID(), c.getClient());
        }
        for (Categorie ca : init.chargerListeCategorie()) {
            mapCategorie.put(ca.getID(), ca.getCategorie());
        }
    }

    public MAINController() {
    }

    private void chargerLesProjets() throws ParseException {

        mesProjets = init.chargerListeProjets();
        //filtrer la liste des projets suivant le critere de recherche -------------------------------------
        try {
            mesProjetParsers = ParceProjet(mesProjets);
        } catch (ParseException ex) {
            Logger.getLogger(MAINController.class.getName()).log(Level.SEVERE, null, ex);
        }
        items = new FilteredList<>(mesProjetParsers, p -> true);
        sortedData = new SortedList<>(items);
        sortedData.comparatorProperty().bind(tableProjets.comparatorProperty());
        //---------------------------------------------------------------------------------------------
        //charger la tableView--------------------------------------------------------------------------
        tableProjets.setItems(sortedData);
        //----------------------------------------------------------------------------------------------
    }

    @FXML
    private void Actualiser(ActionEvent event) throws ParseException {
        chargerLesProjets();
        txtStatus.setText("[ Liste Actualiser ] -> Nombre Total des Projets est de : "+tableProjets.getItems().size());
    }

}
