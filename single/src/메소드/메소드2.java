package 메소드;

public class 메소드2 {

    public static String number(int i, int j) {

        String output = "";

        while (i < j) {
            output += i;
            i++;
        }

        return output;

    }

    public static void main(String[] args) {

        String result = number(1, 5);

        System.out.println(result);

    }

}
