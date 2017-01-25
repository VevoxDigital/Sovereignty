package io.vevox.mc.sovereignty;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("unused WeakerAccess")
public class SovereigntyPlugin extends JavaPlugin {

  @Override
  public void onLoad() {
    SovConfig.playerNameFunction = sp -> Bukkit.getPlayer(sp.uuid).getName();
    SovConfig.playerOnlinePredicate = sp -> Bukkit.getPlayer(sp.uuid) != null;
  }

  @Override
  public void onEnable() {

  }

  @Override
  public void onDisable() {

  }

}
