import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Enrollment {
    private final static int idPos = 0;
    private final static int fnPos = 1;
    private final static int lnPos = 2;
    private final static int vPos = 3;
    private final static int icPos = 4;
    private static List<String> insCos = new ArrayList<String>();

    public static void main(String[] args) {
        /* Get the filename to parse */
        Scanner input = new Scanner(System.in);
        System.out.println("What is the file path to your csv file? " +
                "Ex: /Users/Bob/Desktop/test.csv");
        String fileName = input.nextLine();

        /* Get the location to store new files */
        System.out.println("Enter the file path along with folder name where you want new files stored?" +
                " WARNING: IF THESE FILES AND FOLDERS EXIST, NEW FILES WILL NOT BE CREATED");
        String path = input.nextLine();
        File dirLocation = new File(path);

        /* Turn the CSV to an array */
        List<String[]> records = readCSVFile(fileName);

        /* Get list of insurance companies */
        for (int i = 0; i < records.size(); i++) {
            if (!insCos.contains(records.get(i)[icPos])) {
                insCos.add(records.get(i)[icPos]);
            }
        }

        /* Separate enrollees by insurance company into it's own file */
        /* Create Directory */
        if (!dirLocation.exists()) {
            dirLocation.mkdirs();
        }

        /* Sort contents of each file by last and first name (ascending)
            and remove duplicates with same ID and Ins Co */
        List<String[]> sortedResults = sortArray(records);

        /* Create files */
        for (String ins : insCos) {
            String temp = dirLocation + "/" + ins + ".txt";
            File f = new File(temp);
            try {
                if (!f.exists()) {
                    boolean created = f.createNewFile();
                    if (created) {
                        PrintWriter pw = new PrintWriter(f, "UTF-8");
                        for (int i = 0; i < sortedResults.size(); i++) {
                            if (sortedResults.get(i)[icPos].equals(ins)) {
                                pw.println(Arrays.toString(sortedResults.get(i)));
                            }
                        }
                        pw.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * readCSVFile(String) - Reads in the CSV file.
     * @param file - The file to be read.
     * @return - A list of string arrays from the CSV file.
     */
    private static List<String[]> readCSVFile(String file) {
        List<String[]> records = new ArrayList<String[]>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    /**
     * sortArray(List<String[]>) - Takes a list of string arrays and sorts them
     * based on last name and first name.  Also, if the ID number and insurance
     * company are the same, it will remove the array with the lower version number.
     * @param list - The list to be sorted.
     * @return result - The newly sorted list.
     */
    private static List<String[]> sortArray(List<String[]> list) {
        boolean sorted = true;

        /* Sort by last name then first name */
        while (sorted) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i)[lnPos].toUpperCase().compareTo(list.get(j)[lnPos].toUpperCase()) > 0) {
                        String[] temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                        sorted = false;
                    } else if (list.get(i)[lnPos].toUpperCase().equals(list.get(j)[lnPos].toUpperCase())) {
                        if (list.get(i)[fnPos].compareTo(list.get(j)[fnPos]) > 0 &&
                                !list.get(i)[fnPos].equals(list.get(j)[fnPos])) {
                            String[] temp = list.get(i);
                            list.set(i, list.get(j));
                            list.set(j, temp);
                            sorted = false;
                        }
                    }
                }
            }
        }

        sorted = true;

        /* If id and ins co is the same, remove the one with the lower version number */
        while(sorted) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i)[idPos].equals(list.get(j)[idPos]) &&
                            list.get(i)[icPos].toUpperCase().equals(list.get(j)[icPos].toUpperCase())) {
                        int temp1 = Integer.parseInt(list.get(i)[vPos]);
                        int temp2 = Integer.parseInt(list.get(j)[vPos]);
                        if (temp1 < temp2) {
                            list.remove(i);
                            sorted = false;
                        } else {
                            list.remove(j);
                            sorted = false;
                        }
                    }
                }
            }
        }
        return list;
    }
}
