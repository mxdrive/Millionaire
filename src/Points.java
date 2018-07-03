class Points
{
	private int[] pointsRange = {100, 200, 300, 500, 1000,
			2000, 4000, 8000, 16000, 32000,
			64000, 125000, 250000, 500000, 1000000};
	private int[] unburntPointsRange = {1000, 32000, 1000000};
	private int pointsWon = 0;
	private int pointsWonIterator = 0;
	
	void setPointsWon () {
		pointsWon = pointsRange[pointsWonIterator];
		pointsWonIterator++;
		setFileWithQuestions();
	}
	
	void dropPoints() {
		for (int iterator : unburntPointsRange) {
			if ((pointsWon - iterator) >= 0) {
				pointsWon = iterator;
				return;
			} else pointsWon = 0;
		}
	}
	
	private void setFileWithQuestions() {
	    for (int iterator = 0; iterator < unburntPointsRange.length; iterator++) {
			if ((pointsWon - unburntPointsRange[iterator]) >= 0) {
				//TODO setFile(filename + (iter+1));
			}
		}
	}
	
	int getPointsWon() {
		return pointsWon;
	}
}
