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

  static final boolean CONFIGURATION_SUCCESS;

  /**
   * Project name from pom.xml: `project.name`
   */
  public static final String NAME,

  /**
   * Project version from pom.xml: `project.version`
   */
  VERSION;


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
