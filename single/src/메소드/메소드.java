package 메소드;

public class 메소드 {

    public static void number1() { // 메서드 정의
        int i = 0;
        while (i < 20) {
            System.out.println(i);
            i = i+5;
        }
    }

    public static void number2(int i) { // 메서드 정의 ()안에 "매개변수" 사용
        while (i < 20) {
            System.out.println(i);
            i = i+5;
        }
    }

    public static void number3(int i, int j) { // 메서드 정의 ()안에 "매개변수" 2개 사용
        while (i < j) {
            System.out.println(i);
            i = i+5;
        }
    }

    public static void main(String[] args) {
        number1(); // 메서드 호출
        System.out.println("-----");
        number2(0); // ()안 5는 "인자"
        System.out.println("-----");
        number3(0, 20); // 매개변수 2개를 사용하여, 범위를 설정
    }

}
