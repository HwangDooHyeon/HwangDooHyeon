package 연산자;

public class 비교와Boolean {
    public static void main(String[] args) {

        // Boolean: 참과 거짓을 의미하는 데이터 타입. bool 이라고도 부른다. (true / false)

// ==================================================================================================== //

        // 비교 연산자(관계 연산자): 주어진 값들이 같은지, 다른지, 큰지, 작은지를 구분하는 것을 의미 (true / false)

        // == / !=

        System.out.println(1 == 1); // true
        System.out.println(1 == 2); // false
        System.out.println("one" == "one"); // true
        System.out.println("one" == "two"); // false

        System.out.println("------------------------------");

        System.out.println(1 != 1); // false
        System.out.println(1 != 2); // true
        System.out.println("one" != "one"); // false
        System.out.println("one" != "two"); // true

        System.out.println("------------------------------");

        // > / <

        System.out.println(10 > 20); // false
        System.out.println(20 > 10); // true
        System.out.println(20 < 10); // false
        System.out.println(10 < 20); // true

        System.out.println("------------------------------");

        // >= / <=

        System.out.println(10 >= 20); // false
        System.out.println(20 >= 10); // true
        System.out.println(15 >= 15); // true
        System.out.println(20 <= 10); // false
        System.out.println(10 <= 20); // true
        System.out.println(15 <= 15); // true

        System.out.println("------------------------------");

// ==================================================================================================== //

        // 문자열 비교: .equals 메소드를 사용

        String str1 = "Hello World!";
        String str2 = new String("Hello World!");

        System.out.println(str1 == str2); // false
        System.out.println(str1.equals(str2)); // true

    }
}
