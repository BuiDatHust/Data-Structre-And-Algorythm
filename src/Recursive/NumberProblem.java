package Recursive;

public class NumberProblem {
    public Integer input;

    public NumberProblem(Integer n){
        this.input = n;
    }

    public String convertToBin(Integer n){
        if(n==0) {
            return "";
        }
        return convertToBin(n/2) + n%2;
    }

    public Integer sumUpToN(Integer n){
        if(n==1){
            return 1;
        }
        return sumUpToN(n-1)+n;
    }

    public static void main(String[] args) {
        NumberProblem np = new NumberProblem(3);
        System.out.println(np.sumUpToN(np.input));;
    }
}
