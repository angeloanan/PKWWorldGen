package xyz.angeloanan.pkwsubmissionutils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PKWPlotAnnouncer implements Listener {
  private final Map<UUID, PKWPlot> playerLocation = new HashMap<>();

  @EventHandler
  void onPlayerJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();
    playerLocation.put(player.getUniqueId(), PKWPlot.from(player.getLocation()));
  }

  @EventHandler
  void onPlayerCrossPlots(PlayerMoveEvent e) {
    if (!e.hasChangedBlock()) return;

    Player player = e.getPlayer();

    PKWPlot prevPlot = playerLocation.get(player.getUniqueId());
    PKWPlot currentPlotId = PKWPlot.from(player.getLocation());

    if (!currentPlotId.equals(prevPlot)) {
      announcePlotId(player, currentPlotId);
      playerLocation.put(player.getUniqueId(), currentPlotId);
    }
  }

  void announcePlotId(Player player, PKWPlot plot) {
    TextComponent text = Component
      .text("Visiting plot ")
      .append(
        Component
          .text(plot.x + ", " + plot.z)
          .decorate(TextDecoration.BOLD)
      );

    player.sendActionBar(text);
  }
}
