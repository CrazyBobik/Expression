package Util;

/**
 * Created on 30.03.2015.
 *
 * @author Crazy Bobik
 *         (.)(.)
 *         =)
 */

public class Expression {
    private String E;

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public float evalE() throws IllegalArgumentException{
        return eval(E);
    }

    private float eval(String str){
        if (str.endsWith(")") && str.startsWith("(")){
            return eval(str.substring(1, str.length() - 1));
        } else if (str.endsWith(")") && !str.startsWith("(")){
            int pos = str.length() - 1;
            int count = 0;
            while (pos > 0){
                if (str.charAt(pos) == ')'){
                    count++;
                } else if (str.charAt(pos) == '('){
                    count--;
                }
                pos--;

                if (count == 0){
                    char operator = str.charAt(pos);
                    switch (operator){
                        case '+': return eval(str.substring(0, pos)) + eval(str.substring(pos + 2, str.length() - 1));
                        case '-': return eval(str.substring(0, pos)) - eval(str.substring(pos + 2, str.length() - 1));
                    }
                }
            }

            throw new IllegalArgumentException("String not correct");
        } else {
            int pos = str.length() - 1;
            while (pos > 0){
                if (isDigit(str.charAt(pos))){
                    pos--;
                } else {
                    float last = Float.valueOf(str.substring(pos + 1));
                    char operator = str.charAt(pos);
                    switch (operator){
                        case '+':
                            return eval(str.substring(0, pos)) + last;
                        case '-': return eval(str.substring(0, pos)) - last;
                    }
                }
            }
            return Float.valueOf(str);
        }
    }

    private boolean isDigit(char c){
        char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (char ch : arr){
            if (ch == c)
                return true;
        }

        return false;
    }

}
