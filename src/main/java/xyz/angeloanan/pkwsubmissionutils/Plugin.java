package xyz.angeloanan.pkwsubmissionutils;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.angeloanan.pkwsubmissionutils.ChunkGen.Generator;
import xyz.angeloanan.pkwsubmissionutils.Commands.BubbleCommand;
import xyz.angeloanan.pkwsubmissionutils.Commands.CheckpointJumpCommand;
import xyz.angeloanan.pkwsubmissionutils.Commands.TpPlotCommand;

import java.util.logging.Level;

public final class Plugin extends JavaPlugin {
  public static Plugin THIS;

  @Override
  public void onEnable() {
    THIS = this;

    PaperCommandManager commandManager = new PaperCommandManager(this);
    commandManager.registerCommand(new CheckpointJumpCommand());
    commandManager.registerCommand(new BubbleCommand());
    commandManager.registerCommand(new TpPlotCommand());

    PluginManager pluginManager = Bukkit.getPluginManager();
    pluginManager.registerEvents(new PKWWorldConfigSetter(), this);
    pluginManager.registerEvents(new PKWTeleporter(), this);
    pluginManager.registerEvents(new PKWTimerEventHandler(), this);
    pluginManager.registerEvents(new PKWPlotAnnouncer(), this);

    new PKWBorderParticleSpawner().runTaskTimer(Plugin.THIS, 0, 20);

    getLogger().log(Level.INFO, "PKWSubmissionUtils was enabled successfully.");
  }

  @Override
  public void onDisable() {
    getLogger().log(Level.INFO, "WorldGenerator was disabled successfully.");
  }

  @Override
  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
    getLogger().log(Level.WARNING, "CustomChunkGenerator is used!");
    return new Generator();
  }
}
