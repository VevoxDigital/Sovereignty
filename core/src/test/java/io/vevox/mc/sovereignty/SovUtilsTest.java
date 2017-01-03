package io.vevox.mc.sovereignty;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Matthew Struble
 */
public class SovUtilsTest {

  @Test
  public void testCompression() throws IOException {
    byte[] str = "foobar".getBytes();

    byte[] compressed = SovUtils.compress(str);
    assertArrayEquals("de-compressed string should equal original", str, SovUtils.decompress(compressed));
  }

}