package GUI;

import java.io.File;

import javax.swing.JOptionPane;

import ESA.Kunde;
import ESA.Kundenverwaltung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlleKundenFenster 
{
	Kundenverwaltung kv = new Kundenverwaltung();
	ListView<Kunde> kListe; 
	
	public void showView()
	{
		if (new File("Kundenliste.txt").exists())
			kv.laden(new File("Kundenliste.txt"));
		BorderPane bp = new BorderPane();
		
		kListe = new ListView<Kunde>();
		
		if (!kv.getKundenListe().isEmpty())
		{
			kListe.getItems().addAll(kv.getKundenListe());
		
			Button bestaetigen = new Button("Bestätigen");
			HBox hBest = new HBox(bestaetigen);
			hBest.setPadding(new Insets(5));
			hBest.setAlignment(Pos.CENTER);
			
			bp.setCenter(kListe);
			bp.setBottom(hBest);
			bp.setPadding(new Insets(5));
			
			
			bestaetigen.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					
				}
			});
			
			Stage s = new Stage();
			Scene scene = new Scene(bp);
			s.setScene(scene);
			s.setTitle("Kundenliste");
			s.show();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Es sind noch keine Kunden abgespeichert.");
			
		}
	}
}
