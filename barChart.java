import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

class barChartUltimateExtremeDelux {
    // make new scanner
    private static Scanner scan = new Scanner (System.in);

    // define default settings
    static String symbol;
    static int barCount;
    static boolean vertGraph;

    public static void main (String[] Args) {
        // default settings
        symbol = "#";
        barCount = 5;
        vertGraph = true;
        confirm(symbol, barCount, vertGraph);
    }

    private static void confirm(String sl, int bt, boolean vh) {
        String operation;
        System.out.println("\n*************************\nBar Graph Generator\n\nConfig\n\nSymbol: "+sl+"\nNumber of bars: "+bt+"\nVertical graph: "+vh);
        System.out.print("\n\nContinue? [e/Y/N] ");
        operation = scan.nextLine();
        switch (operation.toLowerCase()) {
            case "e":
                edit();
                break;

            case "y":
                getData(sl, bt, vh);
                break;

            case "n":
                System.out.println("\nAbort.");
                break;
        }
    }

    private static void edit() {
        System.out.println("\n\nEdit...\n\n");

        // get new symbol
        System.out.print("\nSymbol? ");
        String newSym = scan.nextLine();

        // get new number of bars
        System.out.print("\nNumber of Bars? ");
        int newBC = scan.nextInt();

        // vertical or not
        System.out.print("\nVertical Bars? (true/false) ");
        boolean newVG = scan.nextBoolean();

        // clear scanner cuz it causes problem
        scan.nextLine();

        // confirm new settings
        confirm(newSym, newBC, newVG);
    }

    private static void getData (String sym, int bar, boolean vert) {
        System.out.println("\n\nGenerate...\n\n");

        // get data
        ArrayList<Integer> data = new ArrayList<Integer>();
        for(int i=0; i<bar; i++){
            System.out.print("Data "+(i+1)+"? ");
            data.add(scan.nextInt());
        }
        int max = Collections.max(data);

        // throw to renderer
        render(sym, bar, max, vert, data);
    }

    public static void render (String sym, int barCount, int max, boolean vertical, ArrayList<Integer> data) {

        // make 2d
        ArrayList<ArrayList<String>> flat = new ArrayList<ArrayList<String>>(barCount);
        for(int i=0; i<data.size(); i++) {
            ArrayList<String> line = new ArrayList<String>(max);
            for(int j=0; j<data.get(i); j++) {
                line.add(sym);
            }
            int lineSize = line.size();
            if(lineSize < max) {
                int difference = max - lineSize;
                for(int k=0; k<difference; k++) {
                    line.add(" ");
                }
            }
            flat.add(line);
        }

        // vertical padding
        System.out.println("\n");

        if (vertical == true) {
            String vertsym;
            ArrayList<ArrayList<String>> vertflat = new ArrayList<ArrayList<String>>(barCount);

            // flip x and y axis and print
            for (int l = max-1; l >= 0; l--){
                for (int m = 0; m < barCount; m++) {
                    vertsym = flat.get(m).get(l);
                    System.out.print(vertsym+" ");
                }
                System.out.println();
            }
        } else {
            // just print
            for (int l = 0; l < flat.size(); l++) {
                for (int m = 0; m < flat.get(l).size(); m++) {
                    System.out.print(flat.get(l).get(m) + " ");
                }
                System.out.println();
            }
        }
    }
}
