package sh0inx.incentive;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import sh0inx.incentive.versionCheck.UpdateChecker;
import sh0inx.incentive.versionCheck.VersionCheck;

import java.io.File;
import java.util.logging.Logger;

public final class Incentive extends JavaPlugin {

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

    String currentProfile = String.valueOf(platform);

    //debug
    public static String debugPrefix = "[DEBUG] ";

    public void verifyPlatform(VersionCheck.Platform platform) {

        if(!platformSupport) {
            log.warning(VersionCheck.message(false, platform));
            onDisable();
        } else {
            log.info(VersionCheck.message(true, platform));
        }
    }

    public void verifyVersion(String version) {
        if(!versionSupport) {
            log.warning(VersionCheck.message(false, version));
            onDisable();
        } else {
            log.info(VersionCheck.message(true, version));
        }
    }

    public void loadProfile() {
        verifyPlatform(platform);
        verifyVersion(version);

        switch(platform) {
            case Spigot, Paper -> {
                log.info("Incentive is loading with " + platform + " profile. Some features will behave differently.");
                log.info("It's recommended that you switch to Purpur for the best performance.");
                log.info("Download it now: https://purpurmc.org/");
            }
            case Purpur -> {
                log.info("Incentive is loading with Purpur profile.");
            }
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

    private enum CONFIG {
        gui,
        streaks,
        playtime,
        login,
        missions,
        shop,
        economy,
        points,
        network
    }

    //TODO: Figure out how to get total CONFIG enums dynamically for modules.
    int modules = 9;

    private FileConfiguration guiConfig;
    private FileConfiguration streaksConfig;
    private FileConfiguration playtimeConfig;
    private FileConfiguration loginConfig;
    private FileConfiguration missionsConfig;
    private FileConfiguration shopConfig;
    private FileConfiguration economyConfig;
    private FileConfiguration pointsConfig;
    private FileConfiguration networkConfig;

    public FileConfiguration getGuiConfig() {
        return this.guiConfig;
    }
    public FileConfiguration getStreaksConfig() {
        return this.streaksConfig;
    }
    public FileConfiguration getPlaytimeConfig() {
        return this.playtimeConfig;
    }
    public FileConfiguration getLoginConfig() {
        return this.loginConfig;
    }
    public FileConfiguration getMissionsConfig() {
        return this.missionsConfig;
    }
    public FileConfiguration getShopConfig() {
        return this.shopConfig;
    }
    public FileConfiguration getEconomyConfig() {
        return this.economyConfig;
    }
    public FileConfiguration getPointsConfig() {
        return this.pointsConfig;
    }
    public FileConfiguration getNetworkConfig() {
        return this.networkConfig;
    }

    private void createConfig(CONFIG CONFIG) {
        String fileName = CONFIG + ".yml";
        File configFile = new File(getDataFolder(), fileName);
        if (!configFile.exists()) {
            try {
                configFile.getParentFile().mkdirs();
                saveResource(fileName, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(configFile);

        switch (CONFIG) {
            case gui -> guiConfig = yamlConfig;
            case streaks -> streaksConfig = yamlConfig;
            case playtime -> playtimeConfig = yamlConfig;
            case login -> loginConfig = yamlConfig;
            case missions -> missionsConfig = yamlConfig;
            case shop -> shopConfig = yamlConfig;
            case economy -> economyConfig = yamlConfig;
            case points -> pointsConfig = yamlConfig;
            case network -> networkConfig = yamlConfig;
        }

        // TODO: See if there's a way to implement this example.
        // int d = switch(config) { case A=>1,case B=>3};
    }

    private boolean useModule(CONFIG CONFIG) {
        return getConfig().getBoolean("modules." + CONFIG);
    }

    public int enableModule(CONFIG CONFIG) {
        createConfig(CONFIG);
        log.info("Enabling " + CONFIG + " module...");
        return 1;
    }

    public void loadConfig() {
        saveDefaultConfig();
        int modulesOn = 0;

        if(useModule(CONFIG.gui))
            modulesOn += enableModule(CONFIG.gui);

        if(useModule(CONFIG.streaks))
            modulesOn += enableModule(CONFIG.streaks);

        if(useModule(CONFIG.playtime))
            modulesOn += enableModule(CONFIG.playtime);

        if(useModule(CONFIG.login))
            modulesOn += enableModule(CONFIG.login);

        if(useModule(CONFIG.missions))
            modulesOn += enableModule(CONFIG.missions);

        if(useModule(CONFIG.shop))
            modulesOn += enableModule(CONFIG.shop);

        if(useModule(CONFIG.economy))
            modulesOn += enableModule(CONFIG.economy);

        if(useModule(CONFIG.points))
            modulesOn += enableModule(CONFIG.points);

        if(useModule(CONFIG.network))
            modulesOn += enableModule(CONFIG.network);

        log.info("Enabled " + modulesOn + " of " + modules + " modules.");
    }

    @Override
    public void onLoad() {
        loadConfig();
    }

    @Override
    public void onEnable() {
        //Starting up the plugin
        log.info("Initializing Incentive...");
        //Metrics metrics = new Metrics(this, bStatsPluginId);
        loadProfile();
        checkForUpdates();

        //Setting command handlers
        //TODO: Start writing commands.
    }

    @Override
    public void onDisable() {

    }
}