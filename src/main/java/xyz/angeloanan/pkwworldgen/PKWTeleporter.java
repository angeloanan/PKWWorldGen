package xyz.angeloanan.pkwworldgen;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PKWTeleporter implements Listener {
  void teleportToNearestGold(Player player) {
    Location playerLoc = player.getLocation();
    int chunkX = playerLoc.getBlockX();
    int chunkZ = playerLoc.getBlockZ();

    int originChunkX = chunkX / (PKWChunkGen.STRUCTURE_PATTERN_CHUNK_SIZE_X * 16);
    int originChunkZ = chunkZ / (PKWChunkGen.STRUCTURE_PATTERN_CHUNK_SIZE_Z * 16);

    playerLoc.set(
        (originChunkX * PKWChunkGen.STRUCTURE_PATTERN_CHUNK_SIZE_X * 16) - 0.5,
        PKWChunkGen.STRUCTURE_PATTERN_LOWEST_Y + 12,
        (originChunkZ * PKWChunkGen.STRUCTURE_PATTERN_CHUNK_SIZE_Z * 16) + 11.5
    );
    player.teleport(playerLoc);
    player.setRotation(-90, 0);
  }

  @EventHandler
  void onPlayerVoid(PlayerMoveEvent e) {
    Player player = e.getPlayer();
    int tpHeight = player.getGameMode() == GameMode.SURVIVAL ? PKWChunkGen.STRUCTURE_PATTERN_LOWEST_Y - 15 : 40;

    if (player.getLocation().getY() <= tpHeight) {
      teleportToNearestGold(player);
    }
  }
}
