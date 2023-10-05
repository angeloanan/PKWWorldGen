package xyz.angeloanan.pkwsubmissionutils;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.angeloanan.pkwsubmissionutils.features.world.WorldConfigSetter;
import xyz.angeloanan.pkwsubmissionutils.features.world.chunkGen.Generator;
import xyz.angeloanan.pkwsubmissionutils.commands.BubbleCommand;
import xyz.angeloanan.pkwsubmissionutils.commands.CheckpointJumpCommand;
import xyz.angeloanan.pkwsubmissionutils.commands.TpPlotCommand;
import xyz.angeloanan.pkwsubmissionutils.features.plots.PlotAnnouncer;
import xyz.angeloanan.pkwsubmissionutils.features.plots.VoidTeleporter;
import xyz.angeloanan.pkwsubmissionutils.features.runAttempt.TimerEventHandler;
import xyz.angeloanan.pkwsubmissionutils.features.world.WorldOutOfBoundsHandler;

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
    pluginManager.registerEvents(new WorldConfigSetter(), this);
    pluginManager.registerEvents(new VoidTeleporter(), this);
    pluginManager.registerEvents(new TimerEventHandler(), this);
    pluginManager.registerEvents(new PlotAnnouncer(), this);
    pluginManager.registerEvents(new WorldOutOfBoundsHandler(), this);

    new BorderParticleSpawner().runTaskTimer(Plugin.THIS, 0, 20);

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
