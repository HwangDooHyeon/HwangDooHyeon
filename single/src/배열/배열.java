package 배열;

public class 배열 {
    public static void main(String[] args) {

        String[] classGroup = new String[4]; // 배열의 크기가 '4'
//        = String[] classGroup = {"최준혁", "최유빈", "한이람", "이고잉"};

        System.out.println(classGroup.length); // 배열의 크기를 출력

        classGroup[0] = "최준혁";
        System.out.println(classGroup[0]); // 배열의 0번방 출력

        classGroup[1] = "최유빈";
        System.out.println(classGroup[1]); // 배열의 1번방 출력

        classGroup[2] = "한이람";
        System.out.println(classGroup[2]); // 배열의 2번방 출력

        classGroup[3] = "이고잉";
        System.out.println(classGroup[3]); // 배열의 3번방 출력

    }
}
