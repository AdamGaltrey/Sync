package couk.Adamki11s.Statistics;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.plugin.Plugin;

import couk.Adamki11s.Configuration.GlobalConfiguration;
import couk.Adamki11s.Exceptions.MultipleStatisticServiceException;
import couk.Adamki11s.Exceptions.UnsupportedTrackingException;

public class StatisticService {

	private Plugin plugin;
	
	private int commandsExecuted;
	
	private HashMap<String, Integer> customStats = new HashMap<String, Integer>();

	public StatisticService(Plugin p){
		if(!GlobalConfiguration.isAllowStatisticTracking()){
			try {
				throw new UnsupportedTrackingException(p);
			} catch (UnsupportedTrackingException e) {
				e.printStackTrace();
			}
		}
		for (StatisticService ss : StatisticRegister.services) {
			if (ss != null) {
				if (ss.getPlugin().getDescription().getName().equalsIgnoreCase(p.getDescription().getName())) {
					try {
						throw new MultipleStatisticServiceException(p);
					} catch (MultipleStatisticServiceException e) {
						e.printStackTrace();
					}
				} else {
					continue;
				}
			}

		}
		this.plugin = p;
		StatisticRegister.services.add(this);
	}

	public Plugin getPlugin() {
		return this.plugin;
	}
	
	public int getCommandsExecuted(){
		return this.commandsExecuted;
	}
	
	public HashMap<String, Integer> getCustomStatistics(){
		return this.customStats;
	}
	
	public void commandExecuted(){
		this.commandsExecuted++;
	}
	
	public void addCustomStatistic(String label, int statistic){
		this.customStats.put(label, statistic);
	}

}
