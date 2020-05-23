import java.util.ArrayList;


class array {
    array(){
    }

    public static void main(String args[]) {
        ArrayList<String> a = new ArrayList<String>();

        a.add("a");
        a.add("b");
        System.out.println("get method element 0 = :" + a.get(0));
        System.out.println(a);
    }


}