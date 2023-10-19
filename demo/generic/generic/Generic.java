package generic;

public class Generic<T> {


    public T value = null;


    public T output(T t) {
        value = t;

        return value;
    }


    public static class Inner<U> {

        private U innerValue;

        public U output(U u) {
            innerValue = u;
            return innerValue;
        }
    }


    public static <T> InnerClass<T> Output() {

        return new InnerClass<T>();
    }


    public static class InnerClass<T> {

        public T value;

    }


    public static <T> void output(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }


}
