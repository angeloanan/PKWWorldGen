package xyz.angeloanan.pkwworldgen;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Plugin extends JavaPlugin implements Listener {
    public static Plugin THIS;

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().getPlugin("PlotSquared").getDescription().getVersion().startsWith("7")) {
            getLogger().severe("PlotSquared 7.x is required for HoloPlots to work!");
            getLogger().severe("Please update PlotSquared: https://www.spigotmc.org/resources/77506/");
            getLogger().severe("Disabling HoloPlots...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().log(Level.INFO, "WorldGenerator was enabled successfully.");

        THIS = this;
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "WorldGenerator was disabled successfully.");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        getLogger().log(Level.WARNING, "CustomChunkGenerator is used!");
        return new PKWChunkGen();
    }
}
