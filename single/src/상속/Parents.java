package 상속;

public class Parents {

    int i;
    int j;

    public void 상속() {}

    public Parents(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void Cal() {
        System.out.println(this.i + this.j);
    }

}
