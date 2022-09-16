import mjmarokane.calculator.ui.CalculatorPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		CalculatorPane root = new CalculatorPane();
		
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(new Scene(root, 260, 365));
		
		CalculatorPane.setAlignment(root, Pos.BASELINE_CENTER);
		primaryStage.show();
	}
}