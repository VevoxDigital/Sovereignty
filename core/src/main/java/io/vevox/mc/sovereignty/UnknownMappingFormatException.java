package io.vevox.mc.sovereignty;

/**
 * An exception thrown when an unknown mapping format is encountered.
 *
 * @author Matthew Struble
 */
public class UnknownMappingFormatException extends RuntimeException {

  public UnknownMappingFormatException(int format) {
    super("Unknown mapping format: " + format);
  }

}
