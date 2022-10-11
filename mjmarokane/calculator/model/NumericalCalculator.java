package mjmarokane.calculator.model;

import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.math.BigDecimal;

/**
 * A class representing a calculator
 *
 * @author Mphotle J. Marokane
 * @version 1.0.0
 */
public class NumericalCalculator
{
	private static Pattern PTN_NUMBER = Pattern.compile("(\\d+.\\d+)|\\d+");
	private static Pattern PTN_OPERATOR = Pattern.compile("\\+|\\-|\\*|\\/");
	
	private static BigDecimal[] dblNumbers = null;
	private static char[] chOperators = null;
	
	/**
	 * <p>Calculates the answer of a numeric mathametical expression.</p>
	 * @param strExpression A mathematical expression with the following operators:
	 * multiplication(*), addition(+), subtraction(*) and division(/).
	 * <p>Argument format:</p>
	 * <ul>
	 * <li>Only positive integers and decimal numbers are allowed in the expression.</li>
	 * <li>Operators and numbers must be sepparated by a single space character.</li>
	 * <li>Numbers must not have space charecter(s) in between.</li>
	 * <li>A decimal point is represented by a period; not a comma.</li>
	 * </ul>
	 * <p>
	 * Here is an example of a valid expression: 434.3535 * 4533.3 / 99 + 7 - 4.2 - 0.2 * 0
	 * </p>
	 * @return Answer for the numeric mathametical expression provided.
	 */
	public static BigDecimal calculate(String strExpression)
	{
		dblNumbers = new BigDecimal[1];
		chOperators = new char[1];
		
		//convert the expression from string to arrays of decimals and operators
		fillArrays(strExpression);
		
		//apply BODMAS
		calcForOperator('/');
		calcForOperator('*');
		calcForOperator('-');
		calcForOperator('+');

		return dblNumbers[0];
	}
	
	/**
	 * Tests if an operator is valid to be used in this class.
	 * @param chCharecter The operator to be tested.
	 * @return true if the operator is valid; false otherwise;
	 */
	public static boolean isOperator(char chCharecter)
	{
		if (chCharecter == '+')
		{ return true; }
		else if (chCharecter == '-')
		{ return true; }
		else if (chCharecter == '/')
		{ return true; }
		else if (chCharecter == '*')
		{ return true; }
		
		return false;
	}
	
	private static void fillArrays(String strExpression)
	{
		StringTokenizer strExpressionTokens = new StringTokenizer(strExpression);
		
		while (strExpressionTokens.hasMoreTokens())
		{
			String strToken = strExpressionTokens.nextToken();
			Matcher mtrNumber = PTN_NUMBER.matcher(strToken);
			Matcher mtrOperator = PTN_OPERATOR.matcher(strToken);
			
			//if the token is a decimal
			if (mtrNumber.matches())
			{
				BigDecimal dblNumber = new BigDecimal(strToken);
				addDoubleToArray(dblNumber);
			}
			
			//otherwise if the token is an operator
			else if (mtrOperator.matches())
			{
				char chOperator = strToken.charAt(0);
				addOperatorToArray(chOperator);
			}
			else
			{
				System.err.printf("Error: %s did not match a number nor an operator.", strToken);
			}
		}
	}
	
	//Simplifies a numeric mathematical expression by evaluting for the operator provided.
	private static void calcForOperator(char chOperator)
	{
		//get the index of the operator in the array of operators is it exists
		int intOperator = operatorExists(chOperator);
		while(intOperator != -1)
		{
			//create temporary array to store the state of the main arrays to be converted to
			BigDecimal[] dblNewNumbers = new BigDecimal[dblNumbers.length - 1];
			char[] chNewOperators = new char[chOperators.length - 1];
			
			for (int i = 0; i < dblNumbers.length; i++)
			{
				
				if (i < intOperator)
				{
					dblNewNumbers[i] = dblNumbers[i];
					chNewOperators[i] = chOperators[i];
				}
				else if (i == intOperator)
				{
					if (chOperator == '/')
						{ dblNewNumbers[i] = dblNumbers[i].divide(dblNumbers[i + 1]); }
					else if (chOperator == '*')
						{ dblNewNumbers[i] = dblNumbers[i].multiply(dblNumbers[i + 1]); }
					else if (chOperator == '+')
						{ dblNewNumbers[i] = dblNumbers[i].add(dblNumbers[i + 1]); }
					else if (chOperator == '-')
						{ dblNewNumbers[i] = dblNumbers[i].subtract(dblNumbers[i + 1]); }
					chNewOperators[i] = chOperators[i + 1];
				}
				else if (i > intOperator & i < dblNumbers.length - 1)
				{
					dblNewNumbers[i] = dblNumbers[i + 1];
					if (i == dblNumbers.length - 2)
						{ continue; }
					chNewOperators[i] = chOperators[i + 1];
				}
			}
			
			dblNumbers = dblNewNumbers;
			chOperators = chNewOperators;
			
			intOperator = operatorExists(chOperator);
			
			//output each iteration of the calculation
			System.out.print("= ");
			for (int i = 0; i < dblNumbers.length - 1; i++)
			{
				System.out.print(dblNumbers[i] + " ");
				System.out.print(chOperators[i] + " ");
			}
			System.out.printf("%n");
		}
	}
	
	//checks if the provided operator exists in the expression
	private static int operatorExists(char chOperator)
	{
		//return the index of the operator when found
		for (int i = 0; i < dblNumbers.length - 1; i++)
		{
			if (chOperators[i] == chOperator)
				return i;
		}
		
		//return -1 if operator is not found
		int intNotFound = -1;
		return intNotFound;
	}
	
	//adds a double at the end of an array of the array of doubles(dblNumbers).
	private static void addDoubleToArray(BigDecimal dblNumber)
	{
		dblNumbers[dblNumbers.length - 1] = dblNumber;
		BigDecimal[] dblNewNumbers = new BigDecimal[dblNumbers.length + 1];
		System.arraycopy(dblNumbers, 0, dblNewNumbers, 0, dblNumbers.length); 
		dblNumbers = dblNewNumbers;
	}
	
	//adds an operator at the end of the array of operators(chOperators).
	private static void addOperatorToArray(char chOperator)
	{
		chOperators[chOperators.length - 1] = chOperator;
		char[] chNewOperators = new char[chOperators.length + 1];
		System.arraycopy(chOperators, 0, chNewOperators, 0, chOperators.length); // Copy the old elements into the new array.
		chOperators = chNewOperators; // Update the reference.
	}
}
