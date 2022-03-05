import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.event.*;
import javax.script.*;
import java.util.logging.*;
public class CalcUi extends Application {
    private String value = "";
    private TextField textField;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calc UI");
        primaryStage.centerOnScreen(); 
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(grid, 320, 370, Color.LAVENDER));
        HBox[] hbox = new HBox[5];
        for (int i = 0; i < 5; i++)
            hbox[i] = new HBox();
        hbox[0].setPadding(new Insets(13, 18, 10, 18));
        hbox[1].setPadding(new Insets(0, 18, 4, 18));
        hbox[2].setPadding(new Insets(0, 18, 4, 18));
        hbox[3].setPadding(new Insets(0, 18, 4, 18));
        hbox[4].setPadding(new Insets(0, 18, 15, 18));
        for (int i = 0; i < 5; i++)
            grid.add(hbox[i], 0, i);
        textField = new TextField();
        textField.setPrefSize(284, 40);
        textField.setAlignment(Pos.CENTER_LEFT);
        final String[] str = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", ".", "=", "/"};
        Button[] buttons = new Button[16];
        for (int i = 0; i < 16; i++) {
                buttons[i] = new Button(str[i]);
                buttons[i].setPrefSize(68, 68);
                HBox.setMargin(buttons[i], new Insets(0, 4, 0, 0));
        }
        hbox[0].getChildren().add(textField);
        hbox[1].getChildren().addAll(buttons[0], buttons[1], buttons[2], buttons[3]);
        hbox[2].getChildren().addAll(buttons[4], buttons[5], buttons[6], buttons[7]);
        hbox[3].getChildren().addAll(buttons[8], buttons[9], buttons[10], buttons[11]);
        hbox[4].getChildren().addAll(buttons[12], buttons[13], buttons[14], buttons[15]);
        for (int i = 0; i < 16; i++) {
            if (i != 14)
                evaButton(buttons, str, i);
        }
        buttons[14].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
                ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
                try {
                    Object exp = scriptEngine.eval(textField.getText());
                    textField.setText(String.valueOf(exp));
                    value = "";
                } catch (ScriptException ex) {
                    value = "";
                    Logger.getLogger(CalcUi.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        });
        primaryStage.show();
    }
    public void evaButton(Button[] buttons, String[] str, int i) {
        buttons[i].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                value += str[i];
                textField.setText(value);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
