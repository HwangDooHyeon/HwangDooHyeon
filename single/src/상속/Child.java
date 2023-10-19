package 상속;

public class Child extends Parents {

    int i;
    int j;

    public Child(int a, int b, int k, int l) {
        super(a, b);
        this.i = k;
        this.j = l;
    }

    public void Child(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void calVer2() {
        System.out.println(this.i * this.j);
    }

}
