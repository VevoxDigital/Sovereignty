package io.vevox.mc.sovereignty;

import java.io.IOException;
import java.util.Properties;

/**
 * Constants and configuration data for the core module.
 *
 * @author Matthew Struble
 */
public class SovConfig {

  private static final Properties config = new Properties();

  public static final boolean CONFIGURATION_SUCCESS;

  // load configuration data from `module.properties`
  static {
    boolean success;
    try {
      config.load(SovConfig.class.getResourceAsStream("module.properties"));
      success = true;
    } catch (IOException e) {
      e.printStackTrace();
      success = false;
    }
    CONFIGURATION_SUCCESS = success;
  }

  /**
   * Project name from pom.xml: `project.name`
   */
  public static final String NAME = config.getProperty("name"),

  /**
   * Project version from pom.xml: `project.version`
   */
  VERSION = config.getProperty("version");

}
