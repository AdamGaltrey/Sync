package couk.Adamki11s.Queue;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import couk.Adamki11s.SQL.SyncSQL;
import couk.Adamki11s.Sync.Sync;

public class SQLQueue {
	
	private final AsyncSQLPush push;
	private final int taskID;
	
	public SQLQueue(SyncSQL sql, int period){
		this.push = new AsyncSQLPush(sql);
		this.taskID = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Sync.plugin, this.push, 20L, 20L * period);
	}
	
	public void addQuery(String query){
		this.push.addQuery(query);
	}
	
	public void eraseQueries(){
		this.push.eraseQueries();
	}
	
	public void forcePush(){
		this.push.forcePush();
	}
	
	public void endTask(){
		Bukkit.getServer().getScheduler().cancelTask(this.taskID);
	}

}

class AsyncSQLPush implements Runnable{

	private final SyncSQL sql;
	
	private boolean locked = false;
	
	private ArrayList<String> pushableQueries = new ArrayList<String>();
	
	public AsyncSQLPush(SyncSQL sql){
		this.sql = sql;
	}
	
	public void addQuery(String query){
		hold();
		this.pushableQueries.add(query);
	}
	
	public void eraseQueries(){
		hold();
		this.pushableQueries.clear();
	}
	
	public void forcePush(){
		hold();
		this.run();
	}
	
	private void hold(){
		if(!this.locked){
			return;
		}
		long timeLock = System.currentTimeMillis();
		while(locked){
			if((System.currentTimeMillis() - timeLock) / 1000 >= 10){ //10 second timeout
				this.locked = false;
				return;
			}
		}
	}
	
	@Override
	public void run() {
		this.locked = true;
		for(String query : this.pushableQueries){
			this.sql.standardQuery(query);
		}
		this.sql.closeConnection();
		this.eraseQueries();
		this.locked = false;
	}
	
}
