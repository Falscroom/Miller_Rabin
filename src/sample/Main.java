package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.math.BigInteger;

public class Main extends Application {

    public TextField number;
    public TextField result;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Simplicity Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void checkIt() {
        Controller cryptController = new Controller();
        BigInteger n = new BigInteger(number.getText());
        if (!cryptController.simpleTest(n))
            if(cryptController.millerRabin(n))
                result.setText("probably simple");
            else
                result.setText("Complain");
        else
            result.setText("Complain");
    }

    public void findPrev() {
        Controller cryptController = new Controller();
        BigInteger n = new BigInteger(number.getText()).subtract(new BigInteger("2"));
        if (n.getLowestSetBit() != 0)
            n = n.subtract(new BigInteger("1"));
        while (!cryptController.millerRabin(n)) {
            n = n.subtract(new BigInteger("2"));
        }
        result.setText(String.valueOf(n));
    }

    public void findNext() {
        Controller cryptController = new Controller();
        BigInteger n = new BigInteger(number.getText()).add(new BigInteger("2"));
        if (n.getLowestSetBit() != 0)
            n = n.add(new BigInteger("1"));
        while (!cryptController.millerRabin(n)) {
            n = n.add(new BigInteger("2"));
        }
        result.setText(String.valueOf(n));
    }
}
