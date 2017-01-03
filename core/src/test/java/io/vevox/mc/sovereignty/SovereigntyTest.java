package io.vevox.mc.sovereignty;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Matthew Struble
 */
public class SovereigntyTest {

  @Test
  public void testSerialization() throws IOException, ClassNotFoundException {
    Sovereignty sov = Sovereignty.create();

    Sovereignty testedSov = Sovereignty.deserialize(sov.serialize());
    assertEquals("uuid should equal after serialization", sov.uuid, testedSov.uuid);
  }

}