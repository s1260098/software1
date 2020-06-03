import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) throws Exception {
        new task3();
    }

    static int INF = Integer.MAX_VALUE; // INF値
    // ４方向探索用
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };
    static String d[] = { "W", "N", "E", "S" };
    static char[] dir = { 'u', 'r', 'd', 'l' };

    String path = ""; // 移動経路(戻値用)

    // マンハッタン距離を求める
    static int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public task3() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int linen = sc.nextInt();
        String prod[][] = new String[n][m];
        String prod2[][] = new String[n][m];
        String dirProd[][] = new String[n][m];
        sc.nextLine();
        String[] inLine = new String[4];
        for (int i = 0; i < linen; i++) {
            inLine = sc.nextLine().split(" ");
            int x = Integer.parseInt(inLine[0]);
            int y = Integer.parseInt(inLine[1]);
            prod[y][x] = prod2[y][x] = inLine[2];
            dirProd[y][x] = inLine[3];
        }
        int loopn = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < loopn; i++) {
            inLine = sc.nextLine().split(" ");
            setFloor(prod, dirProd);
            int ans = astar(n, m, prod, inLine[1], dirProd);
            System.out.println(ans);
        }
    }

    // A*(A-star)探索アルゴリズム
    int astar(int n, int m, String[][] in, String prod, String dirProd[][]) {
        String maze[][] = new String[m][n];
        int shortestPath = 0;
        int[][] grid = new int[n][m]; // 移動コスト(距離)の記録
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                maze[i][j] = in[i][j];
                grid[i][j] = 0; // 移動コスト(距離)の記録
            }
        int sx, sy, mx, my, gx, gy;// スタートとゴール位置
        sx = sy = gx = gy = mx = my = 0;
        // 迷路データのパース
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j].equals(prod)) {
                    if (dirProd[i][j].equals("W")) {
                        mx = i + dx[0];
                        my = j + dy[0];
                    } else if (dirProd[i][j].equals("N")) {
                        mx = i + dx[1];
                        my = j + dy[1];
                    } else if (dirProd[i][j].equals("E")) {
                        mx = i + dx[2];
                        my = j + dy[2];
                    } else if (dirProd[i][j].equals("S")) {
                        mx = i + dx[3];
                        my = j + dy[3];
                    }
                }
            }
        }

        for (int k = 0; k < 2; k++) {
            setGoal(mx, my, maze, k);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (maze[i][j].equals("EN")) {
                        grid[i][j] = 0; // スタートは距離０
                        sy = i;
                        sx = j;
                    } else if (maze[i][j].equals("EX")) {
                        grid[i][j] = INF;
                        gy = i;
                        gx = j;
                    } else if (maze[i][j].equals("X") || !maze[i][j].equals("0")) {
                        grid[i][j] = -1; // 壁
                    } else {
                        grid[i][j] = INF;
                    }
                }
            }
            shortestPath += searchShortest(sx, sy, gx, gy, grid, n, m);
            resetGrid(grid);
        }
        return shortestPath;
    }

    public int searchShortest(int sx, int sy, int gx, int gy, int[][] grid, int n, int m) {
        // A*(A-star) 探索
        Queue<Position> q = new PriorityQueue<Position>();

        Position p = new Position(sx, sy);
        p.estimate = getManhattanDistance(sx, sy, gx, gy); // 推定値
        q.add(p);

        while (!q.isEmpty()) {
            p = q.poll();
            if (p.cost > grid[p.y][p.x]) {
                continue;
            }
            if (p.y == gy && p.x == gx) { // ゴールに到達
                path = p.path; // 移動経路(戻値用)
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || m <= nx || ny < 0 || n <= ny) { // 範囲外
                    continue;
                }
                if (grid[ny][nx] > grid[p.y][p.x] + 1) {
                    grid[ny][nx] = grid[p.y][p.x] + 1;

                    Position p2 = new Position(nx, ny);
                    p2.cost = grid[ny][nx]; // 移動コスト(スタートからの移動量)
                    p2.estimate = getManhattanDistance(nx, ny, gx, gy) + p2.cost; // 推定値
                    p2.path = p.path + dir[i]; // 移動経路(移動方向の記録)
                    q.add(p2);
                }
            }
        }
        return grid[gy][gx]; // 見つからないときは INF 値になる
    }

    public static void setFloor(String prod[][], String dir[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 && prod[i][j] == null)
                    prod[i][j] = "X";
                if ((i == 7 && j == 0) || (i == 8 && j == 7))
                    prod[i][j] = "X";
                if (prod[i][j] == null) {
                    prod[i][j] = "0";
                }
                if (dir[i][j] == null) {
                    dir[i][j] = "0";
                }
            }
        }
    }

    public static void setGoal(int i, int j, String maze[][], int flag) {// set start to prod
        if (flag == 0) {
            maze[0][1] = "EN";
            maze[i][j] = "EX";
        } else {
            maze[i][j] = "EN";
            maze[0][6] = "EX";
        }
    }

    public static void resetGrid(int grid[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = 0;
            }
        }
    }

    // 位置情報の構造体
    class Position implements Comparable<Position> {
        int x; // 座標
        int y;
        int cost; // 移動コスト(スタートからの移動量)
        int estimate; // 推定値(ゴールまでのマンハッタン距離＋移動コスト)
        String path = ""; // 移動経路(移動方向の記録)

        // コンストラクタ
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
            this.cost = 0;
        }

        // 比較関数
        @Override
        public int compareTo(Position o) {
            return this.estimate - o.estimate; // 推定値で小さい順
        }
    }
}