package modulation;

public class OSUtils {
	private static String OS = null;

	public static String getOsName() {
		
		if(OS == null) {
			OS = System.getProperty("os.name"); 
		}
		if(OS.startsWith("Mac")) {
			System.setProperty("apple.awt.application.name", "Modulation");
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		return OS;
	}
	public static boolean isWindows() {
		return getOsName().startsWith("Windows");
	}

	public static boolean isMac() {
		return getOsName().startsWith("Mac");
	}
}
