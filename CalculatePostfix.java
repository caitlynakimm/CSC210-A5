/**
 * Cakculates expressions in postfix notation
 * Supports basic arithmetic operations: +, -, *, /, ^
 */
public class CalculatePostfix {
    /**
     * Evaluates postfix expression and returns result
     * Processes tokens from queue using stack-based algorithm
     * @param tokens Queue of tokens in postfix notation
     * @return Result of evaluated postfix expression
     * @throws IllegalArgumentException If expression is malformed (has unknown operators, dividing by zero, no numbers, not enought numbers or operators, etc.)
     */
    public static Double postfixToResult(Queue<Object> tokens) {
        Stack<Double> stack = new Stack<>();

        while (!tokens.isEmpty()) {
            
            Object token = tokens.remove();

            if (token instanceof Double) { //token is a number
                Double num = (Double) token;
                stack.push(num);
            } else if (token instanceof Character) { //token is an operator or non-number
                Character operator = (Character) token;
                
                if (stack.isEmpty()) { //if there's no second operand
                    throw new IllegalArgumentException("Not enough numbers in the stack to process operator: " + operator + ". Entered expression was malformed.");
                }

                Double secondOperand = stack.pop();
                
                if (stack.isEmpty()) { //if there's no first operand
                    throw new IllegalArgumentException("Not enough numbers in the stack to process operator: " + operator + ". Entered expression was malformed.");
                }
                
                Double firstOperand = stack.pop();

                // performs operation based on operator
                if (operator == '+') {
                    stack.push(firstOperand + secondOperand);
                } else if (operator == '-') {
                    stack.push(firstOperand - secondOperand);
                } else if (operator == '*') {
                    stack.push(firstOperand * secondOperand);
                } else if (operator == '/') {
                    if (secondOperand == 0.0) {
                        throw new IllegalArgumentException("Division by 0 is not allowed.");
                    }
                    stack.push(firstOperand / secondOperand);
                } else if (operator == '^') {
                    stack.push(Math.pow(firstOperand, secondOperand));
                } else {
                    throw new IllegalArgumentException("Unknown operator: " + operator);
                }

            }
        }

        // checking if stack is empty before popping result
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("No numbers in expression. Entered expression was malformed.");
        }

        Double result = stack.pop();

        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Too many items left in the stack. Entered expression was malformed.");
        }

        return result;
    }
}