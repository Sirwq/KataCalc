import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        calc(readLine());
    }

    public static String calc(String userInput) throws IOException {

        int op = 0;
        Operator[] operators = Operator.values();
        for (Operator ui: operators) {
            if ((userInput.indexOf(ui.getValue()) != -1)){
                op = ui.getValue();
            }
        }

        if (op == 0){
            throw new IOException("Оператор не найден");
        }

        String [] elements = userInput.split(" ");

        if(elements.length > 3){
            throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if(elements.length == 1) {
            throw new IOException("Строка не является математической операцией");
        }

        boolean roman = isRoman(elements[0]);
        boolean decimal = isDecimal(elements[0]);
        boolean roman1 = isRoman(elements[2]);
        boolean decimal1 = isDecimal(elements[2]);

        System.out.println(decimal);
        System.out.println(decimal1);

        if (roman & decimal || roman1 & decimal1) {
            throw new IOException("Неверный ввод");
        }

        if (roman & !roman1 || decimal & !decimal1) {
            throw new IOException("т.к. используются одновременно разные системы счисления");
        }

        int num1 = 0;
        int num2 = 0;

        if (decimal) {
            System.out.println("YAY");
            num1 = toInteger(elements[0]);
            num2 = toInteger(elements[2]);
        }


        num1 = toDecimal(elements[0]);
        System.out.println(num1);
        num2 = toDecimal(elements[2]);
        System.out.println(num2);




        int result = 0;
        switch (op) {
            case 43 -> result = num1 + num2;
            case 42 -> result = num1 * num2;
            case 45 -> result = num1 - num2;
            case 47 -> result = num1 / num2;
            default -> throw new IllegalArgumentException("Unexpected value" + op);
        }

        String answer = String.valueOf(result);

        return answer;
    }

    static String readLine() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    static int toInteger(String num){
        return (Integer.parseInt(num));
    }

    static boolean isRoman(String num){
        Roman[] roman = Roman.values();
        for (Roman elem: roman) {
            if (num.indexOf(elem.getValue()) != -1){
                return true;
            }
        } return false;
    }

    static boolean isDecimal(String num) {
        int [] decimal = {0,1,2,3,4,5,6,7,8,9};

        for (int elem: decimal) {
            if(num.indexOf(elem) != -1) {
                System.out.println("NICE");
                return true;
            }
        } return false;
    }

    static int toDecimal(String num) {
        Roman[] roman = Roman.values();
        System.out.println("Hello");

        int result = 0;
        // I , V , X --> 73, 86, 88
        if (num.codePointAt(0) > num.codePointAt(num.length() - 1) || num.codePointAt(0) == num.codePointAt(num.length() - 1)) {
            for(int i = 0; i <= (num.length() - 1); i++) {
                for (Roman elem: roman){
                    if (num.charAt(i) == elem.ordinal()){
                        System.out.println(num.charAt(i));
                        result += elem.getValue();
                    }
                }
            }
        } else {
            char c = num.charAt(num.length() - 1);
            for (Roman elem: roman){
                if(c == elem.ordinal()){
                    result = elem.getValue();
                }
            }
            for (int i = 0; i < num.length() - 2; i++){
                for (Roman elem: roman){
                    if (num.charAt(i) == elem.ordinal()){
                        result -= elem.getValue();
                    }
                }
            }
        }
        return result;
    }

}



