package xyz.angeloanan.pkwsubmissionutils.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Syntax;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import xyz.angeloanan.pkwsubmissionutils.PKWPlot;

@CommandAlias("tpplot|plottp")
public class TpPlotCommand extends BaseCommand {
  @Default
  @Description("Teleport you to a plot ID")
  @Syntax("<x> <z>")
  public void onDefault(Player player, String[] args) {
    try {
      int x = Integer.parseInt(args[0]);
      int z = Integer.parseInt(args[1]);
      PKWPlot plot = new PKWPlot(x, z);

      player.teleport(PKWPlot.goldBlock(plot, player.getWorld()));
      player.sendMessage(
        Component
          .text("Teleported to plot ")
          .color(TextColor.color(0xAAAAAA))
          .append(
            Component
              .text(plot.x + ", " + plot.z)
              .color(TextColor.color(0x55FF55))
          )
      );
    } catch (NumberFormatException e) {
      player.sendMessage(
        Component
          .text("Invalid Plot ID.")
          .color(TextColor.color(0xFF5555))
      );
    }
  }
}
