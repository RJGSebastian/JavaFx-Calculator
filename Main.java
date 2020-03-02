package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    String lastOutput = "";
    String lastInput = "";

    int PREFWIDTH = 80;
    int PREFHEIGHT = 80;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Calculator");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        Label outputField = new Label("");
        outputField.setAlignment(Pos.CENTER);
        grid.add(outputField, 0, 0, 4, 1);

        TextField inputField = new TextField();
        inputField.setPrefSize(PREFWIDTH, PREFHEIGHT);
        grid.add(inputField, 0, 1, 4, 1);

        int row = 3;
        int col = 2;
        row = placeNumButtons(grid, inputField, row, col);

        col = 1;

        placeSignButtons(grid, inputField, outputField, row, col);

        // grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 5 * PREFWIDTH, 7 * PREFHEIGHT);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(this.getClass().getResource("CalculatorCSS.css").toExternalForm());

        primaryStage.show();
    }

    private void placeSignButtons(GridPane grid, TextField inputField, Label outputField, int row, int col) {
        for (String sign : new String[]{".", "=", "+", "-", "*", "/", "^", ")", "(", "C"}){
            Button btn = new Button(" " + sign + " ");
            btn.setPrefSize(PREFWIDTH, PREFHEIGHT);
            grid.add(btn, col, row);

            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    switch (sign) {
                        case "C":
                            inputField.setText("");
                            lastInput = "";
                            lastOutput = "";
                            outputField.setText("");
                            break;
                        case "=":
                            Splitter splitter = new Splitter();
                            lastOutput = splitter.calculate(inputField.getText());
                            if (lastOutput.equals("Invalid Input")){
                                outputField.setText(lastOutput);
                            } else {
                                outputField.setText(inputField.getText() + " = " + lastOutput);
                            }
                            break;
                        case "^":
                            if (inputField.getText().equals(lastInput) && !lastOutput.equals("Invalid Input")) {
                                inputField.setText(lastOutput + "^(");
                            } else {
                                inputField.setText(inputField.getText() + "^(");
                            }
                            break;
                        default:
                            if (inputField.getText().equals(lastInput) && !lastOutput.equals("Invalid Input")) {
                                inputField.setText(lastOutput + sign);
                            } else {
                                inputField.setText(inputField.getText() + sign);
                            }
                    }
                }
            });

            if (row != 2) {
                switch (col) {
                    case 1:
                    case 2:
                        col++;
                        break;
                    case 3:
                        row--;
                }
            } else {
                col--;
            }
        }
    }

    private int placeNumButtons(GridPane grid, TextField inputField, int row, int col) {
        for (int i = 9; i >= 0; i--){
            switch (i){
                case 6:
                case 3:
                    row++;
                    col = 2;
                    break;
                case 0:
                    row++;
                    col = 0;
                default:
                    break;
            }

            Button btn = new Button(" " + i + " ");
            btn.setPrefSize(PREFWIDTH, PREFHEIGHT);
            grid.add(btn, col, row);

            int finalI = i;
            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    inputField.setText(inputField.getText() + finalI);
                }
            });

            col--;
        }
        return row;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
