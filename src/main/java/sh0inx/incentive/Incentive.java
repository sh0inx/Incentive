package sh0inx.incentive;

import net.milkbowl.vault.economy.Economy;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import sh0inx.incentive.commands.CommandManager;
import sh0inx.incentive.versionCheck.UpdateChecker;
import sh0inx.incentive.versionCheck.VersionCheck;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public final class Incentive extends JavaPlugin {

    private static Incentive instance;

    private final boolean bStats = getConfig().getBoolean("general.bStats");
    private static final String pluginID = "uE7Sgy9R";
    private final String pluginVersion = getDescription().getVersion();
    private final List<String> pluginAuthors = getDescription().getAuthors();
    private final String pluginAuthor = pluginAuthors.get(0);
    private final String link = getDescription().getWebsite();
    public static String debugPrefix = "[DEBUG] ";
    public static String prefix = "[Incentive] ";
    public static String commandPrefix = "Incentive >> ";

    private boolean economyCheck;

    static Logger log = Bukkit.getLogger();

    Economy economy = null;

    private static final VersionCheck.Platform platform = VersionCheck.getPlatform();
    private final String version = VersionCheck.getVersion();
    private final boolean platformSupport = VersionCheck.isPlatformSupported(platform);
    private final boolean versionSupport = VersionCheck.isVersionSupported(version);

    public static Incentive getInstance() {
        return instance;
    }

    public boolean getbStats() {
        return bStats;
    }
    public static String getPluginID() {
        return pluginID;
    }
    public String getPluginVersion() {
        return pluginVersion;
    }
    public String getPluginAuthor() {
        return pluginAuthor;
    }
    public String getLink() {
        return link;
    }
    public String getSource() {
        return "https://www.github.com/sh0inx/Incentive";
    }
    public static Logger getLog() {
        return log;
    }
    public static String getPlatform() {
        return String.valueOf(platform);
    }

    public void verifyPlatform(VersionCheck.Platform platform) {

        if(!platformSupport) {
            log.warning(VersionCheck.message(false, platform));
            getServer().getPluginManager().disablePlugin(this);
        } else {
            log.info(VersionCheck.message(true, platform));
        }
    }

    public void verifyVersion(String version) {
        if(!versionSupport) {
            log.warning(VersionCheck.message(false, version));
            getServer().getPluginManager().disablePlugin(this);
        } else {
            log.info(VersionCheck.message(true, version));
        }
    }

    public void loadMetrics() {
        if(bStats) {
            int bStatsPluginId = 17084;
            Metrics metrics = new Metrics(this, bStatsPluginId);
        }
    }

    public String loadProfile() {

        verifyPlatform(platform);
        verifyVersion(version);
        loadMetrics();

        switch(platform) {
            case Spigot, Paper -> {
                log.info(prefix + "Incentive is loading with " + platform + " profile. Some features will behave differently.");
                log.info(prefix + "It's recommended that you switch to Purpur for the best performance.");
                log.info(prefix + "Download it now: https://purpurmc.org/");
            }
            case Purpur -> {
                log.info(prefix + "Incentive is loading with Purpur profile.");
            }
        }

        return String.valueOf(platform);
    }

    public void checkForUpdates() {
        String currentVersion = UpdateChecker.getCurrentVersion();

        if(!pluginVersion.equals(currentVersion)) {
            if(currentVersion.equals("Unable to check for current version.")) {
                log.warning(prefix + "Unable to check for current version.");
            } else {
                log.info(prefix + "There's a new version available! (" + currentVersion + ")");
                log.info(prefix + "Download it here: " + link);
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
        log.info(prefix + "Enabling " + CONFIG + " module...");
        return 1;
    }

    private static int modulesOn = 0;
    public static int getModulesOn() {
        return modulesOn;
    }

    public void loadConfig() {
        saveDefaultConfig();
        modulesOn = 0;

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

        log.info(prefix + "Enabled " + getModulesOn() + " of " + modules + " modules.");
    }

    private enum ECONOMYSTATUS {
        DISABLED,
        NO_VAULT,
        NOT_REGISTERED,
        NULL_ECO,
        EXCEPTION,
        PASS,
        NULL
    }

    public boolean setupEconomy() {

        String status = String.valueOf(ECONOMYSTATUS.NULL);
        boolean setup = false;
        String exception = null;

        if(useModule(CONFIG.economy)) {
            if (VersionCheck.classExists("net.milkbowl.vault.economy.Economy")) {

                try {
                    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
                    if (rsp == null) {
                        status = String.valueOf(ECONOMYSTATUS.NOT_REGISTERED);
                    }
                    economy = rsp.getProvider();
                    if(economy != null) {
                        status = String.valueOf(ECONOMYSTATUS.PASS);
                    } else status = String.valueOf(ECONOMYSTATUS.NULL_ECO);
                } catch (Exception e) {
                    exception = String.valueOf(e);
                    status = String.valueOf(ECONOMYSTATUS.EXCEPTION);
                }

            } else status = String.valueOf(ECONOMYSTATUS.NO_VAULT);
        } else status = String.valueOf(ECONOMYSTATUS.DISABLED);

        switch(status) {
            case "NULL", "DISABLED", "NO_VAULT", "NOT_REGISTERED", "NULL_ECO" -> {
                log.warning(String.format(prefix + "Unable to load economy - [STATUS: %s]", status));
            }
            case "EXCEPTION" -> {
                log.warning(String.format(prefix + "Unable to load economy - [EXCEPTION: %s]", exception));
            }
            case "PASS" -> {
                log.info(prefix + "Loading Economy...");
                setup = true;
            }
        }

        return setup;
    }

    @Override
    public void onLoad() {
        loadConfig();
    }

    @Override
    public void onEnable() {

        //Starting up the plugin
        log.info(prefix + "Initializing Incentive...");
        instance = this;
        loadProfile();
        checkForUpdates();

        //Load economy (if applicable)
        economyCheck = setupEconomy();

        //Registering commands
        this.getCommand("inc").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {

    }
}