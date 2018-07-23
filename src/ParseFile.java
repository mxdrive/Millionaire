import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ParseFile {
    private File file = new File("./resources/questionsBase.txt");

    List<String> parseFile(int pointsWon) {
        chooseFile(pointsWon);

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

    private void chooseFile(int pointsWon) {
        if (pointsWon == 1000) {
            file = new File("./resources/questionsExtended.txt");
            new Millionaire().setShownQuestionsToNull();
        } else if (pointsWon == 32000) {
            file = new File("./resources/questionsPrime.txt");
            new Millionaire().setShownQuestionsToNull();
        }
    }
}
