package sample;

public class Validator {
    public boolean isValid(String input){
        if (subStrCounter(input, "(") != subStrCounter(input, ")")){
            return false;
        }

        int brackets = 0;
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '('){
                brackets++;
            } else if (input.charAt(i) == ')'){
                brackets--;
            }
            if (brackets < 0){
                return false;
            }
        }

        char[] symbols = new char[]{'+', '-', '*', '/', '^', '.'};
        char[] nums = new char[]{'1', '2', '3', '4', '5', '6','7', '8', '9', '0'};
        char[] pChars = new char[]{'1', '2', '3', '4', '5', '6','7', '8', '9', '0', '+', '-', '*', '/', '^', '.', '(', ')'};

        for (int i = 0; i < input.length(); i++){
            for (char symbol1 : symbols){
                if (input.charAt(i) == symbol1){
                    for (char symbol2 : symbols){
                        if (input.charAt(i + 1) == symbol2){
                            return false;
                        }
                    }

                    if (i == 0){
                        return false;
                    }

                    if (symbol1 == '.'){
                        if (i + 1 < input.length()) {
                            if (input.charAt(i + 1) == '(' || input.charAt(i + 1) == ')') {
                                return false;
                            }
                        }
                        if (input.charAt(i - 1) == ')' || input.charAt(i - 1) == '(') {
                            return false;
                        }
                    } else {
                        if (i + 1 < input.length()) {
                            if (input.charAt(i + 1) == ')') {
                                return false;
                            }
                        }
                        if (input.charAt(i - 1) == '(') {
                            return false;
                        }
                    }
                }
            }

            for (char num : nums){
                if (input.charAt(i) == num){
                    if (i + 1 < input.length()) {
                        if (input.charAt(i + 1) == '(') {
                            return false;
                        }
                    }
                    if (i > 0){
                        if (input.charAt(i - 1) == ')') {
                            return false;
                        }
                    }
                }
            }

            boolean isPChar = false;
            for (char pChar : pChars){
                if (input.charAt(i) == pChar) {
                    isPChar = true;
                    break;
                }
            }
            if (!isPChar){
                return false;
            }
        }

        return true;
    }

    public boolean isFinished(String input){
        if (containsBrackets(input)){
            return false;
        } else {

            int c = 0;
            for (String symbol : new String[]{"+", "-", "*", "/", "^"}) {
                c += subStrCounter(input, symbol);
            }

            System.out.println(input + " | " + c);
            return !(c > 1);
        }
    }

    public boolean containsBrackets(String input){
        return input.contains("(");
    }

    public boolean containsCaret(String input){
        return input.contains("^");
    }

    public boolean containsMultCarets(String input){
        return (subStrCounter(input, "^") > 1);
    }

    public boolean containsMultDiv(String input){
        return (input.contains("*") || input.contains("/"));
    }

    public boolean containsAddSub(String input){
        return (input.contains("+") || input.contains("-"));
    }

    private int subStrCounter(String str, String findStr){
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }

        return count;
    }
}
