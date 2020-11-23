/* 
*   A Java program to evaluate a string expression 
*   The string can be in arbitray unit 
*   The program converts to string to the SI unit string
*   The program returns the multiplicative factor to conver to the SI unit
*
*   @author  Chetan Raj Rupakheti
*   @date   2020-11-21 
*/

package controllers;

import java.util.Stack;
import java.util.HashMap;

public class UnitConverterHelper {
    StringBuffer unitStringInSI;
    String inputExpression;
    
    /**
     * Constructor method
     * @param inputExpression user input
     */
    public UnitConverterHelper(String inputExpression) {
        this.inputExpression = inputExpression;
        this.unitStringInSI = new StringBuffer();
    }

    /**
     * This method checks if a character is a special character
     * @param c
     * @return boolean
     */
    public boolean isSpecialChar(char c) {
        if (c == '(' || c == ')' || c == '/' || c == '*') {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Helper method to print error in input string
     * @param c
     * @param position in the string where error occurs
     */
    public void errorHelper(char c, String position) {
        StringBuffer errorBuffer = new StringBuffer(); // stores exact error
        errorBuffer.append(c);
        String errMsg = "Invalid character [".concat(errorBuffer.toString())
                        .concat("] at the [").concat(position)
                        .concat("] position");
        throw new IllegalArgumentException(errMsg);
    }

    /**
     * Checks for trailing or starting operators in a unit string 
     * @param inputExpression
     * @throws IllegalArgumentError if string is invalid
     */
    public void checkOperators() {
        char[] tokens = inputExpression.toCharArray();

        if (tokens[0] == '*' || tokens[0] == '/') {
            errorHelper(tokens[0], "start");
        }  
        else if (tokens[tokens.length - 1] == '*' || tokens[tokens.length - 1] == '/') {
            errorHelper(tokens[tokens.length - 1], "end");
        }
        else if (tokens[0] == '(' && (tokens[1] == '*' || tokens[1] == '/')) {
            errorHelper(tokens[1], "second");
        }
        else if (tokens[tokens.length - 1] == ')' && 
                (tokens[tokens.length - 2] == '*' || tokens[tokens.length - 2] == '/')) {
            errorHelper(tokens[tokens.length - 2], "second to last");
        }
        
        for(int i=0; i < tokens.length-1; i++) {
        	if (areOperators(tokens[i], tokens[i+1])) {
        		throw new IllegalArgumentException("consecutive operators not allowed");
        	}
        }
    }

    /**
     * Helper method to prevent evaluating units with consecutive operators
     * @param c
     * @param d
     * @return true if found
     */
    public boolean areOperators(char c, char d) {
    	char[] operators = {'*','/'};
		if ((c == operators[0] || c == operators[1]) && (d == operators[0] || d == operators[1])) {
			return true;
		}
		return false;
	}

	/**
     * Helper method to check parentheses are always closed
     * @param inputExpression
     * @throws IllegalArgumentException
     */
    public void checkParentheses() {
        char[] tokens = inputExpression.toCharArray();
        int countOpenBrace = 0;
        int countCloseBrace = 0;

        for (int i=0; i<tokens.length; i++) {
            if (tokens[i] == '(') {
                countOpenBrace += 1;
            }
            else if(tokens[i] == ')') {
                countCloseBrace += 1;
            }
        }

        if (countOpenBrace != countCloseBrace) {
            throw new IllegalArgumentException("Please recheck and fix your parentheses!");
        }
    }
    
    /**
     * Provides algorithm to parse and evaluate the expression.
     * Uses two stacks, one for the operators and another for operands.
     * Once any operator is popped, two operands are popped from the stack and result put on the operand's stack.
     * The last element on the operand stack is the final result that is eventually popped.
     * 
     * @return Multiplicative factor to be returned
     */
    public Double evaluate() {
		UnitRecords unitObj = new UnitRecords(); 
		unitObj.addCurrentStringRecords();
		unitObj.addCurrentunitNumericRecords();

        HashMap<String, String> strRecord = unitObj.unitStringRecord; 
        HashMap<String, Double> numericRecord = unitObj.unitNumericRecord; 
        char[] tokens = inputExpression.toCharArray(); 
        Stack<Double> siValues = new Stack<Double>(); 
        Stack<Character> operators = new Stack<Character>();  
        String aUnitStringInSI; // stores SI unit while parsing                      
        
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
            	continue;
            }
            if (!isSpecialChar(tokens[i])) {
                StringBuffer sbuf = new StringBuffer(); 
        
                while (i < tokens.length && !isSpecialChar(tokens[i]) && tokens[i] != ' ') {
                    sbuf.append(tokens[i++]);
                }

                // checking in the HashMap if the key exists before converting
                if (numericRecord.containsKey(sbuf.toString().toLowerCase()) && 
                    strRecord.containsKey(sbuf.toString().toLowerCase())) {
                    Double aValueInSI = numericRecord.get(sbuf.toString().toLowerCase());
                    aUnitStringInSI = strRecord.get(sbuf.toString().toLowerCase());

                    siValues.push(aValueInSI);
                    unitStringInSI.append(aUnitStringInSI); 
                }
                else {
	                String errorMsg = "\nInvalid unit (";
	                errorMsg = errorMsg.concat(sbuf.toString())
	                            .concat(") in the input! Please recheck the input.\n");
	                throw new IllegalArgumentException(errorMsg);
                }
               
                //correcting the offset.
                i--;
            }
            else if (tokens[i] == '(') {
                operators.push(tokens[i]);
                unitStringInSI.append(tokens[i]);
            }
            // solving entire brace until we hit opening brace
            else if (tokens[i] == ')') {
                unitStringInSI.append(tokens[i]);
                while (operators.peek() != '(') {
                    siValues.push(applyOp(operators.pop(), siValues.pop(), siValues.pop()));
                }
                operators.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
            	unitStringInSI.append(tokens[i]);
            	// comparing the precedence of current operator with the ones on stack    
	            while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())) {
	                siValues.push(applyOp(operators.pop(), siValues.pop(), siValues.pop()));
	            }	            
	            operators.push(tokens[i]);
            }
        }
 
        // Applying operations to the remaining siValues
        while (!operators.empty()) {
            siValues.push(applyOp(operators.pop(), siValues.pop(), siValues.pop()));
        }
        
        Double result = Double.parseDouble(String.format("%.13E",siValues.pop()));
        return result;
    }
 
    /**
     * A method to check precedence between operators
     * @param op1
     * @param op2
     * @return boolean true if op1 has precedence
     */
    public boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/')) {
            return false;
        }
        return true;
    }
 
    /**
     * Applies operators 
     * unit1 and unit2 are already converted to the SI
     * @param op
     * @param unit2
     * @param unit1
     * @return value in double
     */
    public Double applyOp(char op, Double unit2, Double unit1) {
        switch (op) {
        case '+':
            return unit1 + unit2;
        case '-':
            return unit1 - unit2;
        case '*':
            return unit1 * unit2;
        case '/':
            if (unit2 == 0.0) { // this really shouldn't occur, but catching in case it occurs
                throw new UnsupportedOperationException("Cannot divide by zero");
            }
            return unit1 / unit2;
        }
        return 0.0;
    }
}
