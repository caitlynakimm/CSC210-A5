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
                
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough numbers in the stack to process operator. Entered expression was malformed.");
                }

                Double secondOperand = stack.pop();
                Double firstOperand = stack.pop();
                Double result;

                if (operator == "+") {
                    result = firstOperand + secondOperand;
                } else if (operator == "-") {
                    result = firstOperand - secondOperand;
                } else if (operator == "*") {
                    result = firstOperand * secondOperand;
                } else if (operator == "/") {
                    result = firstOperand / secondOperand;
                } else if (operator == "^") {
                    result = (int)pow(firstOperand, secondOperand);
                }
                
                stack.push(result);
                System.out.println(stack);
            }
        }
        
    }
}