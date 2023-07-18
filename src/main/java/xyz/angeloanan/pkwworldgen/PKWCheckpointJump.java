package xyz.angeloanan.pkwworldgen;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

class CheckpointData {
  public final Location originalLocation;
  public final ItemStack[] originalInventoryContents;

  public CheckpointData(Player player) {
    originalLocation = player.getLocation();
    originalInventoryContents = player.getInventory().getStorageContents();
  }
}

public class PKWCheckpointJump implements Listener {
  Map<UUID, CheckpointData> playerData;

  public void playerEnableCheckpointJump(Player player) {
    CheckpointData data = new CheckpointData(player);
    playerData.put(player.getUniqueId(), data);

    player.setGameMode(GameMode.SURVIVAL);
  }

  void playerResetCheckpointJump(Player player) {
    // Edge case: Player doesn't have test jump enabled
    if (!playerData.containsKey(player.getUniqueId())) return;

    CheckpointData data = playerData.get(player.getUniqueId());
    player.teleport(data.originalLocation);
  }

  void playerDisableCheckpointJump(Player player) {
    CheckpointData data = playerData.remove(player.getUniqueId());

    if (data == null) return;

    player.teleport(data.originalLocation);
    player.getInventory().setStorageContents(data.originalInventoryContents);

    playerData.remove(player.getUniqueId());
  }

}
