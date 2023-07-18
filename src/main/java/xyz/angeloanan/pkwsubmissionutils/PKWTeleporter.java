package xyz.angeloanan.pkwsubmissionutils;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PKWTeleporter implements Listener {
  void teleportToNearestGold(Player player) {
    Location playerLoc = player.getLocation();
    PKWPlot plot = PKWPlot.from(playerLoc);

    playerLoc.set(
        (plot.x * PKWPlot.PLOT_CHUNK_SIZE_X * 16) - 1.5,
        PKWPlot.STRUCTURE_LOWEST_Y + 12,
        (plot.z * PKWPlot.PLOT_CHUNK_SIZE_Z * 16) + 11.5
    );
    player.teleport(playerLoc);
    player.setRotation(-90, 0);
  }

  @EventHandler
  void onPlayerVoid(PlayerMoveEvent e) {
    Player player = e.getPlayer();
    int tpHeight = player.getGameMode() == GameMode.SURVIVAL
      ? PKWPlot.STRUCTURE_LOWEST_Y - 15
      : 40;

    if (player.getLocation().getY() <= tpHeight) {
      teleportToNearestGold(player);
    }
  }
}
