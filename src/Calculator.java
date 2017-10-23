import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    public static void main(String args[]) {
    	
    	String infix = String.join(" ", args); // Command line argument.

        String postfix = convertToPostfix(infix);

        System.out.println("The postfix expression is: "+ postfix );
        
        System.out.println("The result of calculation is: " + postfixEvaluate(postfix));

    }

	public static String convertToPostfix(String infixExp) throws LookAtMrAlgebraOverHereException, IllegalOperationException, DumbassException{
		String postFix = " ";
		Stack<Character> stack = new Stack<Character>();
		char character;
	     
		if(infixExp.isEmpty())
			throw new DumbassException();
		
		if (infixExp.charAt(0) == '+' || infixExp.charAt(0) == '-' || infixExp.charAt(0) == 'X' || infixExp.charAt(0) == '/' ||infixExp.charAt(0) == '%')
			throw new LookAtMrAlgebraOverHereException();
	
		for(int i = 0; i < infixExp.length(); i++) {
			character = infixExp.charAt(i);
	         
		if(Character.isLetter(character) && character != 'X')
			throw new LookAtMrAlgebraOverHereException();

		if(character == 'X' || character == '-' || character == '/' || character == '+' || character == '%') {
			while(!stack.empty() && precedence(stack.peek(), character))
				postFix += stack.pop();
	             
			stack.push(character);
		} else if ((character >= '!' && character <= '$') || ((character == '&') || character == '`' || character == ','))
	        	 throw new IllegalOperationException();
		  else if(character == '(') 
	             stack.push(character);
		  else if(character == ')') {
			  while(!stack.peek().equals('(') && !stack.isEmpty())
				  postFix += stack.pop();
	
			  if(!stack.isEmpty() && stack.peek().equals('('))
				  stack.pop(); 
		  } else
			  postFix += character;
		}
	     while(!stack.empty()) 
	     {
	    	 if(stack.peek().equals('('))
	         {
	             postFix = "There is no matching right parenthesis.";
	             return postFix;
	         }
	         postFix += " " + stack.pop();
	     }
	         return postFix;
	}
	
	public static boolean precedence(char first, char second) {
	      int v1 = 0, v2 = 0;

	      if(first == '-' || first == '+')
	         v1 = 1;
	      	else if(first == 'X' || first == '/' || first == '%')
	      		v1 = 2;    

	      if(second == '-' || second == '+')
	         v2 = 1;	
	      	else if(second == 'X' || second == '/' || second == '%')
	      		v2 = 2;    

	     if(v1 < v2)
	        return false;

	     return true;
	}
	
	 public static double postfixEvaluate(String exp) {

		 Stack<Double> s = new Stack<Double> ();
			Scanner tokens = new Scanner(exp);
			
			while (tokens.hasNext()) {
				if (tokens.hasNextInt() || tokens.hasNextDouble()) {
					s.push(tokens.nextDouble());
				} else {
					double num2 = s.pop();
					double num1 = s.pop();
					String op = tokens.next();
					
					if (op.equals("+")) {
						s.push(num1 + num2);
					} else if (op.equals("-")) {
						s.push(num1 - num2);
					} else if (op.equals("X")) {
						s.push(num1 * num2);
					} else if (op.equals("/")){
						if(num2 == 0 || num2 == 0.0)
							throw new ArithmeticException("Can't divide by zero, DUMBASS!");
						else
							s.push(num1 / num2);
					} else if(op.equals("%"))
						s.push(num1 % num2);
				}
			}
			return s.pop();
	    }
}