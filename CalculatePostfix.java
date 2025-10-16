public class CalculatePostfix {
    public static Double postfixToResult(Queue<Object> tokens) {
        Stack<Double> stack = new Stack<>();

        while (!tokens.isEmpty()) {
            
            Object token = tokens.remove();

            if (token instanceof Double) {
                Double num = (Double) token;
                stack.push(num);
            } else if (token instanceof Character) {
                Character operator = (Character) token;
                
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Not enough numbers in the stack to process operator. Entered expression was malformed.");
                }

                Double secondOperand = stack.pop();
                
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Not enough numbers in the stack to process operator. Entered expression was malformed.");
                }
                
                Double firstOperand = stack.pop();

                if (operator == '+') {
                    stack.push(firstOperand + secondOperand);
                } else if (operator == '-') {
                    stack.push(firstOperand - secondOperand);
                } else if (operator == '*') {
                    stack.push(firstOperand * secondOperand);
                } else if (operator == '/') {
                    stack.push(firstOperand / secondOperand);
                } else if (operator == '^') {
                    stack.push(Math.pow(firstOperand, secondOperand));
                } else {
                    throw new IllegalArgumentException("Unknown operator: " + operator);
                }

            }
        }
        Double result = stack.pop();

        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Too many items left in the stack. Entered expression was malformed.");
        }

        return result;
    }
}