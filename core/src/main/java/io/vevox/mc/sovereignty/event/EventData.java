package io.vevox.mc.sovereignty.event;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A data carrier class for events emitted by an {@link EventEmitter}
 * @author Matthew Struble
 */
public class EventData {

  /**
   * An {@link java.util.Collections.UnmodifiableMap} of the data passed to the event.
   */
  public final @NotNull Map<String, Object> data;

  /**
   * Creates a new event data package with no data.
   */
  public EventData() {
    data = Collections.unmodifiableMap(new HashMap<>());
  }

  /**
   * Creates a new event data package with the given data.
   *
   * @param data The data
   */
  public EventData(@NotNull Map<String, Object> data) {
    this.data = Collections.unmodifiableMap(new LinkedHashMap<>(data));
  }

}
