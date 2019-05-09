package GUI;

import java.io.File;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.swing.JOptionPane;

import ESA.Kunde;
import ESA.Kundenverwaltung;
import ESA.Lager;
import ESA.Rechnung;
import ESA.Rechnungsverwaltung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KundenFenster 
{
	Kundenverwaltung kv = new Kundenverwaltung();
	Rechnungsverwaltung rv = new Rechnungsverwaltung();
	ListView<Kunde> kListe = new ListView<Kunde>();

	// @Override
	// public void start(Stage primaryStage) throws Exception
	public void showView(Lager lager, String summe) 
	{
		
		Label vorname = new Label("Vorname");
		Label nachname = new Label("Nachname");
		Label bday = new Label("Geburtstag");
//		Label adresse = new Label("Adresse");
		Label strasse = new Label("Straße");
		Label hausnr = new Label("Hausnr.");
		Label plz = new Label("PLZ");
		Label ort = new Label("Ort");

		TextField vornameFeld = new TextField();
		vornameFeld.setMinWidth(265);
		TextField nachnameFeld = new TextField();
		nachnameFeld.setMinWidth(265);
		TextField bdayFeld = new TextField();
		bdayFeld.setMinWidth(265);
		TextField strasseFeld = new TextField();
		strasseFeld.setMaxWidth(120);
		TextField hausnrFeld = new TextField();
		hausnrFeld.setMaxWidth(120);
		TextField plzFeld = new TextField();
		plzFeld.setMaxWidth(120);
		TextField ortFeld = new TextField();
		ortFeld.setMaxWidth(120);
		
		GridPane gpAdresse = new GridPane();
		gpAdresse.add(strasse, 1, 1);
		gpAdresse.add(strasseFeld, 2, 1);
		gpAdresse.add(hausnr, 3, 1);
		gpAdresse.add(hausnrFeld, 4, 1);
		gpAdresse.add(plz, 1, 2);
		gpAdresse.add(plzFeld, 2, 2);
		gpAdresse.add(ort, 3, 2);
		gpAdresse.add(ortFeld, 4, 2);
		
		gpAdresse.setPadding(new Insets(5));
		gpAdresse.setHgap(5);
		gpAdresse.setVgap(5);
		
		Button bestaetigen = new Button("Bestätigen");
		Button kundenSuche = new Button("Kunde suchen");

		GridPane gp = new GridPane();

		gp.add(vorname, 1, 1);
		gp.add(vornameFeld, 2, 1);

		gp.add(nachname, 1, 2);
		gp.add(nachnameFeld, 2, 2);

		gp.add(bday, 1, 3);
		gp.add(bdayFeld, 2, 3);

//		gp.add(adresse, 1, 4);
//		gp.add(gpAdresse, 2, 4);
//
//		gp.add(kundenSuche, 1, 5);
//		gp.add(bestaetigen, 2, 5);

		gp.setPadding(new Insets(5));
		gp.setHgap(5);
		gp.setVgap(5);
		
		HBox hButton = new HBox(kundenSuche, bestaetigen);
		hButton.setPadding(new Insets(5));
		hButton.setSpacing(5);
		hButton.setAlignment(Pos.BOTTOM_RIGHT);
		
		VBox vb = new VBox(gp, gpAdresse, hButton);
		
		Stage s = new Stage();
		Scene scene = new Scene(vb);
		s.setScene(scene);
		s.setTitle("Kundenverwaltung");
		s.show();

		bestaetigen.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				if (vornameFeld.getText().isEmpty() | nachnameFeld.getText().isEmpty() | bdayFeld.getText().isEmpty()
						| strasseFeld.getText().isEmpty() | hausnrFeld.getText().isEmpty() | plzFeld.getText().isEmpty()
						| ortFeld.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bitte geben Sie vollständige Daten ein!");
				}
				else 
				{
					Kunde kunde = new Kunde(vornameFeld.getText(), nachnameFeld.getText(), bdayFeld.getText(),
							strasseFeld.getText(), Integer.parseInt(hausnrFeld.getText()), Integer.parseInt(plzFeld.getText()), ortFeld.getText());

					if (new File("Kundenliste.txt").exists())
						kv.laden(new File("Kundenliste.txt"));

					boolean hinzugefuegt = kv.hinzufuegen(kunde);
					
					if (hinzugefuegt)
					{
						kv.speichern(new File("Kundenliste.txt"));
						JOptionPane.showMessageDialog(null, "Kunde wurde hinzugefügt");
						
						lager.speichern(new File("Lagerbestand.txt"));
						
						if(new File("Rechnungsliste.txt").exists())
							rv.laden(new File("Rechnungsliste.txt"));
						
						Rechnung rech = new Rechnung(kunde, lager.getBestellung(), summe, rv);
						rv.getRechnungsliste().add(rech);
						rv.speichern(new File("Rechnungsliste.txt"));
						rech.ausgeben();
						JOptionPane.showMessageDialog(null, "Rechnung wird erstellt");
					} 
					else
						JOptionPane.showMessageDialog(null, "Dieser Kunde ist bereits abgespeichert");

					s.close();
				}
			}
		});

		kundenSuche.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				AlleKundenAuswahlFenster akaf = new AlleKundenAuswahlFenster();
				akaf.showView(lager, kv, rv, summe);
				s.close();
			}
		});
	}
}
