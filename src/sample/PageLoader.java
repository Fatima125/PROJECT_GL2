package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class PageLoader {
    private Pane page;

    public Pane getPage(String fxml) {
        try {
            URL url = Main.class.getResource(fxml);
            if (url == null) {
                throw new java.io.FileNotFoundException("file not found");
            }

            page = new FXMLLoader().load(url);
        }
        catch (Exception e) {
            System.out.println("no file named "+fxml+" was found");
        }
        return page;
    }
}
