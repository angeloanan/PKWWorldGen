package xyz.angeloanan.pkwsubmissionutils;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PKWTimerEventHandler implements Listener {
  HashMap<UUID, Integer> playerTimer = new HashMap<>();

  public PKWTimerEventHandler() {
    new PKWTimerRunnable(Plugin.THIS, playerTimer).runTaskTimer(Plugin.THIS, 0, 1);
  }

  @EventHandler
  void onPlayerDeath(PlayerDeathEvent e) {
    playerTimer.remove(e.getEntity().getUniqueId());
  }

  @EventHandler
  void onGameModeChange(PlayerGameModeChangeEvent e) {
    Integer time = playerTimer.remove(e.getPlayer().getUniqueId());
    if (time != null) {
      e.getPlayer().sendActionBar(Component.text("Timer stopped"));
    }
  }

  @EventHandler
  void onPlayerLogout(PlayerQuitEvent e) {
    playerTimer.remove(e.getPlayer().getUniqueId());
  }

  @EventHandler
  void onPlayerMove(PlayerMoveEvent e) {
    Player player = e.getPlayer();
    if (player.getGameMode() != GameMode.SURVIVAL) return;

    Block standingBlock = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
    switch (standingBlock.getType()) {
      case GOLD_BLOCK: {
        // Handle timer start
        playerTimer.put(player.getUniqueId(), 0);
        player.playSound(Sound.sound(Key.key("block.metal_pressure_plate.click_on"), Sound.Source.BLOCK, 1f, 1f));

        break;
      }

      case DIAMOND_BLOCK: {
        // Handle timer finish

        // Edge case: Player is not running but landed on dmd block
        if (!playerTimer.containsKey(player.getUniqueId())) return;

        // Remove will return the time
        Integer time = playerTimer.remove(player.getUniqueId());

        int milliseconds = (time * 50) % 1000;
        int seconds = time / 20;

        TextComponent message = Component.text().content("Finished in " + String.format("%02d", seconds) + "s " + String.format("%03d", milliseconds) + "ms").color(TextColor.color(0x00FF00)).build();
        player.sendMessage(message);

        player.playSound(Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.PLAYER, 1f, 1f));

        // TODO: Teleport to starting

        break;
      }
    }


  }
}
