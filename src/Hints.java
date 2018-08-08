import java.util.*;

class Hints {

    private int coefficient;

    boolean fiftyFifty(List<String> splitRow) {
        for (int i = 0; i < 2; i++) {
            int rowToRemove = new Random().nextInt(splitRow.size() - 1) + 1;
            while (splitRow.get(rowToRemove).contains("{correct}")) {
                rowToRemove = new Random().nextInt(splitRow.size() - 1) + 1;
            }
            splitRow.remove(rowToRemove);
        }
        return true;
    }

    boolean peopleHelp(int pointsWon, int correctAnswer) {
        setCoefficient(pointsWon);
        int[] randomPercents = randomPercentsList(correctAnswer);
        System.out.println("---------------");
        for (int i = 0; i < randomPercents.length; i++) {
            System.out.println((i + 1) +" variant - " + randomPercents[i] + "%");
        }
        System.out.println("---------------");
        return true;
    }

    boolean friendsHelp(int pointsWon, int correctAnswer) {
        setCoefficient(pointsWon);
        System.out.println("---------------");
        System.out.println("Your friend think variant " + (getIndexOfMaxValue(randomPercentsList(correctAnswer)) + 1) +
        " is correct");
        System.out.println("---------------");
        return true;
    }

    private int[] randomPercentsList(int correctAnswerIndex) {
        List<int[]> randomRes = new ArrayList<>();
        for (int k = 0; k <= coefficient; k++) {
            int[] randomPercentsArray;
            do {
                randomPercentsArray = randomRes();
            } while (getIndexOfMaxValue(randomPercentsArray) != correctAnswerIndex);
            randomRes.add(randomPercentsArray);
        }

        for (int j = 0; j <= (100 - coefficient); j++) {
            int[] randomPercentsArray;
            do {
                randomPercentsArray = randomRes();
            } while (getIndexOfMaxValue(randomPercentsArray) == correctAnswerIndex);
            randomRes.add(randomPercentsArray);
        }

        Collections.shuffle(randomRes);

        return randomRes.get(new Random().nextInt(randomRes.size()));
    }

    private int[] randomRes() {
        int[] pollRes = new int[4];
        for (int variants = 0; variants < 4; variants++) {
            if (variants == 3) {
                pollRes[variants] = 100 - Arrays.stream(pollRes).sum();
            } else if (variants == 0) {
                pollRes[variants] = new Random().nextInt(100 + 1);
            } else {
                do {
                    pollRes[variants] = new Random().nextInt(100 + 1);
                } while (Arrays.stream(pollRes).sum() != 100 && Arrays.stream(pollRes).sum() >= 100);
            }
        }
        return pollRes;
    }

    private int getIndexOfMaxValue(int[] array) {
        int maxAt = 0;

        for (int i = 0; i < array.length; i++) {
            maxAt = array[i] > array[maxAt] ? i : maxAt;
        }
        return maxAt;
    }

    private void setCoefficient(int pointsWon) {
        if (pointsWon < 1000) {
            coefficient = 90;
        } else if (pointsWon < 32000) {
            coefficient = 80;
        } else coefficient = 70;
    }
}
