import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.HashMap;

class Info {
    String p;
    int count;
}

public class Ex1test {
    public static void main(String args[]){

    
    manageProduct a = new manageProduct();
        a.print();
    }
}

class manageProduct {
    int top = 0;

    manageProduct() {
    }

    int i, j, m, n;
    Scanner sc = new Scanner(System.in);
    Ex1map test = new Ex1map();
    Info []product = new Info[10];

    for(i=0;i<product.length;i++)
    {
        product[i] = new Info();
    }

    n=sc.nextInt(); //Count the product are bought by people
    for(i=0;i<n;i++)
    {
        m = sc.nextInt();
        for (j = 0; j < m; j++) {
            test.putProduct(sc.next());
        }
    }

    //

    public void putProduct(String in){
        int i, flag = 0;
        for (i = 0; i < top; i++) {
            if (product[i].p.equals(in)) {
                product[i].count++;
                flag++;
            }
        }
        if (flag == 0) {
            product[top].p = in;
            product[top].count++;
            top++;
        }
    }
}



