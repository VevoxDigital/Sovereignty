package io.vevox.mc.sovereignty.event;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Matthew Struble
 */
public class EventRegistry {

  private final Map<String, Set<Consumer<EventData>>> registry, onceRegistry;

  public EventRegistry() {
    registry = new HashMap<>();
    onceRegistry = new HashMap<>();
  }

  protected void register(@NotNull String name, boolean once, @NotNull Consumer<EventData> consumer) {
    Map<String, Set<Consumer<EventData>>> map = once ? onceRegistry : registry;
    if (!map.containsKey(name)) map.put(name, new HashSet<>());

    map.get(name).add(consumer);
  }

  private void emitFor(@NotNull String name, Map<String, Set<Consumer<EventData>>> map, EventData data) {
    Set<Consumer<EventData>> set = map.get(name);

    if (set == null) return;
    set.forEach(c -> {
      try {
        c.accept(data);
      } catch (Exception e) {
        new EventException(e).printStackTrace();
      }
    });
  }

  protected void emit(@NotNull String name, EventData data) {
    emitFor(name, onceRegistry, data);
    onceRegistry.remove(name);
    emitFor(name, registry, data);
  }

}
