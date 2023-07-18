package xyz.angeloanan.pkwsubmissionutils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PKWTimerRunnable extends BukkitRunnable {
  private final Plugin plugin;
  private final Map<UUID, Integer> playerTimer;

  public PKWTimerRunnable(Plugin plugin, HashMap<UUID, Integer> map) {
    this.plugin = plugin;
    playerTimer = map;
  }

  @Override
  public void run() {
    playerTimer.forEach((uuid, time) -> {
      Player player = plugin.getServer().getPlayer(uuid);

      if (player == null) { // Player left or something
        playerTimer.remove(uuid);
        return;
      }

      TextComponent actionBarText = Component.text(String.format("%02d", time));
      player.sendActionBar(actionBarText);

      playerTimer.put(uuid, time + 1);
    });
  }
}
