public enum Roman {
    I(1,'I'), V(5,'V'), X(10,'X');

    private final int value;
    private final char name;

    Roman(int value, char name) {
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return value;
    }

    public char getChar() {
        return name;
    }

}
