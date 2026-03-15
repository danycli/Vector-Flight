package org.danycli.vectorFlight;

import java.io.File;

public class SaveManager {

    private static final String GAME_FOLDER = "Fly";

    public static File getSaveDirectory() {

        String appData = System.getenv("APPDATA");

        File saveDir = new File(appData, GAME_FOLDER);

        // create folder if it doesn't exist
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveDir;
    }

    public static File getStatsFile() {
        return new File(getSaveDirectory(), "stats.txt");
    }
}
