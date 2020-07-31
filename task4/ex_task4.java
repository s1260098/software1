import java.util.*;
import java.io.*;



public class ex_task4 {
    // String product[] = new String[20];
    private int top = 0;
    ArrayList<ComboInfo> cmbArray = new ArrayList<>();
    HashSet<ComboInfo> cmb = new HashSet<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    ex_task4() {
        long start_Time = new Date().getTime();
        int i, j, m, n;
        try {
            // n = sc.nextInt();
            n = Integer.parseInt(br.readLine());
            for (i = 0; i < n; i++) {
                // m = sc.nextInt();
                String product[] = br.readLine().split(" ");

                combination(product);

            }

            Collections.sort(cmbArray, new MyComp2());

            n = Integer.parseInt(br.readLine());
            for (i = 0; i < n; i++) {
                String a[] = br.readLine().split(" ");
                m = Integer.parseInt(a[0]);
                j = Integer.parseInt(a[1]);
                for (m -= 1; m < j; m++) {
                    System.out.println(cmbArray.get(m).getCount() + " " + cmbArray.get(m).getName1() + " "
                            + cmbArray.get(m).getName2());// 結果の表示
                }
                System.out.println("");
            }
        } catch (IOException e) {

        }

        long end_Time = new Date().getTime();
        try {
            File time = new File("./time.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(time));
            bw.write("start Time : " + String.valueOf(start_Time) + "\n");
            bw.write("end Time : " + String.valueOf(end_Time) + "\n");
            bw.write("Elapsed Time : " + String.valueOf((end_Time - start_Time) / 1000.0) + "s.\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        new ex_task4();
    }

    public void combination(String in[]) {
        int i, j, idx;
        int n = in.length;
        String name[] = new String[2];
       
        for (i = 1; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                name[0] = in[i];
                name[1] = in[j];
                Arrays.sort(name);//2つの文字列をソート;
                if ((idx = binarySearch(0, cmbArray.size() - 1, name[0], name[1])) >= 0) {
                    cmbArray.get(idx).incCount();
                } else {
                    cmbArray.add(new ComboInfo(1, name[0], name[1]));
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


