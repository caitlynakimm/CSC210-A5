/**
 * Test class for CalculatePostfix class to see if it is functioning properly
 * Consists of valid expressions and exception cases
 */
public class CalculatePostfixTest {
    /**
     * Main method that runs tests for CalculatePostfix
     * Tests valid expressions and exception cases
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //valid expressions
        String[] testCases = {"3 2 + 5*", "3 2 + 5 * 3 2 ^ ^", "3.5 5.678 * 20 -", "5"};
        
        //computes each expression with for loop
        for (String test : testCases) {
            Queue<Object> tokens = Tokenizer.readTokens(test);
            Double result = CalculatePostfix.postfixToResult(tokens);
            System.out.println(test + " = " + result);
        }

        //exception cases

        // throws exception since + operator is missing first operand
        String exception = "32 + 5*";
        Queue<Object> tokensTwo = Tokenizer.readTokens(exception);
        Double resultTwo = CalculatePostfix.postfixToResult(tokensTwo);
        System.out.println(exception + " = " + resultTwo);

        // throws exception since + operator is missing second operand
        // String exceptionTwo = "+";
        // Queue<Object> tokensThree = Tokenizer.readTokens(exceptionTwo);
        // Double resultThree = CalculatePostfix.postfixToResult(tokensThree);
        // System.out.println(exceptionTwo + " = " + resultThree);

        // no numbers in expression
        // String exceptionThree = " ";
        // Queue<Object> tokensFive = Tokenizer.readTokens(exceptionThree);
        // Double resultFive = CalculatePostfix.postfixToResult(tokensFive);
        // System.out.println(exceptionThree + " = " + resultFive);

        // no numbers in expression
        // String exceptionFour = "";
        // Queue<Object> tokensSix = Tokenizer.readTokens(exceptionFour);
        // Double resultSix = CalculatePostfix.postfixToResult(tokensSix);
        // System.out.println(exceptionFour + " = " + resultSix);

        // can't divide by 0
        // String exceptionFive = "3 0 /";
        // Queue<Object> tokensSeven = Tokenizer.readTokens(exceptionFive);
        // Double resultSeven = CalculatePostfix.postfixToResult(tokensSeven);
        // System.out.println(exceptionFive + " = " + resultSeven);

        //unknown operator
        // String exceptionSix = "5 0 !";
        // Queue<Object> tokensEight = Tokenizer.readTokens(exceptionSix);
        // Double resultEight = CalculatePostfix.postfixToResult(tokensEight);
        // System.out.println(exceptionSix + " = " + resultEight);

        //too many numbers left in stack
        // String exceptionSeven = "2 3 + 25 8";
        // Queue<Object> tokensNine = Tokenizer.readTokens(exceptionSeven);
        // Double resultNine = CalculatePostfix.postfixToResult(tokensNine);
        // System.out.println(exceptionSeven + " = " + resultNine);
    }
}
