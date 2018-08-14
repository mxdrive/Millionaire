import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

class Millionaire {
	private List<String> questions;
	private List<String> splitRow;
    private static List<Integer> shownQuestions = new ArrayList<>();

	private boolean isFiftyFiftyHelp = false;
	private boolean isPeopleHelp = false;
	private boolean isFriendsHelp = false;
	private Points points = new Points();
	private int correctAnswer;
	private List<String> variantsList = new ArrayList<>();

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
        int answer = getAnswer(scanner);

		while ((answer - 1) >= 4) {
			if (variantsList.get((answer - 1)).equals(". 50/50") && !isFiftyFiftyHelp) {
				isFiftyFiftyHelp = new Hints().fiftyFifty(splitRow);
				answer = ifHint(answer, scanner);
			}

			if (variantsList.get((answer - 1)).equals(". People's help") && !isPeopleHelp) {
				isPeopleHelp = new Hints().peopleHelp(points.getPointsWon(), correctAnswer);
				answer = ifHint(answer, scanner);
			}

			if (variantsList.get((answer - 1)).equals(". Friend's help") && !isFriendsHelp) {
				isFriendsHelp = new Hints().friendsHelp(points.getPointsWon(), correctAnswer);
				answer = ifHint(answer, scanner);
			}
		}

		if (splitRow.get(answer).contains("{correct}")) {
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
		variantsList.clear();
		for (int variantsIterator = 1; variantsIterator < splitRow.size(); variantsIterator++) {
			String variant = splitRow.get(variantsIterator);
			if (variant.contains("{correct}")) {
			    correctAnswer = variantsIterator - 1;
				variant = variant.replace("{correct}", "");
			}
			
			variantsList.add(". " + variant);
        }

		if (!isFiftyFiftyHelp) {
			variantsList.add(". 50/50");
		}

        if (!isPeopleHelp) {
            variantsList.add(". People's help");
        }

        if (!isFriendsHelp) {
            variantsList.add(". Friend's help");
        }

		for (int s = 1; s <= variantsList.size(); s++) {
			System.out.println(s + variantsList.get(s - 1));
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

	private int ifHint(int answer, Scanner scanner) {
		variantsList.remove(answer - 1);
		showVariants();
		System.out.println("Type your answer number");
		answer = getAnswer(scanner);
		return answer;
	}

	private int getAnswer(Scanner scanner) {
		String answer = scanner.next();
		int parsedAnswer = 0;
		while (parsedAnswer == 0) {
			try {
				parsedAnswer = Integer.parseInt(answer);
			} catch (Exception e) {
				System.out.println("Wrong input! Please try again");
				answer = scanner.next();
			}
		}
		return parsedAnswer;
	}

    void clearShownQuestionsList() {
		shownQuestions.clear();
    }

	public boolean[] isHelp() {
		boolean[] isHelp = {isFiftyFiftyHelp, isPeopleHelp, isFriendsHelp};
		return isHelp;
	}
}
