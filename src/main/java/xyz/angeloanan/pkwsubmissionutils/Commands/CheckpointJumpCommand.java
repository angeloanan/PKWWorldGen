package xyz.angeloanan.pkwsubmissionutils.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Description;
import org.bukkit.entity.Player;
import xyz.angeloanan.pkwsubmissionutils.Plugin;

@CommandAlias("cp")
@Description("Sets and start a checkpoint jump session")
public class CheckpointJumpCommand extends BaseCommand {
  @Dependency
  private Plugin plugin;

  @Default
  public void onDefault(Player player) {
  }
}
