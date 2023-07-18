package xyz.angeloanan.pkwworldgen;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

public class PKWBorderParticleSpawner extends BukkitRunnable {
  private final Plugin plugin = Plugin.THIS;
  public static final Particle.DustOptions DustOpt = new Particle.DustOptions(
      org.bukkit.Color.fromRGB(255, 0, 0),
      5
  );

  public static boolean isLocInsideSphere(Location loc, Location sphereCenter, double sphereRadius) {
    double x_0 = loc.x();
    double y_0 = loc.y();
    double z_0 = loc.z();

    double x_c = sphereCenter.x();
    double y_c = sphereCenter.y();
    double z_c = sphereCenter.z();

    double distance = Math.sqrt(
        Math.pow(x_0 - x_c, 2) +
            Math.pow(y_0 - y_c, 2) +
            Math.pow(z_0 - z_c, 2)
    );

    return distance <= sphereRadius;
  }

  @Override
  public void run() {
    plugin.getServer().getOnlinePlayers().forEach(player -> {
      if (player.getGameMode() != GameMode.CREATIVE) return;

    });
  }
}
