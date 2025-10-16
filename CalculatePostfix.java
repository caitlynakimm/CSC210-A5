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
                Object numBefore = tokens.remove();
                Object numAfter = tokens.remove()+1;
            }
        }
        
    }
}