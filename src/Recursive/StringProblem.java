package Recursive;

public class StringProblem {
    public String input;
    public StringProblem(String str){
        this.input = str;
    }

    public String reverString(String s){
        if(s.equals("")) return "";
        return reverString(s.substring(1)) + s.charAt(0);
    }

    public Boolean isPalindrome(String s){
        if(s.length()==0 || s.length()==1) {
            return true;
        }
        if(s.charAt(0) == s.charAt(s.length()-1)) {
            return isPalindrome(s.substring(1,s.length()-1));
        }
        return false;
    }
    public static void main(String[] args) {
        StringProblem rs = new StringProblem("heeh");
        System.out.println(rs.isPalindrome(rs.input));
    }
}
