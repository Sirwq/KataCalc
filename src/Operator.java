public enum Operator {
    PLUS(43), MINUS(45), MULTIPLICATION(42), DIVISION(47);

    private final int value;

    Operator(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
