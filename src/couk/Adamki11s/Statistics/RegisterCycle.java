package couk.Adamki11s.Statistics;

public class RegisterCycle implements Runnable {

	@Override
	public void run() {
		StatisticRegister.saveData();
	}

}
