package GUI;

import java.io.File;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlleKundenAuswahlFenster 
{
	ListView<Kunde> kListe = new ListView<Kunde>();
	
	public void showView(Lager lager, Kundenverwaltung kv, Rechnungsverwaltung rv, String summe)
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
			
	//						Button kundenAnlegen = new Button("Kunde anlegen");
			Button bestaetigen = new Button("Bestätigen");
			HBox hButton = new HBox(bestaetigen);
			hButton.setSpacing(5);
			hButton.setAlignment(Pos.BOTTOM_RIGHT);
			
			bp.setTop(hSuche);
			bp.setCenter(kListe);
			bp.setBottom(hButton);
			bp.setPadding(new Insets(5));
			
			Stage st = new Stage();
			Scene scene = new Scene(bp);
			st.setScene(scene);
			st.setTitle("Kundenliste");
			st.show();
			
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
			
			bestaetigen.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{	
					if(kListe.getSelectionModel().getSelectedItem() != null)
					{
						Kunde kunde = new Kunde(kListe.getSelectionModel().getSelectedItem().getVorname(), kListe.getSelectionModel().getSelectedItem().getNachname(),
								kListe.getSelectionModel().getSelectedItem().getBday(), kListe.getSelectionModel().getSelectedItem().getStrasse(),
								kListe.getSelectionModel().getSelectedItem().getHausnr(), kListe.getSelectionModel().getSelectedItem().getPlz(),
								kListe.getSelectionModel().getSelectedItem().getOrt());
					
						lager.speichern(new File("Lagerbestand.txt"));
						
						if(new File("Rechnungsliste.txt").exists())
							rv.laden(new File("Rechnungsliste.txt"));
						Rechnung rech = new Rechnung(kunde, lager.getBestellung(), summe, rv);
						rv.getRechnungsliste().add(rech);
						rv.speichern(new File("Rechnungsliste.txt"));
						rech.ausgeben();
						JOptionPane.showMessageDialog(null, "Rechnung wird erstellt");
						
						st.close();
					}
				}
			});
			
		}	
	}
}
