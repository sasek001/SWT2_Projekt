package GUI;
import java.io.File;

import javax.swing.JOptionPane;

import ESA.Mitarbeiter;
import ESA.Mitarbeiterverwaltung;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AnmeldeFenster extends Application
{
	Mitarbeiterverwaltung mitarbeiterverwaltung;
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		GridPane gp = new GridPane();
		
		Label iDLabel = new Label("ID");
		TextField iDFeld = new TextField();
		
		Label passLabel = new Label("Passwort");
		PasswordField passFeld = new PasswordField();
		
		Button anmelden = new Button("anmelden");
		
		gp.add(iDLabel, 1, 1);
		gp.add(iDFeld, 2, 1);
		
		gp.add(passLabel, 1, 2);
		gp.add(passFeld, 2, 2);
		
		gp.add(anmelden, 2, 3);
		
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(gp);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Anmeldung");
		primaryStage.show();
		
		
		anmelden.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				mitarbeiterverwaltung = new Mitarbeiterverwaltung();
				mitarbeiterverwaltung.getMitarbeiterListe().add(new Mitarbeiter("Arben", "Kurtishi", 123456L));

				mitarbeiterverwaltung.speichern(new File ("MitarbeiterListe.txt"));
				mitarbeiterverwaltung.laden(new File("MitarbeiterListe.txt"));
				
				
				boolean passt = mitarbeiterverwaltung.ueberpruefen(iDFeld.getText(), Long.parseLong(passFeld.getText()));
				
				if (passt)
				{
					primaryStage.close();
					Hauptfenster hauptFenster = new Hauptfenster();
					hauptFenster.showView();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "ID und Passwort stimmen nicht überein. \nBei Fragen wenden Sie sich an den Administrator!");
				}
			}
		});
	}

	
	public static void main (String[] args)
	{
		launch(args);
	}
	
}
