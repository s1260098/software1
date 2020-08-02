import java.io.*;
import java.util.*;

import javax.naming.NoPermissionException;

public class bfs {
    public static void main(String args[]) {
        try {
            new bfs();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    int n, x, y, grid[][], cost;
    String product[][], dir[][];
    Map<String, Point> prodMap = new HashMap<>();
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
            prodMap.put(in[2], getPoint(gx, gy));
        }
        n = x;
        setAllArray(n);
        graph.setAllNode(n, grid);
        graph.setAdj(grid);
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            Point r = prodMap.get(in[1]);//中間地点
            cost = BFS(n, s, g, r);
            System.out.println(cost);
            // printArray(n, "ans");//デバッグ用
        }


    }

    public Point getPoint(int x, int y) {
        String d = dir[x][y];
        String dc[] = { "S", "E", "N", "W" };
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { -1, 0, 1, 0 };

        for (int i = 0; i < dc.length;i++) {
            if (dc[i].equals(d)) {
                x += dx[i];
                y += dy[i];
            }
        }
        return new Point(x, y);
    }


    
    public int BFS(int n, Point s, Point g, Point r) {
        Queue<Point> q = new ArrayDeque<>();
        int x, y, cost = 0;
        Point nowP;
        boolean rvisited, gvisited;
        boolean visited[][];
        int costd[][];

        rvisited = gvisited = false;

        while (!(rvisited && gvisited)) {
            visited = new boolean[n][n];
            costd = new int[n][n];
            if (!rvisited) {
                nowP = s;
            }
            else {
                nowP = r;
            }
            visited[nowP.x][nowP.y] = true;
            q.add(nowP);
            while (q.size() != 0) {
                nowP = q.poll();
                x = nowP.x;
                y = nowP.y;
                if (nowP.equals(r) && !rvisited) {
                    rvisited = true;
                    cost += costd[x][y];
                    q.clear();
                    break;
                }
                else if (nowP.equals(g) && rvisited) {
                    gvisited = true;
                    cost += costd[x][y];
                    break;
                }
                String key = getKey(x, y);
                Node node = graph.vMap.get(key);
                for (Point p : node.adj) {
                    if (!visited[p.x][p.y]) {
                        visited[p.x][p.y] = true;
                        q.add(new Point(p.x, p.y));
                        costd[p.x][p.y] = costd[x][y] + 1;
                    }
                }

            }
        }

        return cost;

    }

    public void printCost(int costd[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(costd[i][j] + " ");
            }
            System.out.println();
        }
    }




    String getKey(int x, int y) {
        return String.format(String.valueOf(x) + "," + String.valueOf(y));
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

    void printArray(int n, String name) {   //debug用
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
    ArrayList<Node> v = new ArrayList<>();
    Map<String, Node> vMap = new HashMap<>();

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    void addV(int x, int y) {
        Node n = new Node(x, y);
        String key = new String(String.valueOf(x) + "," + String.valueOf(y));
        v.add(n);
        vMap.put(key, n);
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
            for (Point p : n.adj)
                System.out.print(p);
            System.out.println();
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
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return p.x == this.x && p.y == this.y;
        }
        return false;
    }
}
