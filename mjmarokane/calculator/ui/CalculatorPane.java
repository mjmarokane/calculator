package mjmarokane.calculator.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mjmarokane.calculator.model.NumericalCalculator;

/**
 * A class representing a Calculator pane used to display the calculator UI.
 * @author Mphotle J. Marokane
 * @version 1.0.0
 */

public class CalculatorPane extends StackPane{
	private final int DEFAULT_BUTTON_WIDTH = 65;
	private final int DEFAULT_BUTTON_HIEGHT = 65;
	private final int OPERATOR_BUTTON_WIDTH = 55;
	private final int OPERATOR_BUTTON_HIEGHT = 55;
	private final int DEFAULT_BUTTON_RADIUS = 20;
	private String expressionString = new String();
	private Text expressionText = new Text();
	private Text answerText = new Text();
	
	/**
	 * Creates and initiates the CalculatorPane.
	 */
	public CalculatorPane()
	{
		//The main layout of the class
		BorderPane rootBorderPane = new BorderPane();
		rootBorderPane.setStyle("-fx-background-color: black; ");
		//The layout which serves as the display of the calculator
		VBox displayVBox = createCalcDisplay();
		
		//The layout to contain the buttons of the calculator
		GridPane buttonsGridPane = createCalcButtons();
		
		//add display to the top section of the main layout
		rootBorderPane.setTop(displayVBox);
		//add the grid pane of buttons to the center section of the main layout
		rootBorderPane.setCenter(buttonsGridPane);
		this.getChildren().add(rootBorderPane);
	}
	
	//method to create the calculator buttons and return them in a layout
	private GridPane createCalcButtons()
	{
		GridPane buttonsGridPane = new GridPane();
		buttonsGridPane.setAlignment(Pos.CENTER);
		buttonsGridPane.setPadding(new Insets(10, 10, 10, 10));
		//the styling of a button of an operator
		String strOpButtonStyle = new String("-fx-text-fill: white; -fx-background-color: #111111; "
				+ "-fx-background-radius: " + DEFAULT_BUTTON_RADIUS + "px;");
		//the styling of a control button
		String strContButtonStyle = new String("-fx-text-fill: yellow; -fx-background-color: #111111; "
				+ "-fx-background-radius: " + DEFAULT_BUTTON_RADIUS + "px; ");
		String strHoverButtonStyle = new String("-fx-text-fill: white; -fx-background-color: #111111; "
				+ "-fx-background-radius: " + DEFAULT_BUTTON_RADIUS + "px;");
		//the stylling of a button of a digit
		String strDigButtonStyle = new String("-fx-background-color: black; -fx-text-fill: green;"
					+ " -fx-font-weight: bold; -fx-text-size: 15px;");
		
		//create a clear text button
		Button clearButton = new Button("clr");
		clearButton.setPrefSize(OPERATOR_BUTTON_WIDTH, 15);
		clearButton.setStyle(strContButtonStyle);
		buttonsGridPane.add(clearButton, 2, 0);
		
		//create the correct button
		Button correctButton = new Button("<<");
		correctButton.setPrefSize(OPERATOR_BUTTON_WIDTH, 15);
		correctButton.setStyle(strContButtonStyle);
		buttonsGridPane.add(correctButton, 3, 0);
		
		//Create the digit buttons
		Button[] digitButtons = new Button[10];
		for (int i = 0; i < digitButtons.length; i++)
		{
			digitButtons[i] = new Button(String.valueOf(i));
			digitButtons[i].setStyle(strDigButtonStyle);
			digitButtons[i].setPrefSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HIEGHT);
		}
		//create the decimal point button
		Button pointButton = new Button(".");
		pointButton.setPrefSize(OPERATOR_BUTTON_WIDTH, OPERATOR_BUTTON_HIEGHT);
		pointButton.setStyle(strOpButtonStyle);
		//create buttons for addition, multiplication, division, subtraction and equal operators
		Button equalButton = new Button("=");
		equalButton.setPrefSize(OPERATOR_BUTTON_WIDTH, OPERATOR_BUTTON_HIEGHT);
		equalButton.setStyle("-fx-text-fill: white; -fx-background-color: #0000ff; "
				+ "-fx-background-radius: " + DEFAULT_BUTTON_RADIUS + "px;");
		Button multiplyButton = new Button(String.valueOf('\u00D7'));
		multiplyButton.setPrefSize(OPERATOR_BUTTON_WIDTH, OPERATOR_BUTTON_HIEGHT);
		multiplyButton.setStyle(strOpButtonStyle);
		Button devideButton = new Button(String.valueOf('\u00F7'));
		devideButton.setPrefSize(OPERATOR_BUTTON_WIDTH, OPERATOR_BUTTON_HIEGHT);
		devideButton.setStyle(strOpButtonStyle);
		Button addButton = new Button("+");
		addButton.setPrefSize(OPERATOR_BUTTON_WIDTH, OPERATOR_BUTTON_HIEGHT);
		addButton.setStyle(strOpButtonStyle);
		Button subtractButton = new Button("-");
		subtractButton.setPrefSize(OPERATOR_BUTTON_WIDTH, OPERATOR_BUTTON_HIEGHT);
		subtractButton.setStyle(strOpButtonStyle);
		
		//add buttons to the GridPane
		int digitCounter = 1;
		for (int r = 3; r >= 1; r--)
		{
			for (int c = 0; c <= 2; c++)
			{
				buttonsGridPane.add(digitButtons[digitCounter], c, r);
				digitCounter++;
			}
		}
		
		//set onAction events 
		digitButtons[0].setOnAction(event -> {clickedDigit(0);});
		digitButtons[1].setOnAction(event -> {clickedDigit(1);});
		digitButtons[2].setOnAction(event -> {clickedDigit(2);});
		digitButtons[3].setOnAction(event -> {clickedDigit(3);});
		digitButtons[4].setOnAction(event -> {clickedDigit(4);});
		digitButtons[5].setOnAction(event -> {clickedDigit(5);});
		digitButtons[6].setOnAction(event -> {clickedDigit(6);});
		digitButtons[7].setOnAction(event -> {clickedDigit(7);});
		digitButtons[8].setOnAction(event -> {clickedDigit(8);});
		digitButtons[9].setOnAction(event -> {clickedDigit(9);});
		correctButton.setOnAction(event -> {clickedCorrect();});
		pointButton.setOnAction(event -> {clickedPoint();});
		equalButton.setOnAction(event -> {updateAnswer();});
		multiplyButton.setOnAction(event -> {clickedMathOperator('*');});
		devideButton.setOnAction(event -> {clickedMathOperator('/');});
		addButton.setOnAction(event -> {clickedMathOperator('+');});
		subtractButton.setOnAction(event -> {clickedMathOperator('-');});
		clearButton.setOnAction(event -> {clickedClear();});
		
		digitButtons[0].setOnMouseEntered(event -> {digitButtons[0].setStyle(strHoverButtonStyle);});
		digitButtons[0].setOnMouseExited(event -> {digitButtons[0].setStyle(strDigButtonStyle);});
		digitButtons[1].setOnMouseEntered(event -> {digitButtons[1].setStyle(strHoverButtonStyle);});
		digitButtons[1].setOnMouseExited(event -> {digitButtons[1].setStyle(strDigButtonStyle);});
		digitButtons[2].setOnMouseEntered(event -> {digitButtons[2].setStyle(strHoverButtonStyle);});
		digitButtons[2].setOnMouseExited(event -> {digitButtons[2].setStyle(strDigButtonStyle);});
		digitButtons[3].setOnMouseEntered(event -> {digitButtons[3].setStyle(strHoverButtonStyle);});
		digitButtons[3].setOnMouseExited(event -> {digitButtons[3].setStyle(strDigButtonStyle);});
		digitButtons[4].setOnMouseEntered(event -> {digitButtons[4].setStyle(strHoverButtonStyle);});
		digitButtons[4].setOnMouseExited(event -> {digitButtons[4].setStyle(strDigButtonStyle);});
		digitButtons[5].setOnMouseEntered(event -> {digitButtons[5].setStyle(strHoverButtonStyle);});
		digitButtons[5].setOnMouseExited(event -> {digitButtons[5].setStyle(strDigButtonStyle);});
		digitButtons[6].setOnMouseEntered(event -> {digitButtons[6].setStyle(strHoverButtonStyle);});
		digitButtons[6].setOnMouseExited(event -> {digitButtons[6].setStyle(strDigButtonStyle);});
		digitButtons[7].setOnMouseEntered(event -> {digitButtons[7].setStyle(strHoverButtonStyle);});
		digitButtons[7].setOnMouseExited(event -> {digitButtons[7].setStyle(strDigButtonStyle);});
		digitButtons[8].setOnMouseEntered(event -> {digitButtons[8].setStyle(strHoverButtonStyle);});
		digitButtons[8].setOnMouseExited(event -> {digitButtons[8].setStyle(strDigButtonStyle);});
		digitButtons[9].setOnMouseEntered(event -> {digitButtons[9].setStyle(strHoverButtonStyle);});
		digitButtons[9].setOnMouseExited(event -> {digitButtons[9].setStyle(strDigButtonStyle);});
		
		buttonsGridPane.add(digitButtons[0], 0, 4);
		buttonsGridPane.add(pointButton, 1, 4);
		buttonsGridPane.add(equalButton, 2, 4);
		buttonsGridPane.add(multiplyButton, 3, 1);
		buttonsGridPane.add(devideButton, 3, 2);
		buttonsGridPane.add(addButton, 3, 3);
		buttonsGridPane.add(subtractButton, 3, 4);

		return buttonsGridPane;
	}
	
	//Method to create calculator displays and return them in a layout
	private VBox createCalcDisplay()
	{
		VBox displayVBox = new VBox();
		
		//Configure the expresion text
		expressionText.setFont(Font.font(30));
		expressionText.setTextAlignment(TextAlignment.RIGHT);
		expressionText.setStyle("-fx-fill: #ffffff;");
		expressionText.setWrappingWidth(DEFAULT_BUTTON_WIDTH * 4);
		//wraps araund at 12 charecters
		
		//Configure the answer text
		answerText.setFont(Font.font(15));
		answerText.setTextAlignment(TextAlignment.RIGHT);
		answerText.setStyle("-fx-fill: #00ff00; ");
		answerText.setWrappingWidth(DEFAULT_BUTTON_WIDTH * 4);
		//wraps araund at 12 charecters
		
		displayVBox.getChildren().add(expressionText);
		displayVBox.getChildren().add(answerText);
		displayVBox.setSpacing(10);
		VBox.setMargin(answerText, new Insets(0, 0, 10, 0));
		
		return displayVBox;
	}
	
	//action when a digit is clicked
	private void clickedDigit(int intDigit)
	{
		expressionText.setText(expressionText.getText() + intDigit);
		expressionString += intDigit;
		System.out.println("Expression: " + expressionString);
		//clear the previous answer
		answerText.setText("");
	}
	
	//action when point is clicked 
	private void clickedPoint()
	{
		expressionText.setText(expressionText.getText() + '.');
		expressionString += '.';
		System.out.println("Expression: " + expressionString);
		//clear the previous answer
		answerText.setText("");
	}
	
	//action when correct is clicked
	private void clickedCorrect()
	{
		if (expressionString.length() <= 0)
			return;
		
		StringBuilder tempExpressionString = new StringBuilder((expressionText.getText()));
		StringBuilder tempExpressionString2 = new StringBuilder(expressionString);
		//remove an operator with the space char before and after it
		if(expressionString.length() > 1)
		{
			if (NumericalCalculator.isOperator(expressionString.charAt(expressionString.length() - 2)))
			{
				tempExpressionString.delete(tempExpressionString.length() - 3, tempExpressionString.length());
				expressionText.setText(new String(tempExpressionString));
				tempExpressionString2.delete(tempExpressionString2.length() - 3, tempExpressionString2.length());
			}
			//remove one charecter if the previous char is not an operator 
			else
			{
				expressionText.setText(new String(tempExpressionString.deleteCharAt(tempExpressionString.length() - 1)));
				tempExpressionString2.deleteCharAt(tempExpressionString.length());

			}
		}
		
		//remove one charecter if the previous char is not an operator 
		else
		{
			expressionText.setText(new String(tempExpressionString.deleteCharAt(tempExpressionString.length() - 1)));
			tempExpressionString2.deleteCharAt(tempExpressionString.length());

		}
		
		expressionString = new String(tempExpressionString);
		System.out.println("Expression: " + expressionString);
		//clear the previous answer
		answerText.setText("");
		
	}
	
	//action when an operator is clicked
	private void clickedMathOperator(char chOperator)
	{
		switch(chOperator) {	
		case '/':
			expressionText.setText(expressionText.getText() + ' ' + String.valueOf('\u00F7') + ' ');
			break;
		case '*':
			expressionText.setText(expressionText.getText() + ' ' + String.valueOf('\u00D7') + ' ');
			break;
		default: 
			expressionText.setText(expressionText.getText() + ' ' + chOperator + ' ');
		}
			
		expressionString += " " + chOperator + " ";
		System.out.println("Expression: " + expressionString);
		//clear the previous answer
		answerText.setText("");
	}

	//action when clear is clicked
	private void clickedClear() 
	{
		expressionText.setText("");
		expressionString = "";
		answerText.setText("");
	}
	
	//get the answer from the calculator library and update the display
	private void updateAnswer()
	{
		if(NumericalCalculator.calculate(expressionString).doubleValue() % 1 == 0)
		{
			answerText.setText("= " + (NumericalCalculator.calculate(expressionString).intValue()));
		}
		else
		{
			answerText.setText("= " + NumericalCalculator.calculate(expressionString).toString());
		}
		
		System.out.println("Answer: " + NumericalCalculator.calculate(expressionString).toString());
	}
}