import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class productInfo {
    int count = 0;
    String name = "";

    productInfo(int count, String name) {
        this.count = count;
        this.name = name;
    }

    void inc() {
        count++;
    }

    String getName() {
        return name;
    }

    int getCount() {
        return count;
    }
}

class MyComp implements Comparator<productInfo> {
    public int compare(productInfo c1, productInfo c2) {
        if (c1.getCount() < c2.getCount()) {
            return 1;
        } else if (c1.getCount() > c2.getCount()) {
            return -1;
        } else {
            return c1.getName().compareTo(c2.getName());
        }
    }
}




public class task1_Main {
    ArrayList<productInfo> list = new ArrayList<>();

    public static void main(String[] args) {
        task1_Main request = new task1_Main();
        int i, j, m, n;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            for (j = 0; j < m; j++) {
                request.search(sc.next());
            }
        }
        System.out.println(request.list.size());

        Collections.sort(request.list, new MyComp());
        n = sc.nextInt();
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            j = sc.nextInt();
            for (m -= 1; m < j; m++) {
                System.out.println(request.list.get(m).getCount() + " " + request.list.get(m).getName());
            }
            System.out.println("");
        }
    }


    void search(String in) {
        for (productInfo element : list) {
            int flag = element.name.compareTo(in);
            if (flag == 0) {
                element.inc();
                return;
            }
        }
        list.add(new productInfo(1,in));
    }
}



