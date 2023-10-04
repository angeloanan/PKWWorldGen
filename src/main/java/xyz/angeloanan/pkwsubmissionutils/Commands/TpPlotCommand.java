package xyz.angeloanan.pkwsubmissionutils.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Syntax;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.angeloanan.pkwsubmissionutils.PKWPlot;

@CommandAlias("tpplot|plottp|ptp|tpp")
public class TpPlotCommand extends BaseCommand {
  @Default
  @Description("Teleport you to a plot ID")
  @Syntax("<x> <z>")
  public static void onDefault(Player player, String[] args) {
    if (args.length != 2) {
      player.sendMessage(
        Component
          .text("Usage: /tpplot <x> <z>")
          .color(TextColor.color(0xFF5555))
      );

      return;
    }

    try {
      int x = Integer.parseInt(args[0]);
      int z = Integer.parseInt(args[1]);
      PKWPlot plot = new PKWPlot(x, z);
      Location location = PKWPlot.goldBlock(plot, player.getWorld());
      location.add(0, 2, 0);

      if (plot.x > PKWPlot.MAX_PLOT_X || plot.z > PKWPlot.MAX_PLOT_Z) {
        player.sendMessage(
          Component
            .text("Plot ID exceeded limit!")
            .color(TextColor.color(0xFF5555))
        );

        return;
      }

      player.teleport(location);
      player.setRotation(-90, 0);

      player.sendMessage(
        Component
          .text("Teleported to plot ")
          .color(TextColor.color(0xAAAAAA))
          .append(
            Component
              .text(String.format("[%d, %d]", plot.x, plot.z))
              .color(TextColor.color(0xFFFFFF))
              .decorate(TextDecoration.BOLD)
          )
      );
    } catch (NumberFormatException e) {
      player.sendMessage(
        Component
          .text("Invalid Plot ID. Usage: /tpplot <x> <z>")
          .color(TextColor.color(0xFF5555))
      );
    }
  }
}
