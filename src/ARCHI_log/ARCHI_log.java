/*
 *logiciel qui gere tous les projets creer par le bureau d'etude ACAD NAIB 
 creer le 20/03/2020
 par NAIB KHEIREDDINE
email: knaib22@gmail.com
 */
package ARCHI_log;

import com.modules.Gestion;
import com.modules.LanceurFenetres;
import com.modules.NomChamps;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author KHERO
 */
public class ARCHI_log extends Application {

    private final Stage fenetreMain = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        LanceurFenetres fenetreMAIN = new LanceurFenetres("GUI/MAIN.fxml");
        AffichageMAIN(fenetreMAIN);
    }

    public static void main(String[] args) {
        launch(args);
    }

    //methode affichage fenetre MAIN
    public void AffichageMAIN(LanceurFenetres ls) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ls.getRessources()));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(ls.getCss());
            fenetreMain.getIcons().add(new Image(ls.getFenetreIcon()));
            fenetreMain.setTitle(ls.getFenetreNom());
            fenetreMain.setScene(scene);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            fenetreMain.setWidth(bounds.getWidth());
            fenetreMain.setHeight(bounds.getHeight());
            fenetreMain.show();
            System.out.println(System.getProperty("user.dir"));
        } catch (Exception e) {
            Gestion.messageBoxErreur(NomChamps.NOM_APP, e.toString()+"ERREUR de chargement du PROGRAMME");
        }

    }

    //methode affichage fenetre simple
    public void AffichageFenetre(LanceurFenetres ls) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ls.getRessources()));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(ls.getCss());
            fenetreMain.getIcons().add(new Image(ls.getFenetreIcon()));
            fenetreMain.setTitle(ls.getFenetreNom());
            fenetreMain.setScene(scene);
            fenetreMain.setMaximized(false);
            fenetreMain.alwaysOnTopProperty();
            fenetreMain.show();
        } catch (Exception e) {
            Gestion.messageBoxErreur(NomChamps.NOM_APP, e.toString()+"ERREUR de chargement du PROGRAMME");
        }

    }

}
