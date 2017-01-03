package io.vevox.mc.sovereignty;

import sun.misc.IOUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Utility methods for Sovereignty
 *
 * @author Matthew Struble
 */
@SuppressWarnings("WeakerAccess")
public class SovUtils {

  /**
   * Compresses the given string.
   *
   * @param bytes The bytes to compress
   *
   * @return The compressed bytes
   * @throws IOException General stream errors
   */
  public static byte[] compress(byte[] bytes) throws IOException {
    if (bytes == null || bytes.length == 0) return new byte[0];

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream compressor = new GZIPOutputStream(out);
    compressor.write(bytes);
    compressor.close();
    return out.toByteArray();
  }

  /**
   * De-compresses the given bytes.
   *
   * @param bytes The bytes to de-compress
   *
   * @return The de-compressed string
   * @throws IOException General stream errors
   */
  public static byte[] decompress(byte[] bytes) throws IOException {
    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    GZIPInputStream compressor = new GZIPInputStream(in);

    return IOUtils.readFully(compressor, -1, true);
  }

}
