package io.vevox.mc.sovereignty;

import io.vevox.mc.sovereignty.sov.SovPlayer;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Constants and configuration data for the core module.
 *
 * @author Matthew Struble
 */
public class SovConfig {

  private static final Properties config = new Properties();

  static final boolean CONFIGURATION_SUCCESS;

  /**
   * Project name from pom.xml: `project.name`
   */
  public static final String NAME,

  /**
   * Project version from pom.xml: `project.version`
   */
  VERSION;

  /**
   * A {@link Predicate} for determining if a player is online.
   */
  @Nullable
  public static Predicate<SovPlayer> playerOnlinePredicate;

  /**
   * A {@link Function} for fetching the player's name. Only called if the player is online.
   */
  @Nullable
  public static Function<SovPlayer, String> playerNameFunction;

  // load configuration data from `module.properties`
  static {
    boolean success;
    try {
      config.load(ClassLoader.getSystemClassLoader().getResourceAsStream("module.properties"));
      success = true;
    } catch (IOException e) {
      e.printStackTrace();
      success = false;
    }
    CONFIGURATION_SUCCESS = success;

    NAME = config.getProperty("name");
    VERSION = config.getProperty("version");
  }


}
