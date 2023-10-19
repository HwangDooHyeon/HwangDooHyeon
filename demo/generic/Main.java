import generic.animal.Dog;
import generic.Factory;
import generic.Generic;

import java.util.ArrayList;
import java.util.List;

import static generic.Generic.output;

public class Main {

    public static void main(String[] args) {


        Generic<Integer> generic1 = new Generic<>();
        Generic<Long> generic2 = new Generic<>();
        Generic<String> generic3 = new Generic<>();

        Integer output1 = generic1.output(10);
        System.out.println(output1);

        Long output2 = generic2.output(20L);
        System.out.println(output2);

        String output3 = generic3.output("Hello World!!");
        System.out.println(output3);


        System.out.println("------------------------------");


        Generic.Inner<String> uInner = new Generic.Inner<>();

        String output4 = uInner.output("Hello Generic!!");
        System.out.println(output4);


        System.out.println("------------------------------");


        Factory<Dog> dogFactory = new Factory<>(Dog.class);
        Dog dog = dogFactory.creat();
        dog.output();


        System.out.println("------------------------------");


        List<?> i = new ArrayList<>();

        i = new ArrayList<Integer>();
        i = new ArrayList<String>();
        i = new ArrayList<Long>();


        System.out.println("------------------------------");


        Integer[] iArray = {1, 2, 3, 4, 5};
        output(iArray);


        Character[] cArray = {'ㄱ', 'ㄴ', 'ㄷ', 'ㄹ', 'ㅁ'};
        output(cArray);


        String[] sArray = {"가", "나", "다", "라", "마"};
        output(sArray);


    }

}
