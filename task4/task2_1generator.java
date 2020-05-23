import java.io.*;
import java.util.*;


class task2_1generator {
    static final int maxClient = 10000;
    static final int maxProduct = 20;
    static final int maxLength = 10;
    static final int maxPrint = 10;
    int idx = 0;
    String str = "";
    String alpha= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String productName[] = new String[maxProduct];
    Random rand = new Random();

    task2_1generator() {
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
        for (int i = 0; i < maxClient; i++) { //generate Index
            bw.write(String.valueOf(maxProduct));
            while (idx < 20) {
                generateString();
            }
            idx = 0;
            for (int k = 0; k < productName.length; k++)
                bw.write(" " + productName[k]);
            resetProduct();
            bw.newLine();
        }
        bw.write("10");bw.newLine();//print phase
        for (int i = 0; i < maxPrint; i++) {
            int a = rand.nextInt(10000) + 1;
            int b = rand.nextInt(a + 1000000) + 1;
            bw.write(String.valueOf(a) + " " + String.valueOf(b));
            bw.newLine();
        }


        if (bw != null) {
            bw.close();
        }

    }

    public void generateString()
    {
        StringBuilder sb = new StringBuilder();

        while (sb.length() < maxLength) { // length of the random string.
            int index = (int) (rand.nextFloat() * alpha.length());
            sb.append(alpha.charAt(index));
        }
        if (Arrays.asList(productName).contains(sb.toString()))
            return;
        productName[idx++] = sb.toString();
    }


    public static void main(final String args[]) {
        task2_1generator gene = new task2_1generator();
        try{
        gene.generateInput();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

