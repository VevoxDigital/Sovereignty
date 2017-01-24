package io.vevox.mc.sovereignty.event;

/**
 * @author Matthew Struble
 */
public class EventException extends RuntimeException {

  public EventException(Exception cause) {
    super(cause);
  }

}
