package couk.Adamki11s.Statistics;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import couk.Adamki11s.Configuration.FolderConfigurations;
import couk.Adamki11s.IO.Configuration.Standard.SyncConfiguration;
import couk.Adamki11s.Managers.SyncLog;

public class StatisticRegister {

	protected static HashSet<StatisticService> services = new HashSet<StatisticService>();
	protected static final File root = FolderConfigurations.pluginStatistics;

	protected static void saveData() {
		for (StatisticService ss : services) {
			SyncLog.logInfo("Updating statistics for plugin : " + ss.getPlugin().getDescription().getName());
			File pluginFolder = new File(root + File.separator + ss.getPlugin().getDescription().getName());
			if (!pluginFolder.exists()) {
				pluginFolder.mkdir();
			}
			File pluginFile = new File(pluginFolder + File.separator + "Statistics.syn");
			if (!pluginFile.exists()) {
				try {
					pluginFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				SyncConfiguration io = new SyncConfiguration(pluginFile);
				io.add("FirstRunDate", getDate());
				io.add("CommandsExecuted", 0);
				for (Entry<String, Integer> entry : ss.getCustomStatistics().entrySet()) {
					io.add(entry.getKey(), entry.getValue());
				}
				io.write();
			}
			SyncConfiguration io = new SyncConfiguration(pluginFile);
			io.read();
			int commandsExecuted = io.getInt("CommandsExecuted");
			String firstDate = io.getString("FirstRunDate");
			HashMap<String, Integer> set = new HashMap<String, Integer>();
			for (Entry<String, Object> entry : io.getReadableData().entrySet()) {
				if (!entry.getKey().equalsIgnoreCase("FirstRunDate") && !entry.getKey().equalsIgnoreCase("CommandsExecuted")) {
					int val = 0;
					try {
						val = Integer.parseInt(entry.getValue().toString());
					} catch (NumberFormatException ex) {
						SyncLog.logSevere("Error loading custom statistic value for plugin : " + ss.getPlugin().getDescription().getName() + ". Data type was not an integer!");
						continue;
					}
					set.put(entry.getKey(), val);
				}
			}
			io.erase();
			io.add("FirstRunDate", firstDate);
			io.add("CommandsExecuted", (commandsExecuted + ss.getCommandsExecuted()));
			for(Entry<String, Integer> entry : set.entrySet()){
				io.add(entry.getKey(), (entry.getValue() + ss.getCustomStatistics().get(entry.getKey())));
			}
			io.write();
			ss.resetContents();
		}
	}

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static String getDate() {
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

}
