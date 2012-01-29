package couk.Adamki11s.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
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

	/**
	 * Register a statistic service. The service will automatically be added to the UpdateRegister list.
	 * NOTE : Sync StatisticTracking must be enabled to use this feature.
	 * -----
	 * if(GlobalConfiguration.isAllowStatisticTracking()){
	 * do something
	 * } else {
	 * cant use sync stat tracking
	 * }
	 * -----
	 * @param p
	 */
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
	
	/**
	 * Get a list of all custom statistics
	 * @return
	 */
	public HashMap<String, Integer> getCustomStatistics(){
		return this.customStats;
	}
	
	/**
	 * Call this method whenever a command is executed within your plugin to track command usage.
	 */
	public void commandExecuted(){
		this.commandsExecuted++;
	}
	
	/**
	 * Register a custom statistic. This should be called on startup and the default value of the statistic will be 0.
	 * @param label
	 */
	public void addCustomStatistic(String label){
		this.customStats.put(label, 0);
	}
	
	/**
	 * Check whether a custom statistic is registered.
	 * @param label
	 * @return
	 */
	public boolean doesCustomStatisticExist(String label){
		return this.customStats.containsKey(label);
	}
	
	/**
	 * Increment (+1) the value of a custom statistic
	 * @param label
	 */
	public void incrementStatistic(String label){
		this.customStats.put(label, (this.customStats.get(label) + 1));
	}
	
	/**
	 * Decrement (-1) the value of a custom statistic
	 * @param label
	 */
	public void decrementStatistic(String label){
		this.customStats.put(label, (this.customStats.get(label) - 1));
	}
	
	/**
	 * Manually set the value of a custom statistic
	 * @param label Custom Statistic
	 * @param value Value
	 */
	public void editStatistic(String label, int value){
		this.customStats.put(label, value);
	}
	
	/**
	 * Resets the entire contents of the class. Custom statistic tags are preserved; all values are reset to 0.
	 */
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
