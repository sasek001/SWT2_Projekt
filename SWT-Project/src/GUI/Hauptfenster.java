package GUI;

import java.io.File;
import java.text.DecimalFormat;

import ESA.Artikel;
import ESA.Kunde;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Hauptfenster 
{
	Lager lager = new Lager();
	Rechnungsverwaltung rv = new Rechnungsverwaltung();
	DecimalFormat f = new DecimalFormat("###,##0.00");
	
	@SuppressWarnings("unchecked")
//	@Override
//	public void start(Stage primaryStage) throws Exception
	public void showView()
	{
		BorderPane bp = new BorderPane();
		
		//Menübar:
		MenuBar mb = new MenuBar();
		
		Menu datei = new Menu("Datei");
		
		MenuItem laden = new MenuItem("Laden");
		MenuItem speichern = new MenuItem("Speichern");
		
		datei.getItems().addAll(speichern, laden);
		
		Menu aktionen = new Menu("Aktionen");
		
		MenuItem kunden = new MenuItem("Kunden");
		
		aktionen.getItems().addAll(kunden);
		
		mb.getMenus().addAll(datei, aktionen);
		bp.setTop(mb);
		
		
		//Shortcuts
		laden.setAccelerator(KeyCombination.valueOf("Ctrl+L"));
		speichern.setAccelerator(KeyCombination.valueOf("Ctrl + S"));
		
		
		TextField suchleiste = new TextField("gesuchter Artikel");
		suchleiste.setPrefWidth(200);
		
		//Artikelübersicht
		TableView<Artikel> alleArtikel = new TableView<>();
		TableColumn<Artikel, String> bezeichnung = new TableColumn<>("Bezeichnung");
		bezeichnung.setMinWidth(400);
		TableColumn<Artikel, Double> preis = new TableColumn<>("Preis");
		preis.setMinWidth(160);
		TableColumn<Artikel, Integer> menge = new TableColumn<>("Menge");
		menge.setMinWidth(80);
		alleArtikel.getColumns().addAll(bezeichnung, preis, menge);
		
		bezeichnung.setCellValueFactory(new PropertyValueFactory<>("name"));
		preis.setCellValueFactory(new PropertyValueFactory<>("preis"));
		menge.setCellValueFactory(new PropertyValueFactory<>("menge"));

		//Bestellübersicht
		TableView<Artikel> bestellung = new TableView<>();
		TableColumn<Artikel, String> bezeichnungBestell = new TableColumn<>("Bezeichnung");
		bezeichnungBestell.setMinWidth(200);
		TableColumn<Artikel, Double> preisBestell = new TableColumn<>("Preis");
		preisBestell.setMinWidth(100);
		TableColumn<Artikel, Integer> mengeBestell = new TableColumn<>("Menge");
		mengeBestell.setMinWidth(60);
		bestellung.getColumns().addAll(bezeichnungBestell, preisBestell, mengeBestell);
		
		bezeichnungBestell.setCellValueFactory(new PropertyValueFactory<>("name"));
		preisBestell.setCellValueFactory(new PropertyValueFactory<>("preis"));
		mengeBestell.setCellValueFactory(new PropertyValueFactory<>("menge"));
		
		//Buttons
		Button hinzufuegen = new Button("Hinzufügen");
		Button entfernen = new Button("Entfernen");
		Button bestaetigen = new Button("Bestätigen");
		Button suchenButton = new Button("Suchen");

		
		//CENTER
		BorderPane bpCenter = new BorderPane();
		Label labelArikel = new Label("Artikelübersicht");
		labelArikel.setFont(new Font(20));
		HBox hSuchleiste = new HBox(suchleiste, suchenButton);
		hSuchleiste.setSpacing(5);
		VBox vBoxCenterTop = new VBox(labelArikel, hSuchleiste);
		vBoxCenterTop.setPadding(new Insets(5));
		vBoxCenterTop.setSpacing(5);
		bpCenter.setTop(vBoxCenterTop);
		bpCenter.setCenter(alleArtikel);
		
		VBox vHinzu = new VBox(hinzufuegen);
		vHinzu.setPadding(new Insets(5));
		bpCenter.setBottom(vHinzu);
		bpCenter.setPadding(new Insets(5));
		
		bp.setCenter(bpCenter);
		
		
		//RIGHT
		BorderPane bpRight = new BorderPane();
		Label labelBestellung = new Label("Bestellung");
		labelBestellung.setFont(new Font(20));
		VBox vLabelBestellung = new VBox (labelBestellung);
		vLabelBestellung.setPadding(new Insets(5));
		bpRight.setTop(vLabelBestellung);
		bpRight.setCenter(bestellung);
		
		Label labelSumme = new Label(" Summe:");
		labelSumme.setFont(new Font(15));
		TextField feldSumme = new TextField("0.00€");
		feldSumme.maxWidth(30);
		feldSumme.setEditable(false);;
		feldSumme.setAlignment(Pos.BOTTOM_RIGHT);
		HBox hButtons = new HBox(entfernen, bestaetigen, labelSumme, feldSumme);
		hButtons.setSpacing(5);
		hButtons.setPadding(new Insets(5));
		bpRight.setBottom(hButtons);
		
		bpRight.setBottom(hButtons);
		bpRight.setPadding(new Insets(5));
		
		bp.setRight(bpRight);
	
		speichern.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				lager.speichern(new File("Lagerbestand.txt"));
			}
		});
		
		laden.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				alleArtikel.getItems().clear();
				lager.laden(new File("Lagerbestand.txt"));
				alleArtikel.getItems().addAll(lager.getArtListe());
				
				bestellung.getItems().clear();
				lager.getBestellung().getBestellListe().removeAll(lager.getBestellung().getBestellListe());
			}
		});
		
		kunden.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				AlleKundenFenster akf = new AlleKundenFenster();
				akf.showView();
			}
		});
		
		suchenButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				alleArtikel.getItems().clear();
				if (!lager.getArtListe().isEmpty())
					for(int i = 0; i<lager.getArtListe().size(); i++)
						if(suchleiste.getText().equals(lager.getArtListe().get(i).getName()))
						{
							alleArtikel.getItems().add(lager.getArtListe().get(i));
							alleArtikel.refresh();
						}
			}
		});
		
		hinzufuegen.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				if(alleArtikel.getSelectionModel().getSelectedItem() != null)
				{
					lager.lagerZuBestellung(alleArtikel.getSelectionModel().getSelectedItem());
					
					alleArtikel.getItems().clear();
					alleArtikel.getItems().addAll(lager.getArtListe());
					
					bestellung.getItems().clear();
					bestellung.getItems().addAll(lager.getBestellung().getBestellListe());
					
					alleArtikel.refresh();
					bestellung.refresh();
				
					
					double summe = 0;
					for (int i = 0; i<bestellung.getItems().size(); i++)
						summe += bestellung.getItems().get(i).getPreis() * bestellung.getItems().get(i).getMenge();
					feldSumme.setText(f.format(summe) + "€");
				}
			}
		});
		
		entfernen.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				if(bestellung.getSelectionModel().getSelectedItem() != null)
				{
					lager.bestellungZuLager(bestellung.getSelectionModel().getSelectedItem());
					
					alleArtikel.getItems().clear();
					alleArtikel.getItems().addAll(lager.getArtListe());
					
					bestellung.getItems().clear();
					bestellung.getItems().addAll(lager.getBestellung().getBestellListe());
					
					alleArtikel.refresh();
					bestellung.refresh();
					
					double summe = 0;
					for (int i = 0; i<bestellung.getItems().size(); i++)
						summe += bestellung.getItems().get(i).getPreis() * bestellung.getItems().get(i).getMenge();
					feldSumme.setText(f.format(summe) + "€");
				}
			}
		});

		bestaetigen.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				KundenFenster kf = new KundenFenster();
				kf.showView(lager, feldSumme.getText());
				
				bestellung.getItems().clear();
			}
		});
		
		Stage s = new Stage();
		Scene scene = new Scene(bp);
		s.setScene(scene);
		s.setTitle("ESA-Handel");
		s.show();
	}
}
