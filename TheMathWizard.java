import java.util.*;

public class TheMathWizard {
    
    public static int prec(char c) {
        if (c == '^')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    public static String infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                || (c >= '0' && c <= '9'))
                result += c;
            else if (c == '(')
                st.push('(');
            else if (c == ')') {
                while (st.peek() != '(') {
                    result += st.peek();
                    st.pop();
                }
                st.pop();
            } else {
                while (!st.isEmpty()
                       && prec(s.charAt(i)) <= prec(st.peek())) {
                    result += st.peek();
                    st.pop();
                }
                st.push(c);
            }
        }
        while (!st.isEmpty()) {
            result += st.peek();
            st.pop();
        }

        return result;
    }

    public static double postfix(String s) {
        Stack<Double> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
               st.push((double) (s.charAt(i) - '0'));
            } else {
                double op1 = st.peek();
                st.pop();
                double op2 = st.peek();
                st.pop();
                switch (s.charAt(i)) {
                    case '+':
                        st.push(op2 + op1);
                        break;
                    case '-':
                        st.push(op2 - op1);
                        break;
                    case '*':
                        st.push(op2 * op1);
                        break;
                    case '/':
                        st.push(op2 / op1);
                        break;
                    default:
                        break;
                }
            }
        }
        return st.peek();
    }

    public static String getSymbol(String s) {
        if(s.length() == 1) return s;
        if(s.equals("zero")) return "0";
        if(s.equals("one")) return "1";
        if(s.equals("two")) return "2";
        if(s.equals("three")) return "3";
        if(s.equals("four")) return "4";
        if(s.equals("five")) return "5";
        if(s.equals("six")) return "6";
        if(s.equals("seven")) return "7";
        if(s.equals("eight")) return "8";
        if(s.equals("nine")) return "9";
        if(s.equals("plus")) return "+";
        if(s.equals("substract")) return "-";
        if(s.equals("multiple")) return "*";
        if(s.equals("division")) return "/";
        if(s.equals("equals")) return "=";
        return "";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int a = 1;
        while(t-- > 0) {
            String str;
            String equation = "";
            while(true) {
                str = sc.next();
                if(str.equals("equals") || str.equals("=")) break;
                String temp = getSymbol(str);
                equation += temp;
            }
            String word = sc.nextLine();
            double ans = Double.parseDouble(word);
            equation = infixToPostfix(equation);
            double res = postfix(equation);
            System.out.print("Case #" + a + ": ");
            if (ans == res)
                System.out.println("true");
            else
                System.out.println("false");
    
            a++;
        }
    }
}
