package studentskills.util;

public class MyLogger {

    public static enum DebugLevel {
        DRIVER, CONSTRUCTOR, FILEPROCESSOR, INPUTDATAPROCESSOR, TREEHELPER, TREE, NODE, RESULTS, NONE
    };

    private static DebugLevel debugLevel;

    public static void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 1:
                debugLevel = DebugLevel.DRIVER;
                break;
            case 2:
                debugLevel = DebugLevel.CONSTRUCTOR;
                break;
            case 3:
                debugLevel = DebugLevel.FILEPROCESSOR;
                break;
            case 4:
                debugLevel = DebugLevel.INPUTDATAPROCESSOR;
                break;
            case 5:
                debugLevel = DebugLevel.TREEHELPER;
                break;
            case 6:
                debugLevel = DebugLevel.TREE;
                break;
            case 7:
                debugLevel = DebugLevel.NODE;
                break;
            case 8:
                debugLevel = DebugLevel.RESULTS;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    public static void setDebugValue(DebugLevel indebugValue) {
        MyLogger.debugLevel = indebugValue;
    }

    public static void writeMessage(String message, DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}