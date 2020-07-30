import java.io.*;
import java.util.*;


class task4_gene {
    static final int maxClient = 10000;
    static final int maxProduct = 20;
    static final int maxLength = 5;
    static final int maxPrint = 10;
    int idx = 0;
    String str = "";
    String alpha = "abcdefghij";//"klmnopqrstuvwxyz";
    String productName[] = new String[maxProduct];
    Random rand = new Random();
    String alphalist[] = new String[10 * 10];
    task4_gene() {
        Arrays.fill(productName, "");
    }

    public void resetProduct() {
        Arrays.fill(productName, "");
    }

    public void generateInput() throws IOException {
        File newfile = new File("./inputFile/inputMax.txt");
        BufferedWriter bw = null;
        newfile.createNewFile(); //generate inputfile
        bw = new BufferedWriter(new FileWriter(newfile));
        bw.write(String.valueOf(maxClient)); //input 10000
        bw.newLine();
        initArray();
        for (int i = 0; i < maxClient; i++) { //generate Index
            bw.write(String.valueOf(maxProduct));
            while (idx < maxProduct) {
                generateString();
            }
            idx = 0;
           // System.out.println(productName[1]);
            for (int k = 0; k < productName.length; k++)
                bw.write(" " + productName[k]);
            productName = new String[maxProduct];
            bw.newLine();
        }
        bw.write("10");bw.newLine();//print phase
        for (int i = 0; i < maxPrint; i++) {
            int a = rand.nextInt(10) + 1;
            int b = rand.nextInt(a + 10) + 1;
            bw.write(String.valueOf(a) + " " + String.valueOf(b));
            bw.newLine();
        }


        if (bw != null) {
            bw.close();
        }

    }

    public void generateString()
    {
        int index = (int) (rand.nextFloat() * alphalist.length);
        if (Arrays.asList(productName).contains(alphalist[index]))
            return;
        productName[idx++] = alphalist[index];

    }


    public void initArray()
    {
        String a = "";
        String b = "";
        int c = 0;

        for (int i = 0; i < alpha.length(); i++) {
            for (int j = 0; j < alpha.length(); j++) {

                a = String.valueOf(alpha.charAt(i)) + String.valueOf(alpha.charAt(j));
                for (int k = 0; k < 5; k++) {
                    b += a;
                }
                alphalist[c++] = b;
                b = "";
            }
        }
        // for(String e:alphalist)
        //     System.out.println(e);
    }


    public static void main(final String args[]) {
        try {
            new task4_gene().generateInput();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

