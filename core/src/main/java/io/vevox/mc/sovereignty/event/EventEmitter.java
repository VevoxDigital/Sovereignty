package io.vevox.mc.sovereignty.event;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author Matthew Struble
 */
public interface EventEmitter {

  EventRegistry getRegistry();

  default void on(@NotNull String event, @NotNull Consumer<EventData> handler) {
    getRegistry().register(event, false, handler);
  }

  default void once(@NotNull String event, @NotNull Consumer<EventData> handler) {
    getRegistry().register(event, true, handler);
  }

  default void emit(@NotNull String event, @NotNull EventData data) {
    getRegistry().emit(event, data);
  }

}
