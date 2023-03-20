import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println(calc(readLine()));
    }

    public static String calc(String userInput){

        int op = 0;
        Operator[] operators = Operator.values();
        for (Operator ui: operators) {
            if ((userInput.indexOf(ui.getValue()) != -1)){
                op = ui.getValue();
            }
        }

        if (op == 0){
            System.out.println("ERROR , no operator was found");
        }

        String [] elements = userInput.split(" ");

        if (elements.length != 3) {
            System.out.println("Error");
        }

        int num1 = toInteger(elements[0]);
        int num2 = toInteger(elements[2]);

        if (num1 > 10 || num2 > 10 || num1 < 0 || num2 < 0){
            System.out.println("Error");
        }

        int result = 0;

        switch (op) {
            case 43 :
                result = num1 + num2;
                break;
            case 42:
                result = num1 * num2;
                break;
            case 45:
                result = num1 - num2;
                break;
            case 47:
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Second num is equal to zero");
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected value" + op);
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
}



