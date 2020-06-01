import java.util.*;


public class test {
        public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int n = sc.nextInt();
        int floor[][] = new int[x][y];
        String prod[][] = new String[x][y];
        String dir[][] = new String[x][y];
        int dx[] = { 0, 1, 0, -1 };
        int dy[] = { 1, 0, -1, 0 };
        String a[] = { "N", "E", "S", "W" };
        sc.nextLine();
        String[] inLine = new String[4];
        for (int i = 0; i < n; i++) {
            inLine = sc.nextLine().split(" ");
            x = Integer.parseInt(inLine[0]);
            y = Integer.parseInt(inLine[1]);
            prod[y][x] = inLine[2];
            dir[y][x] = inLine[3];
            floor[y][x] = 1;
        }

        setFloor(prod, dir, floor);
        showFloor(prod, dir, floor);

    }
    
    public static void setFloor(String prod[][], String dir[][],int floor[][]) {
        prod[0][1] = "EN";
        prod[0][6] = "EX";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 && prod[i][j] == null)
                    prod[i][j] = "X";
                if (prod[i][j] == null) {
                    prod[i][j] = "0";
                }
                if (dir[i][j] == null) {
                    dir[i][j] = "0";
                }
                if (prod[i][j].equals("X"))
                    floor[i][j] = 1;

            }
        }

    }

    public static void showFloor(String prod[][], String dir[][], int floor[][]) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(prod[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(dir[i][j] + " ");

            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(floor[i][j] + " ");

            }
            System.out.println("");
        }
    }


}