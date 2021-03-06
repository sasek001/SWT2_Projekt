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
import javafx.scene.control.TextField;
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
		
			TextField suchleiste = new TextField("Kundenname");
			Button sucheButton = new Button("suchen");
			Button refresh = new Button("R");
			
			HBox hSuche = new HBox(suchleiste, sucheButton, refresh);
			hSuche.setPadding(new Insets(5));
			hSuche.setSpacing(5);
			
//			Button kundenAnlegen = new Button("Kunde anlegen");
			Button bestaetigen = new Button("Bestštigen");
			HBox hButton = new HBox(bestaetigen);
			hButton.setSpacing(5);
			hButton.setAlignment(Pos.BOTTOM_RIGHT);
			
			bp.setTop(hSuche);
			bp.setCenter(kListe);
			bp.setBottom(hButton);
			bp.setPadding(new Insets(5));
			
			
			sucheButton.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					kListe.getItems().clear();
					if (!kv.getKundenListe().isEmpty())
						for(int i = 0; i<kv.getKundenListe().size(); i++)
							if(suchleiste.getText().equals(kv.getKundenListe().get(i).getVorname()) 
								| suchleiste.getText().equals(kv.getKundenListe().get(i).getNachname()) 
								| suchleiste.getText().equals(kv.getKundenListe().get(i).getBday()))
							{
								kListe.getItems().add(kv.getKundenListe().get(i));
								kListe.refresh();
							}
				}
			});
			
			refresh.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					kListe.getItems().clear();
					kv.laden(new File("Kundenliste.txt"));
					kListe.getItems().addAll(kv.getKundenListe());
					kListe.refresh();
				}
			});
			
//			kundenAnlegen.setOnAction(new EventHandler<ActionEvent>() 
//			{
//				@Override
//				public void handle(ActionEvent event) 
//				{
//					KundenFenster kf = new KundenFenster();
//					kf.showView();
//				}
//			});
			
			bestaetigen.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					kListe.getSelectionModel().getSelectedItem();
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
			KundenFenster kf = new KundenFenster();
//			kf.showView();
		}
	}
}
