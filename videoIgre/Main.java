package videoIgre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final String igriceFile = "igre.txt"; 
	private static List<VideoIgra> videoIgre = new ArrayList<>();
	private static Prodavnica prodavnica;
	
	private static Button ucitajBtn, prikaziBtn, sortirajBtn, dodajBtn;
	
	private static RadioButton pcBtn, ps4Btn, xBoxBtn, sveBtn, 
								imeBtn, cenaBtn,
								pcBtn2, ps4Btn2, xBoxBtn2;
	private static ToggleGroup tg3;
	
	private static TextField imeTf, cenaTf, zanrTf, zahtTf;
	
	private static TextArea igriceTA = new TextArea();


	public static void main(String[] args) {
		
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Video igre");
		VBox root = new VBox();	
		napraviGUI(root);
		
		ucitajIgrice();	
		prikaziIgrice();
		sortirajIgrice();
		dodajIgricu();
		
		Scene scene = new Scene(root, 400, 600);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	    primaryStage.show();		
	}
	
	private void napraviGUI(VBox root) {
		
		VBox top = new VBox();
		ucitajBtn = new Button("Ucitaj video igre");
		igriceTA.setPrefHeight(150);
		igriceTA.setEditable(false);
		
		top.getChildren().addAll(ucitajBtn, igriceTA);
		top.setSpacing(10);
		top.setAlignment(Pos.CENTER);
		
		HBox bottom = new HBox();		
		VBox left = new VBox();
		Label platforma = new Label("Platforma");
		
        pcBtn = new RadioButton("PC");
        ps4Btn = new RadioButton("PS4");
        xBoxBtn = new RadioButton("XBOX One");
        sveBtn = new RadioButton("Sve");
        ToggleGroup tg1 = new ToggleGroup();
        
        pcBtn.setToggleGroup(tg1);
        ps4Btn.setToggleGroup(tg1);
        xBoxBtn.setToggleGroup(tg1);
        sveBtn.setToggleGroup(tg1);
		
        prikaziBtn = new Button("Prikazi");      
		left.getChildren().addAll(platforma, pcBtn, ps4Btn, xBoxBtn, sveBtn, prikaziBtn);
		left.setPrefWidth(80);
		left.setSpacing(10);
			
		VBox middle = new VBox();
		Label sortirajLb = new Label("Sortiraj po:");
		
        imeBtn = new RadioButton("Imenu");
        cenaBtn = new RadioButton("Ceni");
        ToggleGroup tg2 = new ToggleGroup();
        
        imeBtn.setToggleGroup(tg2);
        cenaBtn.setToggleGroup(tg2);
        
        sortirajBtn = new Button("Sortiraj");	
		middle.getChildren().addAll(sortirajLb, imeBtn, cenaBtn, sortirajBtn);
		middle.setPrefWidth(60);
		middle.setSpacing(10);
			
		VBox right = new VBox();
		
		Label imeLb = new Label("Ime:");
		 imeTf = new TextField();
		imeTf.setMaxWidth(150);
		Label cenaLb = new Label("Cena:");
		cenaTf = new TextField();
		cenaTf.setMaxWidth(150);
		Label zanrLb = new Label("Zanr:");
		zanrTf = new TextField();
		zanrTf.setMaxWidth(150);
		Label zahtLb = new Label("Zahtevnost:");
		zahtTf = new TextField();
		zahtTf.setMaxWidth(150);
		
        pcBtn2 = new RadioButton("PC");
		ps4Btn2 = new RadioButton("PS4");
        xBoxBtn2 = new RadioButton("XBOX ONE");
        tg3 = new ToggleGroup();
        
        pcBtn2.setToggleGroup(tg3);
        ps4Btn2.setToggleGroup(tg3);
        xBoxBtn2.setToggleGroup(tg3);
        
        dodajBtn = new Button("Dodaj");		
		right.getChildren().addAll(imeLb, imeTf, cenaLb, cenaTf,
									zanrLb, zanrTf, zahtLb, zahtTf,
									pcBtn2, ps4Btn2, xBoxBtn2, dodajBtn);
		right.setPrefWidth(150);
		right.setSpacing(10);
		
		bottom.getChildren().addAll(left, middle, right);
		bottom.setSpacing(10);
				
		root.getChildren().addAll(top, bottom);
		root.setSpacing(20);
		root.setPadding(new Insets(15));	
	}
	
	public void ucitajIgrice() {
		
		ucitajBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				igriceTA.setText("");
				videoIgre.addAll(Prodavnica.ucitajIgre(igriceFile));
				prodavnica = new Prodavnica(videoIgre);
				int broj = prodavnica.getIgre().size();
				igriceTA.setText("Broj ucitanih igara: " + broj);	
			}		
		});		
	}
	
	public void prikaziIgrice() {
		
		prikaziBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				igriceTA.clear();
				
				if (videoIgre.isEmpty()) {
					igriceTA.setText("Prvo ucitajte igrice!");
				}
				else {
					prodavnica = new Prodavnica(videoIgre);
				}
				for (VideoIgra igrica : videoIgre) {
					
					if (igrica.getPlatforma().equalsIgnoreCase("pc") && pcBtn.isSelected()) {
						igriceTA.appendText("* " + igrica + "\n");
					}
					if (igrica.getPlatforma().equalsIgnoreCase("ps4") && ps4Btn.isSelected()) {
						igriceTA.appendText("* " + igrica + "\n");
					}
					if (igrica.getPlatforma().startsWith("xb") && xBoxBtn.isSelected()) {
						igriceTA.appendText("* " + igrica + "\n");
					}
					if (!pcBtn.isSelected() && !ps4Btn.isSelected() && !xBoxBtn.isSelected()) {
						igriceTA.setText(prodavnica.toString());
					}
				}						
			}			
		});
	}
	
	public void sortirajIgrice() {
		
		sortirajBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (videoIgre.isEmpty()) {
					igriceTA.setText("Prvo ucitajte igrice!");
				}
				else if (!imeBtn.isSelected() && !cenaBtn.isSelected()) {
					igriceTA.setText("Niste izabrali nijednu opciju sortiranja!");
				}
				else {
					if (imeBtn.isSelected()) {
						sortirajPoImenu();
					}
					if (cenaBtn.isSelected()) {
						sortirajPoCeni();
					}
					prodavnica = new Prodavnica(videoIgre);
					igriceTA.setText(prodavnica.toString());
				}		
			}		
		});		
	}
	
	public void sortirajPoImenu() {	
		videoIgre.sort(new Comparator<VideoIgra>() {
			@Override
			public int compare(VideoIgra a, VideoIgra b) {
				int rez = a.getIme().compareTo(b.getIme());
				if (rez == 0) {
					return a.getPlatforma().compareTo(b.getPlatforma());
				}
				return rez;
			}		
		});
	}
	public void sortirajPoCeni() {	
		videoIgre.sort(new Comparator<VideoIgra>() {
			@Override
			public int compare(VideoIgra a, VideoIgra b) {
				int rez = Integer.compare(a.getCena(), b.getCena());
				if (rez == 0) {
					return a.getIme().compareTo(b.getIme());
				}
				return rez;
			}		
		});
	}
	
	public void dodajIgricu() {
		
		dodajBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				boolean ispravnostPodataka = false;
				
				try {
					String ime = imeTf.getText().trim();
					int cena = Integer.parseInt(cenaTf.getText().trim());
					String zanr = zanrTf.getText().trim();
					
					if (pcBtn2.isSelected()) {
						int zahtevnost = Integer.parseInt(zahtTf.getText().trim());
						videoIgre.add(new PCVideoIgra(ime, zanr, cena, zahtevnost));
						ispravnostPodataka = true;				
					}
					else if (ps4Btn2.isSelected()){
						videoIgre.add(new KonzolnaVideoIgra(ime, zanr, cena, "ps4"));
						ispravnostPodataka = true;
					}
					else if (xBoxBtn2.isSelected()){
						videoIgre.add(new KonzolnaVideoIgra(ime, zanr, cena, "xbone"));
						ispravnostPodataka = true;
					}
				} catch (NumberFormatException e) {
					ispravnostPodataka = false;
				}

				if (ispravnostPodataka) {
					igriceTA.setText("Uspesno je dodata igra!");
				}
				else {
					igriceTA.setText("Greska pri unosu!");
				}			
			}			
		});		
	}
}
