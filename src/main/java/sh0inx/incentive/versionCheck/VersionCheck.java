package sh0inx.incentive.versionCheck;

import org.bukkit.Bukkit;
import sh0inx.incentive.Incentive;

public class VersionCheck {

    private static final String prefix = Incentive.debugPrefix;

    public VersionCheck() {
    }

    public static String message(boolean status, String version) {
        if(status) {
            return String.format("""
                %s Running versionCheck.message
                %s Version detected: %s
                %s STATUS: Pass""", prefix, prefix, version, prefix);
        }

        if(!status) {
            return String.format("""
                %s Running versionCheck.message
                %s Version detected: %s
                %s STATUS: Failure - Bad Version""", prefix, prefix, version, prefix);
        }
        return prefix + "ERROR: Illegal message request [versionCheck.message].";
    }
    public static String message(boolean status, Platform platform) {

        if(status) {
            return String.format("""
            %s Running versionCheck.message
            %s Platform detected: %s
            STATUS: Pass""", prefix, prefix, platform.toString());
        }

        if(!status) {
            return String.format("""
                %s Running versionCheck.message
                %s Platform detected: %s
                STATUS: Failure - Bad Platform""", prefix, prefix, platform.toString());
        }

        return prefix + "ERROR: Illegal message request [versionCheck.message].";
    }

    public enum Platform {
        Bukkit,
        Spigot,
        Paper,
        Purpur,
        Sponge,
        Magma,
        Mohist,
        Arclight,
        Cardboard,
        Quilt,
        Fabric,
        Forge,
        NULL
    }

    private static boolean classExists(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Platform getPlatform() {

        if (classExists("org.magmafoundation.magma.Magma"))
            return Platform.Magma;
        else if (classExists("com.mohistmc.MohistMC"))
            return Platform.Mohist;
        else if (classExists("io.izzel.arclight.boot.AbstractBootstrap"))
            return Platform.Arclight;
        else if (classExists("org.cardboardpowered.CardboardConfig"))
            return Platform.Cardboard;
        else if (classExists("org.spongepowered.api.Sponge"))
            return Platform.Sponge;
        else if (classExists("net.minecraftforge.fml.server.ServerMain"))
            return Platform.Forge;
        else if (classExists("org.quiltmc.loader.impl.QuiltLoaderConfig"))
            return Platform.Quilt;
        else if (classExists("net.fabricmc.fabric.mixin.event.entity.EntityMixin"))
            return Platform.Fabric;
        else if (classExists("org.purpurmc.purpur.event.player.PlayerBookTooLargeEvent"))
            return Platform.Purpur;
        else if (classExists("io.papermc.paper.event.player.AsyncChatEvent"))
            return Platform.Paper;
        else if (classExists("org.spigotmc.CustomTimingsHandler"))
            return Platform.Spigot;
        else if (classExists("org.bukkit.Bukkit"))
            return Platform.Bukkit;
        else
            return Platform.NULL;
    }

    public static boolean isPlatformSupported(Platform platform) {

        return switch (platform) {
            case Spigot, Paper, Purpur -> true;
            default -> false;
        };
    }

    public static String getVersion() {
        System.out.println(org.bukkit.Bukkit.getMinecraftVersion());
        return org.bukkit.Bukkit.getMinecraftVersion();

    }

    public static boolean isVersionSupported(String version) {

        if(version.contains("1.19") ||
           version.contains("1.18") ||
           version.contains("1.17") ||
           version.contains("1.16")) {
            return true;
        } else {
            return false;
        }
    }
}

