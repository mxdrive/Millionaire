class Points
{
	private int[] pointsRange = {100, 200, 300, 500, 1000,
			2000, 4000, 8000, 16000, 32000,
			64000, 125000, 250000, 500000, 1000000};
	private int[] unburntPointsRange = {1000000, 32000, 1000};
	private int pointsWon = 0;
	private int pointsWonIterator = 0;
	
	void setPointsWon () {
		pointsWon = pointsRange[pointsWonIterator];
		pointsWonIterator++;
	}
	
	void dropPoints() {
		for (int iterator : unburntPointsRange) {
			if ((pointsWon - iterator) >= 0) {
				pointsWon = iterator;
				return;
			}
		}
		pointsWon = 0;
	}
	
	int getPointsWon() {
		return pointsWon;
	}
}
