import java.util.Scanner;
import java.util.Arrays;



public class Ex1 {
    String product[] = new String[10];
    int countProduct[] = new int[10];
    int top=0;
    Ex1() {
        Arrays.fill(product, "");
        Arrays.fill(countProduct, 0);
    }
    public static void main(String args[]) {
        int i, j, m, n;
        Scanner sc = new Scanner(System.in);
        Ex1 test = new Ex1();
        

        n = sc.nextInt(); //Count the product are bought by people
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            for (j = 0; j < m; j++) {
                test.putProduct(sc.next());
                // System.out.println(sc.next());
            }
        }
        Arrays.sort(test.product);
            for (i = 0; i < test.product.length; i++) { //print test
                System.out.println( i+" :"+test.product[i]+" "+test.countProduct[i]);
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
            product[top] = in;
            countProduct[top]++;
            top++;
        }

    }


}