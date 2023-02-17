package sh0inx.incentive;


public class versionCheck {

    private static String prefix = Incentive.prefix;
    private static Platform platform;

    public static String message(boolean status, String version) {
        if(status) {
            String message = String.format("""
                %s Running versionCheck.message
                %s Version detected: %s
                STATUS: Pass""", prefix, prefix, version);
            return message;
        }

        if(!status) {
            String message = String.format("""
                %s Running versionCheck.message
                %s Version detected: %s
                STATUS: Failure - Bad Version""", prefix, prefix, version);
            return message;
        }
        return prefix + "ERROR: Illegal message request [versionCheck.message].";
    }
    public static String message(boolean status, Platform platform) {

        if(status) {
            String message = String.format("""
            %s Running versionCheck.message
            %s Platform detected: %s
            STATUS: Pass""", prefix, prefix, platform.toString());
            return message;
        }

        if(!status) {
            String message = String.format("""
                %s Running versionCheck.message
                %s Platform detected: %s
                STATUS: Failure - Bad Platform""", prefix, prefix, platform.toString());
            return message;
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

        switch (platform) {
            case Spigot:
            case Paper:
            case Purpur:
                return true;
            default:
                return false;
        }
    }

    public static String getVersion() {
        return org.bukkit.Bukkit.getVersion();
    }

    public static boolean isVersionSupported(String version) {

        //TODO: implement version check
        return true;
    }
}