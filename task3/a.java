import java.io.*;
import java.util.*;

public class bfs {
    public static void main(String args[]) {
        try {
            new bfs();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    int n, x, y, grid[][];
    String product[][], dir[][];
    Point s, g;
    Graph graph;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    bfs() throws IOException {
        String in[] = new String[20];
        in = br.readLine().split(" ");
        x = toInt(in[0]);
        y = toInt(in[1]);
        n = toInt(in[2]);
        graph = new Graph();
        grid = new int[x][y];
        product = new String[x][y];
        dir = new String[x][y];
        s = new Point(1, 0);
        g = new Point(6, 0);

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            int gx = toInt(in[0]);
            int gy = toInt(in[1]);
            grid[gx][gy] = 1;
            product[gx][gy] = in[2];
            dir[gx][gy] = in[3];
        }
        n = x;
        setAllArray(n);
        graph.setAllNode(n, grid);
        graph.setAdj(grid);
        graph.printNode();
        // printArray(n, "grid");

        int ans[][] = BFS(n,s);
    


        // for(Node node :graph.v)
        // System.out.println(node);

    }

    public int[][] BFS(int n,Point s) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int ans[][] = new int[n][n];
        int x, y;
        visited[s.x][s.y] = true;
        q.add(s);
        while (q.size() != 0) {
            s = q.poll();
            System.out.println(s);




        }

        


        return ans;
    }

    void setAllArray(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                product[i][j] = (product[i][j] == null ? "0" : product[i][j]);//productの条件
                dir[i][j] = dir[i][j] == null ? "0" : dir[i][j];//dir(方角)
                if (!(g.x == i && g.y == j) && !(s.x == i && s.y == j)) {
                    if (j == 0 || (i == 0 && j == 7) || (i == 7 && j == 7)) {
                        grid[i][j] = 1;//通過可能 1:0
                    }
                }
            }
        }
    }

    void printArray(int n, String name) {   //配列の
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (name) {
                    case "product":
                        System.out.print(product[i][j]);
                        break;
                    case "dir":
                        System.out.print(dir[i][j]);
                        break;
                    case "grid":
                        System.out.print(grid[i][j]);
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    int toInt(String in) {
        return Integer.parseInt(in);
    }




}

class Graph {
    Map<String,Node> v = new HashMap<>();

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    void addV(int x, int y) {
        String xy=new String(String.valueOf(x)+String.valueOf(y));

        v.put(xy, new Node(x, y));
    }


    void setAdj(int grid[][]) {
        int x, y;
        for (Node n : v) {
            for (int i = 0; i < 4; i++) {
                x = n.x + dx[i];
                y = n.y + dy[i];
                if ((x >= 0 && y >= 0) && (x < 8 && y < 8)) {
                    if (grid[x][y] == 0) {
                        n.adj.add(new Point(x, y));
                    }
                }
            }
        }
    }


    void setAllNode(int n, int grid[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    addV(i, j);
            }
        }
    }

    void printNode() {
        for (Node n : v) {
            System.out.println(n);
            for(Point p:n.adj)
                System.out.print(p);
            System.out.println();
        }
    }

    void printAdj() {
        for (Node n : v) {
            for (Point p : n.adj) {
                System.out.println(p);
            }
        }
    }
}

class Node {
    int x, y;
    ArrayList<Point> adj = new ArrayList<>();

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("x = " + x + ": y= " + y);
    }
}



class Point{
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return String.format("[x = " + x + ": y = " + y + "] ");
    }
    @Override
    public boolean equals(Object obj) {
        
        return super.equals(obj);
    }
}
