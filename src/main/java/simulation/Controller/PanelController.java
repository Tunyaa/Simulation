package simulation.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import simulation.App;

/**
 *
 * @author tunyaa
 */
public class PanelController {
    
    
    
    @FXML
    public void toPrimary() throws IOException{
        App.setRoot("primary");
    }
   public void initialize() {
       System.out.println("INIT");
   }
    
}
