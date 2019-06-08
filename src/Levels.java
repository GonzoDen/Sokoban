import java.io.*;
import java.util.ArrayList;

class Levels {
    ArrayList<ArrayList<String>> levels = new ArrayList<>();

    Levels() throws IOException {
        String line;
        ArrayList<String> level = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("MiniCosmos.txt")); //faster than Scanner, but reading the whole line
        while ((line = reader.readLine()) != null) {
            if (line.charAt(0) != ';'){
                level.add(line);
            }
            else{
                reader.readLine();
                levels.add(level);
                level = new ArrayList<>();
            }
        }
    }
}