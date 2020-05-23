import java.util.*;
import java.io.*;

class MyClass { //Struct class
    private int count = 0;
    private  String name1;
    private  String name2;

    public MyClass( int count,  String name1,  String name2) {
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
        result = c1.getName1().compareTo(c2.getName1());
        result = result == 0 ? c1.getName2().compareTo(c2.getName2()) : result;
        return result;

    }
}

class MyComp2 implements Comparator<MyClass> {
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

public class task4 {
    String product[] = new String[20];
    private int top = 0;
    ArrayList<MyClass> cmbArray = new ArrayList<>();

    task4() {
        Arrays.fill(product, "");
    }

    public static void main(String[] args) {
        long start_Time = new Date().getTime();
        task4 test = new task4();
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

        Collections.sort(test.cmbArray, new MyComp2());
        n = sc.nextInt();
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            j = sc.nextInt();
            for (m -= 1; m < j; m++) {
                System.out.println(test.cmbArray.get(m).getCount() + " " + test.cmbArray.get(m).getName1() + " "
                        + test.cmbArray.get(m).getName2());//結果の表示
            }
            System.out.println("");
        }
        long end_Time = new Date().getTime();
        try {
            File time = new File("./time.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(time));
            bw.write("start Time : " + String.valueOf(start_Time)+"\n");
            bw.write("end Time : " + String.valueOf(end_Time)+"\n");
            bw.write("Elapsed Time : " + String.valueOf(end_Time - start_Time)+"ms.\n");
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void combination(String in[]) {
        int i, j, idx;
        int n = top;
        String name[] = new String[2];
       
        for (i = 0; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                name[0] = in[i];
                name[1] = in[j];
                Arrays.sort(name);//2つの文字列をソート;
                if ((idx = binarySearch(0, cmbArray.size() - 1, name[0], name[1])) >= 0) {
                    cmbArray.get(idx).incCount();
                } else {
                    cmbArray.add(new MyClass(1, name[0], name[1]));
                }
                Collections.sort(cmbArray, new MyComp());
            }
        }
    }

    public int binarySearch(int left, int right, String key1, String key2) {
        int flag1 = 0, flag2 = 0, mid;
        while (right >= left) {
            mid = (right + left) / 2; // 区間の真ん中
            flag1 = cmbArray.get(mid).getName1().compareTo(key1);
            flag2 = cmbArray.get(mid).getName2().compareTo(key2);
            if (flag1 == 0) {
                if (flag2 == 0) {
                    return mid;
                }
                else if (flag2 > 0) {
                    right = mid - 1;
                    continue;
                } else {
                    left = mid + 1;
                    continue;
                }
            } else if (flag1 > 0)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }
}


