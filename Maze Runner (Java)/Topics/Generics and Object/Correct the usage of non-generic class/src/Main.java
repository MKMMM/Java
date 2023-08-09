class Holder<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Main {
    public static void main(String... args) {
        Holder<Integer> holder = new Holder<>();
        holder.set(256);

        // correct the line to make the code compile
        Integer value = holder.get();

        // do not change
        System.out.println(value);
    }
}