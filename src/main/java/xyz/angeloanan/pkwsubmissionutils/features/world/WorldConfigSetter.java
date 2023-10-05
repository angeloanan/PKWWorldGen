package xyz.angeloanan.pkwsubmissionutils.features.world;

import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.WorldLoadEvent;
import xyz.angeloanan.pkwsubmissionutils.Plugin;

public class WorldConfigSetter implements Listener {
  @EventHandler
  void onWorldLoad(WorldLoadEvent e) {
    World world = e.getWorld();

    // Time related
    world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
    world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
    world.setGameRule(GameRule.DO_FIRE_TICK, false);
    world.setGameRule(GameRule.DO_VINES_SPREAD, false);

    // Mobs
    world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
    world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
    world.setGameRule(GameRule.DO_INSOMNIA, false);
    world.setGameRule(GameRule.DO_MOB_SPAWNING, false);

    world.setGameRule(GameRule.DO_TILE_DROPS, false);
    world.setGameRule(GameRule.DO_MOB_LOOT, false);

    world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
    world.setGameRule(GameRule.FALL_DAMAGE, false);
    world.setGameRule(GameRule.KEEP_INVENTORY, true);

    world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);

    world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);

    world.setTime(6000L);

    world.setDifficulty(Difficulty.PEACEFUL);
    Plugin.THIS.getServer().setDefaultGameMode(GameMode.CREATIVE);
  }

  @EventHandler
  void onCropsTrample(PlayerInteractEvent e) {
    if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType().name().contains("FARMLAND") && e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
      e.setCancelled(true);
    }
  }
}
