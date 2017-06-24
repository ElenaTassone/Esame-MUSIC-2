package it.polito.tdp.music;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.music.model.Artist;
import it.polito.tdp.music.model.Mese;
import it.polito.tdp.music.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MusicController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Mese> boxMese;

    @FXML
    private Button btnArtisti;

    @FXML
    private Button btnNazioni;

    @FXML
    private TextArea txtResult;

    private Model model ;


	public void setModel(Model model) {
		this.model = model ;
		boxMese.getItems().addAll(this.model.getMesi()) ;
		
		
		
	}

    @FXML
    void doElencoArtisti(ActionEvent event) {
    	Mese mese = boxMese.getValue() ;
    	if(mese == null){
    		txtResult.setText("ERRORE: Selezionare prima il mese");
    	}
    	else{
    		txtResult.clear() ;
    		List<Artist> top = model.getTop (mese) ;
    		Collections.sort(top, new Comparator<Artist> (){
    			public int compare(Artist a, Artist b){
    				return a.getAscolti()-b.getAscolti();
    			}
    		});
    		for(Artist a : top){
    			txtResult.appendText(a.toString()+" \n ");
    		}
    	}
    }

    @FXML
    void doMaxDistanza(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert btnArtisti != null : "fx:id=\"btnArtisti\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert btnNazioni != null : "fx:id=\"btnNazioni\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MusicA.fxml'.";

    }
}
