//import java.util.ArrayDeque;

/**
 * Converts infix expressions to postfix notation and then evaluates expressions
 * Uses Shunting Yard algorithm to convert infix to postfix then uses CalculatePostfix's postfixToResult method for evaluation
 * Supports basic arithmetic operations and parentheses
 */
public class CalculateInfix {
    /**
     * Converts infix expression to postfix notation and evaluates expression
     * Processes tokens using Shunting Yard algorithm
     * 
     * @param tokens Queue of tokens in infix notation
     * @return Result of evaluated expression
     * @throws IllegalArgumentException If there are malformed expressions (ex. mismatched parentheses)
     */
    public static Double infixToPostfix(Queue<Object> tokens) {
        Stack<Character> operatorStack = new Stack<>();
        Queue<Object> outputQueue = new Queue<>();

        while (!tokens.isEmpty()) {
            Object token = tokens.remove();
            
            if (token instanceof Double) {
                Double num = (Double) token;
                outputQueue.add(num);
            } else if (token instanceof Character) {
                Character c = (Character) token;

                if (c == '(') {
                    operatorStack.push(c);
                } else if (c == ')') {
                    while(!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        outputQueue.add(operatorStack.pop());
                    }
                    if (operatorStack.isEmpty()) {
                        throw new IllegalArgumentException("Couldn't find left parenthesis. There are mismatched parentheses.");
                    }
                    operatorStack.pop(); //remove left parenthesis
                } else {
                    Character queueOperator = (Character) token;

                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && (precedence(operatorStack.peek()) > precedence(queueOperator) || (precedence(operatorStack.peek()) == precedence(queueOperator) && precedence(queueOperator) != 3))) {
                        Character topStackOperator = operatorStack.pop();
                        outputQueue.add(topStackOperator);
                    }
                    operatorStack.push(queueOperator);

                }
            }
        } 

        // processes operators remaining in stack
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(' || operatorStack.peek() == ')') {
                throw new IllegalArgumentException("No more tokens to read and token at the top of the stack is a parenthesis. There are mismatched parentheses.");
            } else {
                outputQueue.add(operatorStack.pop());
            }
        }

        
        return CalculatePostfix.postfixToResult(outputQueue);
    }

    /**
     * Determines precedence of operators
     * Higher number means higher precedence
     * 
     * @param token Operator character
     * @return Precedence level (3 = highest, 1 = lowest, 0 = parentheses or unknown operator)
     */
    public static int precedence(Character token) {
        //3 means highest precedence and 1 means lowest precedence
        if (token == '^') { //^ is right-associative and the other four operators are left-associative
            return 3;
        } else if (token == '*' || token == '/') {
            return 2;
        } else if (token == '+' || token == '-') {
            return 1;
        } return 0; //if operator isn't one of the above options (a parenthesis or unknown), return 0
    }

    /**
     * Main method for testing CalculateInfix class
     * Tests inflix to postfix expression conversion with different expressions
     * 
     * @param args Command line arguments
     */
     public static void main(String[] args) {
        //testing infix to postfix conversion by calling all three functions
        String expression = "(5+2)/3";
        Queue<Object> tokens = Tokenizer.readTokens(expression);
        Double result = CalculateInfix.infixToPostfix(tokens);
        System.out.println(expression + " = " + result);

        //testing expression with two exponentiations
        String expressionTwo = "(5+2)^3^5";
        Queue<Object> tokensTwo = Tokenizer.readTokens(expressionTwo);
        Double resultTwo = CalculateInfix.infixToPostfix(tokensTwo);
        System.out.println(expressionTwo + " = " + resultTwo);
        
        // testing edge case: missing left parenthesis
        String exceptionOne = "5+2)^3^5";
        Queue<Object> tokensThree = Tokenizer.readTokens(exceptionOne);
        Double resultThree = CalculateInfix.infixToPostfix(tokensThree);
        System.out.println(exceptionOne + " = " + resultThree);

        // testing edge case: missing right parenthesis
        // String exceptionTwo = "(5+2^3^5";
        // Queue<Object> tokensFour = Tokenizer.readTokens(exceptionTwo);
        // Double resultFour = CalculateInfix.infixToPostfix(tokensFour);
        // System.out.println(exceptionTwo + " = " + resultFour);

        // testing edge case: missing left parenthesis
        // String exceptionThree = "((())))";
        // Queue<Object> tokensFive = Tokenizer.readTokens(exceptionThree);
        // Double resultFive = CalculateInfix.infixToPostfix(tokensFive);
        // System.out.println(exceptionThree + " = " + resultFive);
    }
}
