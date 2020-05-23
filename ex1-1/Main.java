import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;
 
class MyClass { //Struct class
    private int count;
    private String name;
    
    public MyClass(int count, String name) {
        this.count = count;
        this.name = name;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getName() {
        return name;
    }
}
 
class MyComp implements Comparator<MyClass> {
    public int compare(MyClass c1, MyClass c2) {
        if(c1.getCount() < c2.getCount()) {
            return 1;
        } else if(c1.getCount() > c2.getCount()) {
            return -1;
        } else {
            return c1.getName().compareTo(c2.getName());
        }
    }
}
 
public class Main {
    private String product[] = new String[10];
    private int countProduct[] = new int[10];
    private int top = 0;

    Main() {
        Arrays.fill(product, "");
        Arrays.fill(countProduct, 0);
    }

    public static void main(String[] args) {
        ArrayList<MyClass> foo = new ArrayList<>();
        Main test = new Main();
        int i, j, m, n;
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            for (j = 0; j < m; j++) {
                test.putProduct(sc.next());
            }
        }

        for (i = 0; i < test.product.length; i++) {
            foo.add(new MyClass(test.countProduct[i], test.product[i]));
        }

        Collections.sort(foo, new MyComp());

        n = sc.nextInt();
        for (i = 0; i < n; i++)
        {
            m = sc.nextInt();
            j = sc.nextInt();
            for (m-=1; m < j;m++)
                System.out.println(foo.get(m).getCount() + " " + foo.get(m).getName());
            System.out.println("");
        }

    }
    

    public void putProduct(String in) {
        int i, flag = 0;
        for (i = 0; i < top; i++) {
            if (product[i].equals(in)) {
                countProduct[i]++;
                flag++;
            }
        }
        if (flag == 0) {
            product[top]=in;
            countProduct[top]++;
            top++;
        }
    }
}