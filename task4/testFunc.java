import java.util.ArrayList;
import java.util.Arrays;

public class testFunc {
    String array[] = new String[30];

    testFunc() {
        Arrays.fill(array, "");
    }

    public static void main(String args[]) {
        ArrayList<ProductInfo> foo = new ArrayList<>();
        String a[] = { "aaaa", "bbbb" };
        String b[] = { "aaaa", "bbbb" };

        int res1 = a[0].compareTo(b[0]);
        int res2 = a[1].compareTo(b[1]);


        System.out.println("res1 : "+res1+" | res2 : "+res2);


    }
}

class ProductInfo { // Struct class
    private int count = 0;
    private String name1;
    private String name2;

    public ProductInfo(int count, String name1, String name2) {
        this.count = count;
        this.name1 = name1;
        this.name2 = name2;
    }

    public void incCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }
}