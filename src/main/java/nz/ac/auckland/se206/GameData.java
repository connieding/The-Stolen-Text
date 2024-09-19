package nz.ac.auckland.se206;

public class GameData {

  private static boolean metArchivist;
  private static boolean metCollector;
  private static boolean metHistorian;
  private static boolean usedClue;

  public static boolean hasMetArchivist() {
    return metArchivist;
  }

  public static boolean hasMetCollector() {
    return metCollector;
  }

  public static boolean hasMetHistorian() {
    return metHistorian;
  }

  public static boolean hasUsedClue() {
    return usedClue;
  }

  public static void setMetArchivist(boolean metArchivist) {
    GameData.metArchivist = metArchivist;
  }

  public static void setMetCollector(boolean metCollector) {
    GameData.metCollector = metCollector;
  }

  public static void setMetHistorian(boolean metHistorian) {
    GameData.metHistorian = metHistorian;
  }

  public static void setUsedClue(boolean usedClue) {
    GameData.usedClue = usedClue;
  }
}
