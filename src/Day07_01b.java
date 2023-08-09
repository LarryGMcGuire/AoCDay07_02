import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import org.apache.commons.io.*;



public class Day07_01b {

    public static void main(String[] args) throws IOException {

        // Reads input and loads into the ElfFileSystem using the
        File inputTxt = new File("src/2022/day01/input.txt");
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        BufferedReader input = new BufferedReader(new FileReader(inputTxt));
        ArrayList<String> data = new ArrayList<>();
        String line;
        while ((line = input.readLine()) != null){
            data.add(line);
        }
        ElfFileSystem fs = new ElfFileSystem(data);

        //  Generates a map with all the paths in the ElfFileSystem with its whole size
        Map<Path, Integer> pathSize = fs.getElfFileSystemPaths().stream()
                .collect(Collectors.toMap(i -> i, fs::getPathContentSize));

        // --------- Part 1, my input answer 1350966 ---------
        int part1 = pathSize.values().stream().filter(i -> i < 100000).mapToInt(i -> i).sum();
        System.out.println("Part 1: " + part1);

        //  --------- Part 2, my input answer 6296435 ---------
        int neededSpace = pathSize.values().stream()
                .sorted(Comparator.reverseOrder()).limit(1)
                .findFirst().orElse(-1) + 30000000 - 70000000;

        int part2 = pathSize.values().stream().filter(i -> i >= neededSpace)
                .mapToInt(i -> i)
                .sorted()
                .limit(1).findFirst().orElse(-1);
        System.out.println("Part 2: " + part2);
    }
}





