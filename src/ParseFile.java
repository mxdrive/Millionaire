import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ParseFile {

    List<String> parseFile(int pointsWon) {
        // TODO: move to separate file; add files with questions for different levels
        File file = new File("./resources/questions.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> questions = new ArrayList<>();

        if (fileReader != null) {
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                questions.add(scanner.nextLine());
            }
        }
        return questions;
    }
}
