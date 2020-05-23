import java.rmi.activation.ActivationID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

class MyClass { //Struct class
    private int count=0;
    private String name1;
    private String name2;

    public MyClass(int count, String name1, String name2) {
        this.count = count;
        this.name1 = name1;
        this.name2 = name2;
    }
    public void incCount(){
        count++;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getName1() {
        return name1;
    }
    public String getName2(){
        return name2;
    }
}

class MyComp implements Comparator<MyClass> {
    public int compare(MyClass c1, MyClass c2) {
        int result;
        if (c1.getCount() < c2.getCount()) {
            return 1;
        } else if (c1.getCount() > c2.getCount()) {
            return -1;
        } else {
            result = c1.getName1().compareTo(c2.getName1());
            result = result == 0 ? c1.getName2().compareTo(c2.getName2()) : result;
            return result;
        }
    }
}

public class Main {
    String product[] = new String[10];
    private int top = 0;
    ArrayList<MyClass> foo = new ArrayList<>();

    Main() {
        Arrays.fill(product, "");
    }

    public static void main(String[] args) {
        Main test = new Main();
        int i, j, m, n;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            for (j = 0; j < m; j++) {
                test.product[j] = sc.next();
                test.top++;
            }
            test.combination(test.product);
            test.top = 0;
        }

        Collections.sort(test.foo, new MyComp());
        n = sc.nextInt();
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            j = sc.nextInt();
            for (m -= 1; m < j; m++) {
                System.out.println(test.foo.get(m).getCount() + " " + test.foo.get(m).getName1() + " "+ test.foo.get(m).getName2());//結果の表示
            }
            System.out.println("");
        }
    }

    public void combination(String in[]) {
        int i, j, k, m,flag=0;
        int n = top;
        String name[] = new String[2];
        for (i = 0; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                name[0] = in[i];
                name[1] = in[j];
                Arrays.sort(name);//2つの文字列をソート
                m = foo.size();
                for (k = 0; k < m; k++) {
                    if (foo.get(k).getName1().equals(name[0]) && foo.get(k).getName2().equals(name[1])) {//arraylist内に同じ名前のものがあればその個数を１増やす
                        flag++;
                        foo.get(k).incCount();
                    }
                }
                if (flag == 0){
                    foo.add(new MyClass(1, name[0], name[1]));
                }//存在しなければ、一番後ろに追加
                flag = 0;
            }

        }
    }
}


