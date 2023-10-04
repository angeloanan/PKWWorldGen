package xyz.angeloanan.pkwsubmissionutils.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("plot|p")
@Description("The main interface for plot-related commands")
public class PlotCommand extends BaseCommand {
  @Default
  @Subcommand("info|i")
  @Description("Shows information about the plot you're standing on")
  public void plotInfo(Player player) {
    // Plot name
    // Plot ID
    // Owner
    // Since when

    player.sendMessage("Plot info");
  }

  @Subcommand("claim")
  @Description("Claims the plot you're standing on")
  public void plotClaim(Player player) {
    // TODO: Check if plot has not been claimed

    player.sendMessage("Plot claim");
  }

  @Subcommand("list")
  @Description("Lists all plots you own")
  public void plotList(Player player) {
    // TODO: Edge case where player doesn't have any plots
    // TODO: Support for pagination
    player.sendMessage("Plot list");
  }

  @Subcommand("name|rename")
  @Description("Renames the plot you're standing on")
  public void plotRename(Player player) {
    // TODO: Check if plot is claimed by player

    player.sendMessage("Plot rename");
  }

  @HelpCommand
  public void onHelp(CommandSender sender, CommandHelp help) {
    sender.sendMessage("Plot help");
    help.showHelp();
  }
}
