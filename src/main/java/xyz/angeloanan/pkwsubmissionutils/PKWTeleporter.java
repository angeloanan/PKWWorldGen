package xyz.angeloanan.pkwsubmissionutils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.angeloanan.pkwsubmissionutils.Events.PlayerVoidOutOfBoundsTeleportEvent;

public class PKWTeleporter implements Listener {
  void teleportToNearestGold(Player player) {
    Location playerLoc = player.getLocation();
    PKWPlot plot = PKWPlot.from(playerLoc.add(PKWPlot.PLAYER_PLOT_OFFSET));

    playerLoc.set(
      (plot.x * PKWPlot.PLOT_CHUNK_SIZE_X * 16) - 1.5,
      PKWPlot.STRUCTURE_LOWEST_Y + 12,
      (plot.z * PKWPlot.PLOT_CHUNK_SIZE_Z * 16) + 11.5
    );
    playerLoc.setYaw(-90);
    playerLoc.setPitch(0);

    player.teleport(playerLoc);
  }

  @EventHandler
  void onPlayerVoid(PlayerMoveEvent e) {
    Player player = e.getPlayer();
    int tpHeight = player.getGameMode() == GameMode.SURVIVAL
      ? PKWPlot.STRUCTURE_LOWEST_Y - 15
      : 40;

    if (player.getLocation().getY() <= tpHeight) {
      teleportToNearestGold(player);
      Bukkit
        .getPluginManager()
        .callEvent(new PlayerVoidOutOfBoundsTeleportEvent(player));
    }
  }
}
