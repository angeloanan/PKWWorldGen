package xyz.angeloanan.pkwworldgen.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BubbleCommand implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (sender instanceof ConsoleCommandSender) {
      sender.sendMessage(Component.text("This command can only be executed by a player."));
      return true;
    }

    Player player = (Player) sender;
    Location playerHeadLocation = player.getEyeLocation();

    playerHeadLocation.getBlock().setType(Material.WATER, false);

    BubbleColumn water = (BubbleColumn) playerHeadLocation.getBlock().getBlockData();
    water.setDrag(true);
    playerHeadLocation.getBlock().setBlockData(water, false);


    return true;
  }
}
