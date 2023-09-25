package 변수;

public class 변수Test {
    public static void main(String[] args) {

        System.out.println(10000 + 1000);
        System.out.println((10000 + 1000) / 100);
        System.out.println(((10000 + 1000) / 100) - 10);
        System.out.println((((10000 + 1000) / 100) - 10) * 2);

        System.out.println("------------------------------");

        int a = 10000;
        int b = 1000;
        int c = 100;
        int d = 10;
        int e = 2;
        System.out.println(a + b);
        System.out.println((a + b) / c);
        System.out.println(((a + b) / c) - d);
        System.out.println((((a + b) / c) - d) * e);

        // 변수를 지정하는 이유 : 동일하게 입력해야 하는 상황에서 변수만 변경해서 유연하고 탄력적이게 대응이 가능하다. (중복 제거)
        // (중복 제거의 효용 : 가독성 상승, 유지보수에 용이)

    }
}
