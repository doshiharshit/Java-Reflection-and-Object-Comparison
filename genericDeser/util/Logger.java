package genericDeser.util;

public class Logger {

	public static enum DebugLevel {
		CONSTRUCTOR, TOSTRING, ADD_TO_DS, DATA_STRUCTURE, AVG_PREF
	};

	private static DebugLevel debugLevel = DebugLevel.AVG_PREF;

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 4:
			debugLevel = DebugLevel.CONSTRUCTOR;
			break;
		case 3:
			debugLevel = DebugLevel.AVG_PREF;
			break;
		case 2:
			debugLevel = DebugLevel.ADD_TO_DS;
			break;
		case 1:
			debugLevel = DebugLevel.DATA_STRUCTURE;
			break;
		case 0:
			debugLevel = DebugLevel.TOSTRING;
			break;
		default:
			break;
		}
	}

	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public static DebugLevel getDebugLevel() {
		return debugLevel;
	}

	public static void writeMessage(String message, DebugLevel levelIn) {
		if (debugLevel.equals(levelIn))
			System.out.println(message);
	}

	public String toString() {
		return "Debug Level is " + getDebugLevel();
	}
}
