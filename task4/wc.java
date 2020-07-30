import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;
import java.io.*;

public class wc {
    ArrayList<Map<String, Integer>> mapList = new ArrayList<>();
    Map<String, Integer> wholeMap = new HashMap<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    wc() {
        long start_Time = new Date().getTime();
        int i, j, m, n;
        Scanner sc = new Scanner(System.in);

        try {
            n = Integer.parseInt(br.readLine());
            for (i = 0; i < n; i++) {
                String product[] = br.readLine().split(" ");
                combination(product);

            }
            // printMap();
            // groupingMap();
            // printWholeMap();

            n = Integer.parseInt(br.readLine());
            for (i = 0; i < n; i++) {
                // m = sc.nextInt();
                // j = sc.nextInt();
                String v[] = br.readLine().split(" ");
                // j = Integer.parseInt(br.readLine());

                // for (m -= 1; m < j; m++) {
                // System.out.println(test.cmbArray.get(m).getCount() + " " +
                // test.cmbArray.get(m).getName1() + " "
                // + test.cmbArray.get(m).getName2());//結果の表示
                // }
                // System.out.println("");
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
        new wc();
    }

    public void combination(String in[]) {
        int i, j;
        int n = in.length;
        Map<String, Integer> map = new HashMap<>();
        String name[] = new String[2];

        for (i = 1; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                name[0] = in[i];
                name[1] = in[j];
                Arrays.sort(name);//2つの文字列をソート;
                String cmb = new String(name[0] + "," + name[1]);
                if (find(cmb) == false) {
                    map.put(cmb, 1);
                }

            }
        }
        mapList.add(map);

    }

    public boolean find(String cmb) {
        for (Map<String, Integer> map : mapList) {
            if (map.containsKey(cmb)) {
                int count = map.get(cmb) + 1;
                map.put(cmb, count);
                return true;
            }
        }
        return false;
    }

    public void groupingMap() {
        for (Map<String, Integer> map : mapList) {
            wholeMap = Stream.of(wholeMap, map).flatMap(m -> m.entrySet().stream())
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        }
    }


    public void printMap() {
        for (Map<String, Integer> map : mapList) {
            for (Entry<String, Integer> entry : map.entrySet()) {
                System.out.print(entry.getKey());
                System.out.println(" " + entry.getValue());
            }
        }
    }

    public void printWholeMap() {
        for (Entry<String, Integer> entry : wholeMap.entrySet()) {
            System.out.print(entry.getKey());
            System.out.println(" " + entry.getValue());
        }
    }
}


