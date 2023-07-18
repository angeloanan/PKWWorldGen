package xyz.angeloanan.pkwworldgen;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.angeloanan.pkwworldgen.Commands.CheckpointJumpCommand;

import java.util.logging.Level;

public final class Plugin extends JavaPlugin {
  public static Plugin THIS;

  @Override
  public void onEnable() {
    THIS = this;
    getLogger().log(Level.INFO, "WorldGenerator was enabled successfully.");

    PaperCommandManager commandManager = new PaperCommandManager(this);
    commandManager.registerCommand(new CheckpointJumpCommand());

    Bukkit.getPluginManager().registerEvents(new PKWWorldConfigSetter(), this);
    Bukkit.getPluginManager().registerEvents(new PKWTeleporter(), this);
    Bukkit.getPluginManager().registerEvents(new PKWTimerEventHandler(), this);
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
