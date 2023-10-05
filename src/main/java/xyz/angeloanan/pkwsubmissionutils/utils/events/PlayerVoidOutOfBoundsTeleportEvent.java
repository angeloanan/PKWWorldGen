package xyz.angeloanan.pkwsubmissionutils.utils.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerVoidOutOfBoundsTeleportEvent extends Event {
  private static final HandlerList handlers = new HandlerList();
  private final Player player;

  public PlayerVoidOutOfBoundsTeleportEvent(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
