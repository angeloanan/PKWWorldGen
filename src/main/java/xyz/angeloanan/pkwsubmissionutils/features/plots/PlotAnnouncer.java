package xyz.angeloanan.pkwsubmissionutils.features.plots;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.angeloanan.pkwsubmissionutils.utils.Plot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlotAnnouncer implements Listener {
  private final Map<UUID, Plot> playerLocation = new HashMap<>();

  @EventHandler
  void onPlayerJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();
    playerLocation.put(player.getUniqueId(), Plot.from(player.getLocation()));
  }

  @EventHandler
  void onPlayerCrossPlots(PlayerMoveEvent e) {
    if (!e.hasChangedBlock()) return;

    Player player = e.getPlayer();

    Plot prevPlot = playerLocation.get(player.getUniqueId());
    Plot currentPlotId = Plot.from(player.getLocation().add(Plot.PLAYER_PLOT_OFFSET));
    //                                                        ^ Plot offset, prob need refactor

    if (!currentPlotId.equals(prevPlot)) {
      announcePlotId(player, currentPlotId);
      playerLocation.put(player.getUniqueId(), currentPlotId);
    }
  }

  void announcePlotId(Player player, Plot plot) {
    TextComponent text = Component
      .text("Visiting plot ")
      .append(
        Component
          .text(String.format("[%d, %d]", plot.x, plot.z))
          .decorate(TextDecoration.BOLD)
      );

    player.sendActionBar(text);
  }
}
