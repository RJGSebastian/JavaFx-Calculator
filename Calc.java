package sample;

import java.util.ArrayList;

public class Calc {

    public double calcSplit(String string, ArrayList<String> splits){
        double aNum = 0;
        double bNum = 0;
        double c = 0;
        boolean containsSymbol = false;

        for (String symbol : new String[]{"+", "-", "*", "/", "^"}) {
            if (string.contains(symbol)) {
                containsSymbol = true;
                String a = string.substring(0, string.indexOf(symbol));
                String b = string.substring(string.indexOf(symbol) + 1);
                if (a.contains("#")){
                    System.out.println(splits.get(Integer.parseInt(a.substring(1, a.length()-1))));
                    aNum = calcSplit(splits.get(Integer.parseInt(a.substring(1, a.length()-1))), splits);
                } else {
                    aNum = Double.parseDouble(a);
                }
                if (b.contains("#")){
                    System.out.println(splits.get(Integer.parseInt(b.substring(1, b.length()-1))));
                    bNum = calcSplit(splits.get(Integer.parseInt(b.substring(1, b.length()-1))), splits);
                } else {
                    bNum = Double.parseDouble(b);
                }

                switch (symbol){
                    case "+":
                        c = aNum + bNum;
                        break;
                    case "-":
                        c = aNum - bNum;
                        break;
                    case "*":
                        c = aNum * bNum;
                        break;
                    case "/":
                        c = aNum / bNum;
                        break;
                    case "^":
                        c = Math.pow(aNum, bNum);
                        break;
                    default:
                        break;
                }

                System.out.println(a + " " + symbol + " " + b + "  | " + aNum + " " + symbol + " " + bNum + " = " + c);
            }

        }

        if (!containsSymbol){
            c = Double.parseDouble(string);
            System.out.println(c);
        }

        return c;
    }
}
