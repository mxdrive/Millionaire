import javafx.application.Application;

public class Main {
	// For the first group of five questions: £100 -> £200 -> £300 -> £500 -> £1,000
	// For the second group of five questions: £2,000 -> £4,000 -> £8,000 -> £16,000 -> £32,000
	// For the final group of five questions: £64,000 -> £125,000 -> £250,000 -> £500,000 -> £1,000,000
//	public static void main(String args []) {
//		new Millionaire().millionaire();
//	}

	public static void main(String args[]){
		Application.launch(UserInterface.class, args);
	}
}
