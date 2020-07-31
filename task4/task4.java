import java.util.*;
import java.io.*;

public class task4 {
    public static void main(String[] args) {
        try {
            new task4();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    Map<String, ComboInfo> map = new HashMap<>();
    List<ComboInfo> sortedList;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    task4() throws IOException {
        long start_Time = new Date().getTime();
        int i, j, m, n;

        n = Integer.parseInt(br.readLine());
        for (i = 0; i < n; i++) {
            String product[] = br.readLine().split(" ");
            combination(product);
        }
            sortedList = new ArrayList<>(map.values());
            Collections.sort(sortedList, new MyComp());

            n = Integer.parseInt(br.readLine());
            for (i = 0; i < n; i++) {
                String v[] = br.readLine().split(" ");
                j = Integer.parseInt(v[0]);
                m = Integer.parseInt(v[1]);

                for (j -= 1; j < m; j++) {
                    System.out.println(sortedList.get(j));
                }
                System.out.println("");
            }

            long end_Time = new Date().getTime();
            writeToFile(start_Time, end_Time);
            br.close();

    }
    

    public void combination(String in[]) {
        int i, j;
        int n = in.length;
        String name[] = new String[2];

        for (i = 1; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                name[0] = in[i];
                name[1] = in[j];
                Arrays.sort(name);//2つの文字列をソート;
                String cmb = new String(name[0] + "," + name[1]);
                if (map.containsKey(cmb))
                    map.get(cmb).incCount();
                else {
                    map.put(cmb, new ComboInfo(1, name[0], name[1]));
                }

            }
        }
    }

    public void writeToFile(long start_Time, long end_Time) throws IOException {
        File time = new File("./time.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(time));
        bw.write("start Time : " + String.valueOf(start_Time) + "\n");
        bw.write("end Time : " + String.valueOf(end_Time) + "\n");
        bw.write("Elapsed Time : " + String.valueOf((end_Time - start_Time) / 1000.0) + "s.\n");
        bw.close();
    }

    public void printList(List<ComboInfo> list) {
        for (ComboInfo c : list) {
            System.out.println(c);
        }
    }
}

class ComboInfo { // Struct class
    private int count = 0;
    private String name1;
    private String name2;

    public ComboInfo(int count, String name1, String name2) {
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
    public String toString() {
        return String.format(count + " " + name1 + " " + name2);
    }

}

class MyComp implements Comparator<ComboInfo> {
    public int compare(ComboInfo c1, ComboInfo c2) {
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





