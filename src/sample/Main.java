package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.math.BigInteger;

public class Main extends Application {

    public TextField number;
    public TextField result;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void checkIt(ActionEvent actionEvent) {
        Controller cryptController = new Controller();
        if(cryptController.millerRabin(new BigInteger(number.getText())))
            result.setText("Simple");
        else
            result.setText("Complain");
    }

    public void findPrev(ActionEvent actionEvent) {
        Controller cryptController = new Controller();
        long currNumber = Long.parseLong(number.getText());
        currNumber -= 2;
        if(currNumber % 2 == 0)
            currNumber--;
        if(currNumber >= 3) {
            while (!cryptController.millerRabin(new BigInteger(""+currNumber))) {
                currNumber -= 2;
            }
            result.setText("" + currNumber);
        }
        else {
            result.setText("1");
        }
    }

    public void findNext(ActionEvent actionEvent) {
        Controller cryptController = new Controller();
        long currNumber = Long.parseLong(number.getText());
        currNumber += 2;
        if(currNumber % 2 == 0)
            currNumber++;
        while (!cryptController.millerRabin(new BigInteger(""+currNumber))) {
            currNumber += 2;
        }
        result.setText("" + currNumber);
    }
}
