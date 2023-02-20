package sh0inx.incentive;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UpdateChecker {

    private final JavaPlugin plugin;
    private final String pluginID;

    public UpdateChecker(JavaPlugin plugin, String pluginID) {
        this.plugin = plugin;
        this.pluginID = pluginID;
    }

    //TODO: Write deserializing class/method to parse JSON GET response from Modrinth API.

    public void getVersion(final Consumer<String> consumer) {

        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL
                    ("https://api.modrinth.com/v2/project/" + pluginID + "/version")
                    .openStream(); Scanner scanner = new Scanner(inputStream)) {

                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
        });
    }
}