package io.vevox.mc.sovereignty;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Matthew Struble
 */
public class SovConfigTest {

  @Test
  public void testConfiguration() {
    assertTrue("configuration should be successful", SovConfig.CONFIGURATION_SUCCESS);
    assertNotNull("name should be non-null", SovConfig.NAME);
    assertNotNull("version should be non-null", SovConfig.VERSION);
  }

}