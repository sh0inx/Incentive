package sh0inx.incentive;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import sh0inx.incentive.versionCheck.VersionCheck;
import sh0inx.incentive.versionCheck.UpdateChecker;

import java.util.logging.Logger;

public final class Incentive extends JavaPlugin {

    //TODO: Load plugin profile based on version found.

    private final int bStatsPluginId = 17084;
    public static final String pluginID = "uE7Sgy9R";

    public static String pluginVersion = "0.0.1";
    public static String pluginAuthor = "sh0inx;";
    public static final String link = "https://www.modrinth.com/plugins/Incentive";
    public static final String source = "https://www.github.com/sh0inx/Incentive";

    Logger log = Bukkit.getLogger();

    VersionCheck.Platform platform = VersionCheck.getPlatform();
    String version = VersionCheck.getVersion();
    boolean platformSupport = VersionCheck.isPlatformSupported(platform);
    boolean versionSupport = VersionCheck.isVersionSupported(version);

    //debug
    public static String debugPrefix = "[DEBUG] ";

    public void verifyPlatform(VersionCheck.Platform platform) {

        if(!platformSupport) {
            log.warning(VersionCheck.message(false, platform));
            //disable
        } else {
            log.info(VersionCheck.message(true, platform));
        }
    }

    public void verifyVersion(String version) {
        if(!versionSupport) {
            log.warning(VersionCheck.message(false, version));
            //disable
        } else {
            log.info(VersionCheck.message(true, version));
        }
    }

    public void checkForUpdates() {
        String currentVersion = UpdateChecker.getCurrentVersion();

        if(!pluginVersion.equals(currentVersion)) {
            if(currentVersion.equals("Unable to check for current version.")) {
                log.warning("Unable to check for current version.");
            } else {
                log.info("There's a new version available! (" + currentVersion + ")");
                log.info("Download it here: " + link);
            }
        }
    }

    @Override
    public void onEnable() {
        log.info("Initializing Incentive...");
        //Metrics metrics = new Metrics(this, bStatsPluginId);
        verifyPlatform(platform);
        verifyVersion(version);
        checkForUpdates();
    }

    @Override
    public void onDisable() {

    }
}