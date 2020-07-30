import java.util.*;



import java.io.*;



public class hash4 {
    String product[] = new String[20];
    private int top = 0;
    ArrayList<MyClass> cmbArray = new ArrayList<>();
    ArrayList<ArrayList<MyClass>> cmba = new ArrayList<>();


    hash4() {
        Arrays.fill(product, "");
    }

    public static void main(String[] args) {
        long start_Time = new Date().getTime();
        hash4 test = new hash4();
        int i, j, m, n;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        for (i = 0; i < n;i++) {
            m = sc.nextInt();
            for (j = 0; j < m; j++) {
                test.product[j] = sc.next();
                test.top++;
            }
            test.combination(test.product);
            test.top = 0;
        }
        test.printList();
        Collections.sort(test.cmbArray, new MyComp2());
        n = sc.nextInt();
        for (i = 0; i < n; i++) {
            m = sc.nextInt();
            j = sc.nextInt();
            // for (m -= 1; m < j; m++) {
            //     System.out.println(test.cmbArray.get(m).getCount() + " " + test.cmbArray.get(m).getName1() + " "
            //             + test.cmbArray.get(m).getName2());//結果の表示
            // }
            // System.out.println("");
        }
        long end_Time = new Date().getTime();
        try {
            File time = new File("./time.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(time));
            bw.write("start Time : " + String.valueOf(start_Time)+"\n");
            bw.write("end Time : " + String.valueOf(end_Time)+"\n");
            bw.write("Elapsed Time : " + String.valueOf((end_Time - start_Time)/1000.0)+"s.\n");
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void combination(String in[]) {
        int i, j, idx, flag = 0;
        int n = top;
        ArrayList<MyClass> a = new ArrayList<>();

        String name[] = new String[2];

        for (i = 0; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                name[0] = in[i];
                name[1] = in[j];
                Arrays.sort(name);//2つの文字列をソート;
                MyClass c = new MyClass(1, name[0], name[1]);
                for (ArrayList<MyClass> list : cmba) {
                    // System.out.println("list = "+list);
                    idx = binarySearch(0, list.size() - 1, name[0], name[1], list);
                    if (idx >= 0) {
                        list.get(idx).incCount();
                        flag++;
                        break;
                    }
                }
                if(flag==0)
                    a.add(c);
                flag = 0;
            }
        }
        cmba.add(a);
    }



    
        
    public void printList() {
        int i = 0;
        for (ArrayList<MyClass> a : cmba) {
            System.out.println(("l["+i++)+"] = "+a);
        }
    }


    public int binarySearch(int left, int right, String key1, String key2,ArrayList<MyClass> cmb) {
        int flag1 = 0, flag2 = 0, mid;
        while (right >= left) {
            mid = (right + left) / 2; // 区間の真ん中
            flag1 = cmb.get(mid).getName1().compareTo(key1);
            flag2 = cmb.get(mid).getName2().compareTo(key2);
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


