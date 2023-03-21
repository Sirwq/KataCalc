public enum Roman {
    I(1), V(5), X(10);

    private final int value;

    Roman(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
