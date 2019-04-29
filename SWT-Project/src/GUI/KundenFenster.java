package GUI;
import java.io.File;

import javax.swing.JOptionPane;

import ESA.Kunde;
import ESA.Kundenverwaltung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KundenFenster 
{
	Kundenverwaltung kv = new Kundenverwaltung();
	boolean ok;
	
//	@Override
//	public void start(Stage primaryStage) throws Exception 
	public void showView()
	{
		
		Label name = new Label("Name");
		Label bday = new Label("Geburtstag");
		Label adresse = new Label("Adresse");
		
		TextField vornameFeld = new TextField("Vorname");
		TextField nachnameFeld = new TextField("Nachname");
		TextField bdayFeld = new TextField("Geburtstag");
		TextField adressFeld = new TextField("Adresse");
		
		Button bestaetigen = new Button("Bestätigen");
		
		GridPane gp = new GridPane();
		
		HBox hName = new HBox(vornameFeld,nachnameFeld);
		hName.setSpacing(5);
		
		gp.add(name, 1, 1);
		gp.add(hName, 2, 1);
		
		gp.add(bday, 1, 2);
		gp.add(bdayFeld, 2, 2);

		gp.add(adresse, 1, 3);
		gp.add(adressFeld, 2, 3);
		
		gp.add(bestaetigen, 2, 4);
		
		gp.setPadding(new Insets(5));
		gp.setHgap(5);
		gp.setVgap(5);
		
		Stage s = new Stage();
		Scene scene = new Scene(gp);
		s.setScene(scene);
		s.setTitle("Kundenverwaltung");
		s.show();
		
		bestaetigen.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				Kunde kunde = new Kunde(vornameFeld.getText(), nachnameFeld.getText(), bdayFeld.getText(), adressFeld.getText());
				
				if(new File("Kundenliste.txt").exists())
					kv.laden(new File("Kundenliste.txt"));
				
				boolean vorhanden = kv.hinzufuegen(kunde);
				if (!vorhanden)
					kv.speichern(new File("Kundenliste.txt"));
				else
					JOptionPane.showMessageDialog(null, "Dieser Kunde ist bereits abgespeichert");
				
				s.close();
			}
		});
	}
}
