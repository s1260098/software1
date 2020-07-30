import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class A {
    LinkedList<String> line = new LinkedList<>();
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s;
        int count = 0;
        A test = new A();
        //System.out.println("n = "+n);
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            test.line.add(s);
        }
        String lineArray[] = new String[test.line.size()];
        test.line.toArray(lineArray);
        combination(lineArray,Integer.parseInt(lineArray[0]));
    }

    public static void combination(String inArray[],int n) {
        LinkedList<MyClass> re = new LinkedList<>();
        for (int inputI = 1; inputI < n + 1; inputI++) {
            String line[] = inArray[inputI].split(" ");
            int loopN=Integer.parseInt(line[0]);
            for (int j = 1; j <=loopN ; j++) {
                for(int k=k+1;k<loopN+1;k++){

                }
            }
        }
    }
        


}