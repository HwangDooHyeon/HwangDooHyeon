package 자바튜토리얼;

public class 문자와문자열 {
    public static void main(String[] args) {

        System.out.println('생'); // Char : 문자
        System.out.println("생"); // String : 문자열 (문자 하나도 문자열이 될 수 있기 때문)
        System.out.println("생활"); // String : 문자열
        System.out.println("생활코딩" + "입니다.");

        System.out.println(1+1); // = 2
        System.out.println("1"+"1"); // = "11"

//        System.out.println("He said "Welcome programming world!""); "" 안에 ""를 쓰고싶은 경우 : "\" 사용
        System.out.println("He said \"Welcome programming world!\"");

        System.out.println("He said \"Welcome programming world!\""); // 줄바꿈을 하고싶은 경우 : "\n" 사용
        System.out.println("He said\n\"Welcome programming world!\"");

    }
}
