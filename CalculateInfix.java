import java.util.ArrayDeque; //is this possible?

public class CalculateInfix {
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

                        if (operatorStack.isEmpty()) {
                            throw new IllegalArgumentException("Couldn't find left parenthesis. There are mismatched parentheses.");
                        }
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

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(' || operatorStack.peek() == ')') {
                throw new IllegalArgumentException("No more tokens to read and token at the top of the stack is a parenthesis. There are mismatched parentheses.");
            } else {
                outputQueue.add(operatorStack.pop());
            }
        }

        
        return CalculatePostfix.postfixToResult(outputQueue);
    }

    public static int precedence(Character token) {
        //1 means highest precedence and 3 means lowest precedence
        if (token == '^') { //^ is right-associative and the other four operators are left-associative
            return 3;
        } else if (token == '*' || token == '/') {
            return 2;
        } else if (token == '+' || token == '-') {
            return 1;
        } return 0; //if operator isn't one of the above options (a parenthesis or unknown), return 0
    }

     public static void main(String[] args) {
        //testing infix to postfix conversion by calling all three functions
        String expression = "(5+2)/3";
        Queue<Object> tokens = Tokenizer.readTokens(expression);
        Double result = CalculateInfix.infixToPostfix(tokens);
        System.out.println(expression + " = " + result);
    }
}
