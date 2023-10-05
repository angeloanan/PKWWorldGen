package xyz.angeloanan.pkwsubmissionutils.features.plots;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.angeloanan.pkwsubmissionutils.utils.events.PlayerVoidOutOfBoundsTeleportEvent;
import xyz.angeloanan.pkwsubmissionutils.utils.Plot;

public class VoidTeleporter implements Listener {
  void teleportToNearestGold(Player player) {
    Location playerLoc = player.getLocation();
    Plot plot = Plot.from(playerLoc.add(Plot.PLAYER_PLOT_OFFSET));

    playerLoc.set(
      (plot.x * Plot.PLOT_CHUNK_SIZE_X * 16) - 1.5,
      Plot.STRUCTURE_LOWEST_Y + 12,
      (plot.z * Plot.PLOT_CHUNK_SIZE_Z * 16) + 11.5
    );
    playerLoc.setYaw(-90);
    playerLoc.setPitch(0);

    player.teleport(playerLoc);
  }

  @EventHandler
  void onPlayerVoid(PlayerMoveEvent e) {
    Player player = e.getPlayer();
    int tpHeight = player.getGameMode() == GameMode.SURVIVAL
      ? Plot.STRUCTURE_LOWEST_Y - 15
      : 40;

    if (player.getLocation().getY() <= tpHeight) {
      teleportToNearestGold(player);
      Bukkit
        .getPluginManager()
        .callEvent(new PlayerVoidOutOfBoundsTeleportEvent(player));
    }
  }
}
