import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

class Millionaire {
	private List<String> questions;
	private List<String> splitRow;
    private static List<Integer> shownQuestions = new ArrayList<>();
	private boolean isHelp = false;
	private boolean isPeopleHelp = false;
	private Points points = new Points();
	private int correctAnswer;

	void millionaire() {
	    ParseFile parseFile = new ParseFile();
		for (int i = 0; i < 15; i++) {
			questions = parseFile.parseFile(points.getPointsWon());
            showQuestion(setRandomQuestion());
			showVariants();

		    if (!userAnswer()) {
				break;
			}
		}
		System.out.println("You won " + points.getPointsWon() + " points");
    }

    private boolean userAnswer() {
		boolean isCorrectAnswer;
        Scanner scanner = new Scanner(System.in);
		System.out.println("Type your answer number");
        String answer =scanner.next();
		if (Integer.parseInt(answer) == 5 && !isHelp) {
			isHelp = new Hints().fiftyFifty(splitRow);
			showVariants();
			answer = scanner.next();
		}

        if (Integer.parseInt(answer) == 6 && !isPeopleHelp) {
            isPeopleHelp = new Hints().peopleHelp(points.getPointsWon(), correctAnswer);
            showVariants();
            answer = scanner.next();
        }

		if (splitRow.get(Integer.parseInt(answer)).contains("{correct}")) {
			System.out.println("This is correct!");
			isCorrectAnswer = true;
			points.setPointsWon();
            System.out.println(points.getPointsWon() + " points earned currently");
		} else {
            System.out.println("Sorry, this is incorrect!");
            isCorrectAnswer = false;
            points.dropPoints();
        }
        return isCorrectAnswer;
    }

    private void showQuestion(int randomNumber) {
		String str = questions.get(randomNumber);
		splitRow = new LinkedList<>(Arrays.asList(str.split("__")));
		System.out.println(splitRow.get(0));
    }

	private void showVariants() {
		for (int i = 1; i < splitRow.size(); i++) {
			String variant = splitRow.get(i);
			if (variant.contains("{correct}")) {
			    correctAnswer = i - 1;
				variant = variant.replace("{correct}", "");
			}
			
			System.out.println(i + ". " + variant);
        }

		if (!isHelp) {
			System.out.println("5. 50/50");
		}

        if (!isPeopleHelp) {
            System.out.println("6. People's help");
        }
	}

	private int setRandomQuestion() {
		int randomQuestion = new Random().nextInt(questions.size());
		while (shownQuestions.contains(randomQuestion)) {
			randomQuestion = new Random().nextInt(questions.size());
		}
		shownQuestions.add(randomQuestion);
		return randomQuestion;
	}

    void clearShownQuestionsList() {
		shownQuestions.clear();
    }
}
