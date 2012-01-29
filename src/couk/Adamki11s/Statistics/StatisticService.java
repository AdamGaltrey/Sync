package couk.Adamki11s.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

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
	
	public void addCustomStatistic(String label){
		this.customStats.put(label, 0);
	}
	
	public void incrementStatistic(String label){
		this.customStats.put(label, (this.customStats.get(label) + 1));
	}
	
	public void decrementStatistic(String label){
		this.customStats.put(label, (this.customStats.get(label) - 1));
	}
	
	public void editStatistic(String label, int value){
		this.customStats.put(label, value);
	}
	
	public void resetContents(){
		this.commandsExecuted = 0;
		List<String> labels = new ArrayList<String>();
		for(Entry<String, Integer> entry : this.customStats.entrySet()){
			labels.add(entry.getKey());
		}
		this.customStats.clear();
		for(String label : labels){
			this.customStats.put(label, 0);
		}
	}

}
