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
                Character queueOperator = (Character) token;
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && precedence(operatorStack.peek()) >= precedence(queueOperator)) {
                    Character topStackOperator = operatorStack.pop();
                    outputQueue.add(topStackOperator);
                }
                operatorStack.push(queueOperator);

                if (c == '(') {
                    operatorStack.push(c);
                } else if (c == ')') {
                    
                }
            }
        } 
        //use pemdas, parenthesis don't affect order of tokens
    }

    public static int precedence(Character token) {
        //1 means highest precedence and 3 means lowest precedence
        if (token == '^') {
            return 1;
        } else if (token == '*' | token == '/') {
            return 2;
        } else if (token == '+' | token == '-') {
            return 3;
        } return 0; //if operator isn't one of the above options return 0
    }
}
