package de.leeksanddragons.tools.dialog.javafx.window;

import de.leeksanddragons.tools.dialog.javafx.FXMLWindow;
import de.leeksanddragons.tools.dialog.javafx.controller.MainWindowController;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Created by Justin on 29.08.2017.
 */
public class MainWindow extends FXMLWindow {

    public MainWindow () {
        super("Leeks & Dragons - Dialog Tool", 820, 640, "./data/ui/mainwindow.fxml", new MainWindowController());

        //add handler which will be executed, if user closes window
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        //set window position to center and focus window
        this.stage.centerOnScreen();
        this.stage.requestFocus();

        //set window visible
        this.stage.show();
    }

}
