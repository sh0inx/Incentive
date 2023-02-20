package sh0inx.incentive;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Incentive extends JavaPlugin {

    //TODO: Load plugin profile based on version found.

    final int bStatsPluginId = 17084;
    final String pluginID = "gravestones";
    Logger log = Bukkit.getLogger();

    versionCheck.Platform platform = versionCheck.getPlatform();


    String version = versionCheck.getVersion();
    boolean platformSupport = versionCheck.isPlatformSupported(platform);
    boolean versionSupport = versionCheck.isVersionSupported(version);

    //debug
    static String prefix = "[DEBUG]: ";

    public void verifyPlatform(versionCheck.Platform platform) {
        if(!platformSupport) {
            log.warning(versionCheck.message(platformSupport, platform));
            //disable
        } else {
            log.info(versionCheck.message(platformSupport, platform));
        }
    }

    public void verifyVersion(String version) {
        if(!versionSupport) {
            log.warning(versionCheck.message(versionSupport, version));
            //disable
        } else {
            log.info(versionCheck.message(versionSupport, version));
        }
    }

    public void checkForUpdates() {
        new UpdateChecker(this, pluginID).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info(prefix + "There is not a new update available.");
            } else {
                getLogger().info(prefix + "There is a new update available.");
            }
        });
    }

    @Override
    public void onEnable() {
        log.info("Initializing Incentive...");
        //Metrics metrics = new Metrics(this, bStatsPluginId);
        verifyPlatform(platform);
        verifyVersion(version);
        //checkForUpdates();
    }

    @Override
    public void onDisable() {

    }
}