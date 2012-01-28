package couk.Adamki11s.Utilities;

public class RuntimeData {
	
	public static double getTotalMemory(){
		return (double)Runtime.getRuntime().totalMemory();
	}
	
	public static double getFreeMemory(){
		return (double)Runtime.getRuntime().freeMemory();
	}
	
	public static double getMaxMemory(){
		return (double)Runtime.getRuntime().maxMemory();
	}
	
	public static double getUsedMemory(){
		return (double)(getTotalMemory() - getFreeMemory());
	}
	
	public static double getUsedMemoryPercentage(){
		double untruncated = (double)((getUsedMemory() / getMaxMemory()) * 100L);
		return (double)Math.round(untruncated * 100) / 100; //2.D.P Precision
	}

}
