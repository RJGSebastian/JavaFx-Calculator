package sample;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {
    int C = 0;

    ArrayList<String> splits = new ArrayList<>();
    Validator validator;
    int c = 0;

    public String calculate(String string) {
        validator = new Validator();
        if (validator.isValid(string)) {
            string = splitAtBrackets(string);
            System.out.println();

            string = splitAtCaret(string);
            System.out.println();

            string = splitAtMultDiv(string);
            System.out.println();

            string = splitAtAddSub(string);
            System.out.println();

            Calc calc = new Calc();
            C = 0;
            return Double.toString(calc.calcSplit(string, splits));
        }

        C = 0;
        return "Invalid Input";
    }

    private String splitAtBrackets(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        System.out.println("Splitting at Brackets");

        while (validator.containsBrackets(string)){
            string = splitBrackets(string);
        }

        for (int i = 0; i < splits.size(); i++){
            if (validator.containsBrackets(splits.get(i))) {
                System.out.println();
                splits.set(i, splitBrackets(splits.get(i)));
                i--;
            } else {
                System.out.println(splits.get(i) + " Does not contain any brackets!");
            }
        }

        System.out.println(string);
        System.out.println(splits);

        return string;
    }

    private String splitAtCaret(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        System.out.println("Splitting at Carets");

        while (validator.containsCaret(string) && !validator.isFinished(string)){
            string = splitCaret(string);
        }

        for (int i = 0; i < splits.size(); i++){
            if (validator.containsCaret(splits.get(i)) && !validator.isFinished(splits.get(i))){
                System.out.println();
                splits.set(i, splitCaret(splits.get(i)));
                i--;
            } else if (!validator.isFinished(splits.get(i))){
                System.out.println(splits.get(i) + " Does not contain any carets");
            }
        }

        System.out.println(string);
        System.out.println(splits);

        return string;
    }

    private String splitAtMultDiv(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        System.out.println("Splitting at Mult. or Div.");

        while (validator.containsMultDiv(string) && !validator.isFinished(string)){
            string = splitMultDiv(string);
        }

        for (int i = 0; i < splits.size(); i++){
            if (validator.containsMultDiv(splits.get(i)) && !validator.isFinished(splits.get(i))){
                System.out.println();
                splits.set(i, splitMultDiv(splits.get(i)));
                i--;
            } else if (!validator.isFinished(splits.get(i))){
                System.out.println(splits.get(i) + " Does not contain any Mult. or Div.");
            }
        }

        System.out.println(string);
        System.out.println(splits);

        return string;
    }

    private String splitAtAddSub(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        System.out.println("Splitting at Add. or Sub.");

        while (validator.containsAddSub(string) && !validator.isFinished(string)){
            string = splitAddSub(string);
        }

        for (int i = 0; i < splits.size(); i++){
            if (validator.containsAddSub(splits.get(i)) && !validator.isFinished(splits.get(i))){
                System.out.println();
                splits.set(i, splitAddSub(splits.get(i)));
                i--;
            } else if (!validator.isFinished(splits.get(i))){
                System.out.println(splits.get(i) + " Does not contain any Add. or Sub.");
            }
        }

        System.out.println(string);
        System.out.println(splits);

        return string;
    }

    private String splitBrackets(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        int n = 0;
        int m = 0;

        for (int i = 0; i < string.length(); i++){
            if(string.charAt(i) == '('){
                n++;
            } else if (string.charAt(i) == ')'){
                m++;
                if (n > 0 && n == m){

                    splits.add(string.substring(string.indexOf('(') + 1, i));
                    System.out.println(splits.get(c));
                    string = string.substring(0, string.indexOf('(')) + "#" + c +"#" + string.substring(i + 1);
                    System.out.println(string);

                    c++;
                    i = 0;
                }
            }
        }

        return string;
    }

    private String splitCaret(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        final String regex = "([\\d\\+\\-\\*\\/]+|#\\d+#)\\^([\\d\\+\\-\\*\\/]+|#\\d+#)";

        String subString = string;

        while (validator.containsMultCarets(subString)){
            subString = subString.substring(subString.indexOf('^') + 1);
        }

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(subString);

        while (matcher.find()) {

            splits.add(matcher.group(0));
            System.out.println(splits.get(c));
            string = string.replace(matcher.group(0), "#" + c + "#");
            System.out.println(string);

            c++;
        }

        return string;
    }

    private String splitMultDiv(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        final String regex = "([\\d\\+\\-]+|#\\d+#)[\\*\\/]([\\d\\+\\-]+|#\\d+#)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {

            splits.add(matcher.group(0));
            System.out.println(splits.get(c));
            string = string.replace(matcher.group(0), "#" + c + "#");
            System.out.println(string);

            c++;
        }

        return string;
    }

    private String splitAddSub(String string){
        C++;

        if (C > 100){
            System.exit(1);
        }

        final String regex = "(\\d+|#\\d+#)[\\+\\-](\\d+|#\\d+#)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {

            splits.add(matcher.group(0));
            System.out.println(splits.get(c));
            string = string.replace(matcher.group(0), "#" + c + "#");
            System.out.println(string);

            c++;
        }

        return string;
    }
}
