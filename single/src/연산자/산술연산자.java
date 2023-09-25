package 연산자;

public class 산술연산자 {
    public static void main(String[] args) {

        // 산술 연산자

        /*
        "+" : 더하기
        "-" : 빼기
        "*" : 곱하기
        "/" : 나누기
        "%" : 나머지
         */

// ==================================================================================================== //

        // 산술 연산자 예시 (정수)

        int result = 1 + 2;
        System.out.println(result); // 3

        result = result - 1;
        System.out.println(result); // 2

        result = result * 2;
        System.out.println(result); // 4

        result = result / 2;
        System.out.println(result); // 2

        result = result + 8;
        result = result % 7;
        System.out.println(result); // 3

        System.out.println("------------------------------");

        int a = 3;
        System.out.println(0%a); // 0
        System.out.println(1%a); // 1
        System.out.println(2%a); // 2
        System.out.println(3%a); // 0
        System.out.println(4%a); // 1
        System.out.println(5%a); // 2
        System.out.println(6%a); // 0

        System.out.println("------------------------------");

// ==================================================================================================== //

        // 산술 연산자 예시 (문자열)

        String first = "Hello ";
        String second = "World!";
        String third = first + second;

        System.out.println(third);

        System.out.println("------------------------------");

// ==================================================================================================== //

        // 산술 연산자 활용

        int a2 = 10;
        int b2 = 3;

        float c2 = 10.0F;
        float d2 = 3.0F;

        System.out.println(a2/b2); // 3
        // 계산되는 두 수가 모두 정수형

        System.out.println(c2/d2); // 3.33...
        // 계산되는 두 수가 모두 실수형

        System.out.println(a2/d2); // 3.33...
        // 계산되는 두 수의 데이터 타입이 서로 다름
        // 정수형 변수 a2가 float 타입으로 자동 형변환된 후 계산

        System.out.println("------------------------------");

    }
}
