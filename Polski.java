import java.util.Stack;
public class Polski {
    private String operand;
    private Stack<Character> stack;
    public Polski() {
        stack = new Stack<>();  
    }

    public double decide (String expression){
        String rpn = expressionToRPN(expression);
        return rpnToAnswer(rpn);
    }

     private int getPriority(char token){

        if (token == '*' || token == '/') return 3;

        else if (token == '+' || token == '-') return 2;
        else if (token == '(' ) return 1;
        else if (token == ')' ) return -1;
        else return 0;
    }

    private String expressionToRPN(String expression){

        String current = "";
        
        int priority;

        for (int i = 0;i<expression.length();i++) {
            priority = getPriority(expression.charAt(i));

            if (priority == 0) current += expression.charAt(i);

            if (priority == 1) stack.push(expression.charAt(i));

            if (priority > 1) {
                current += ' ';
                                                                                             
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority)
                        current += stack.pop();
                    else break;
                }
                stack.push(expression.charAt(i));
            }

            if (priority == -1) {
                current += ' ';
                while (getPriority(stack.peek()) != 1) current += stack.pop();

                stack.pop();
            }
        }
        while( ! stack.empty()) current += stack.pop();
        return current;
    }
    private double rpnToAnswer(String rpn){
        Stack<Double> stack = new Stack<Double>();
        String operand = new String();

        for (int i = 0;i<rpn.length(); i++) {

            if (rpn.charAt(i) == ' ') continue;

            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) !=' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);

                    if (i == rpn.length()) break;

                }
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }

            if (getPriority(rpn.charAt(i))>1) {
                double a = stack.pop(), b =  stack.pop();

                if (rpn.charAt(i) == '+') stack.push(a+b);
                if (rpn.charAt(i) == '-') stack.push(a-b);
                if (rpn.charAt(i) == '*') stack.push(a*b);
                if (rpn.charAt(i) == '/') stack.push(a/b);
            }

        }

        return stack.pop();
    }

}