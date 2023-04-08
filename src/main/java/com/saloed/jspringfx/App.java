package com.saloed.jspringfx;

import com.jpro.webapi.JProApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * JavaFX + SpringBoot + Jpro + Gradle + FXML application.
 *
 * @author Saloed
 */
@SpringBootApplication
public class App extends JProApplication {

    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) {
        context = new SpringApplicationBuilder(App.class)
            .web(WebApplicationType.NONE)
            .run();
        // load user interface as FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/saloed/jspringfx/fxml/main.fxml"));
        loader.setControllerFactory(context::getBean);
        loader.setResources(ResourceBundle.getBundle("l10n/jspringfx"));
        Scene scene = null;
        try {
            Parent root = loader.load();
            MainCtrl controller = loader.getController();
            controller.init(this);

            // create JavaFX scene
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Hello JSpringFx!");
        stage.setScene(scene);

        // open JavaFX window
        stage.show();
    }

    /**
     * Application entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
