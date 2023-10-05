package xyz.angeloanan.pkwsubmissionutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@CommandAlias("bubble|bb")
@Description("Spawns a water bubble column on your head coordinate")
public class BubbleCommand extends BaseCommand {
  @Default
  public void onDefault(Player player) {
    Location playerHeadLocation = player.getEyeLocation();

    playerHeadLocation.getBlock().setType(Material.BUBBLE_COLUMN, false);
  }
}
