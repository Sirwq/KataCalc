import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(calc(readLine()));
    }

    public static String calc(String userInput) throws IOException {

        int op = 0;
        Operator[] operators = Operator.values();
        for (Operator ui: operators) {
            if ((userInput.indexOf(ui.getValue()) != -1)){
                op = ui.getValue();
            }
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

        if (roman & decimal || roman1 & decimal1) {
            throw new IOException("Неверный ввод");
        }

        if (roman & !roman1 || decimal & !decimal1) {
            throw new IOException("т.к. используются одновременно разные системы счисления");
        }

        if (op == 0){
            throw new IOException("Оператор не найден");
        }

        int num1 = 0;
        int num2 = 0;

        if (decimal) {
            num1 = strToInt(elements[0]);
            num2 = strToInt(elements[2]);
        }

        if (roman) {
            num1 = toDecimal(elements[0]);
            num2 = toDecimal(elements[2]);
        }

        if ( (num1 > 10 || num2 > 10 || num1 < 1 || num2 < 1)) {
            throw new IOException("На вход подана неверная велечина < 0 || > 10");
        }

        int result;
        switch (op) {
            case 43 -> result = num1 + num2;
            case 42 -> result = num1 * num2;
            case 45 -> result = num1 - num2;
            // Dont check for division by zero cuz working only with (0-10)
            case 47 -> result = num1 / num2;
            default -> throw new IllegalArgumentException("Unexpected value" + op);
        }

        String answer = "";

        if(decimal) {
            answer = String.valueOf(result);
        }

        if(roman) {
            if (result < 1) {
                throw new ArithmeticException("т.к. в римской системе нет отрицательных чисел");
            } else {
                answer = toRoman(result);
            }
        }
        return answer;
    }

    static String readLine() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    static int strToInt(String num){
        int result;

        try {
            result = Integer.parseInt(num);
        } catch (Exception e) {
            throw new RuntimeException("Неверный ввод");
        }
        return result;
    }

    static boolean isRoman(String num) {
        boolean result = false;
        Roman[] roman = Roman.values();

        for (int i = 0; i < num.length(); i++) {
            for (Roman c :
                    roman) {
                if (num.charAt(i) == c.getChar()) {
                    result = true;
                    break;
                }
            }
            if (result) break;
        }
        return result;
    }

    static boolean isDecimal(String num) {
        boolean result = false;
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < num.length(); i++) {
            for (char c :
                    numbers) {
                if (num.charAt(i) == c) {
                    result = true;
                    break;
                }
            }
            if (result) break;
        }
        return result;
    }

    static int toDecimal(String num) {
        HashMap<Character,Integer> roman_table = new HashMap<>();

        roman_table.put('I',1);
        roman_table.put('V',5);
        roman_table.put('X',10);

        int result = 0;
        int length = num.length();

        if (length == 1 && roman_table.containsKey(num.charAt(0))) {
            result = roman_table.get(num.charAt(0));
        }

        // I - 73, V - 86, X - 88
        else if (num.codePointAt(0) >= num.codePointAt(length - 1)) {
            for (int i = 0; i < length; i++) {
                if(roman_table.containsKey(num.charAt(i))) {
                    result += roman_table.get(num.charAt(i));
                }
            }
        }

        else {

            if(roman_table.containsKey(num.charAt(length - 1))){
                result = roman_table.get(num.charAt(length - 1));
            }

            for(int i = 0; i < length - 1; i++){
                if(roman_table.containsKey(num.charAt(i))) {
                    result -= roman_table.get(num.charAt(i));
                }
            }
        }
        return result;
    }

    static String toRoman(int num) {
        return "I".repeat(num)
                .replace("IIIII","V")
                .replace("IIII","IV")
                .replace("VV","X")
                .replace("VIV","IX")
                .replace("XXXXX","L")
                .replace("XXXX","XL")
                .replace("LL","C")
                .replace("LXL","XC");
    }

}



