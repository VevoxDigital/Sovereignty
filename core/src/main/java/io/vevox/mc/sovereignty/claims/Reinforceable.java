package io.vevox.mc.sovereignty.claims;

import java.util.concurrent.TimeUnit;

/**
 * Any object that can be reinforced and has a vulnerability window
 *
 * @author Matthew Struble
 */
public interface Reinforceable {

  enum State {

    /**
     * The object is completely secure and cannot be attacked
     */
    SECURE,

    /**
     * The object is vulnerable and open to attack, but will reinforce afterward.
     */
    VULNERABLE,

    /**
     * The object is invulnerable and will being repairing soon.
     */
    REINFORCED,

    /**
     * The object is repairing and has become vulnerable.
     * If attacked, it may enter another reinforcement cycle or be destroyed.
     */
    REPAIRING;

    /**
     * Whether or not the object is vulnerable in this state
     *
     * @return True if vulnerable, false if not.
     */
    boolean vulnerable() {
      return this == VULNERABLE || this == REPAIRING;
    }

    String display() {
      switch (this) {
        case SECURE:
          return "Secure";
        case VULNERABLE:
          return "Vulnerable";
        case REINFORCED:
          return "Reinforced";
        case REPAIRING:
          return "Repairing";
      }
      return null;
    }

  }

  /**
   * The current {@link State state} of this reinforceable.
   *
   * @return The state
   */
  State reinforcementState();

  /**
   * Ticks until the current state changes.
   * If negative, the state is paused.
   *
   * @return Ticks
   */
  long reinforcementTimer();

  /**
   * Whether or not the object is being captured
   * @return True if being captured, false if not.
   */
  boolean captured();

  /**
   * Gets the displayed timer for the current state.
   * @return The displayed timer.
   */
  default String reinforcementTimerDisplay() {
    if (reinforcementTimer() < 0)
      return "Paused...";

    StringBuilder builder = new StringBuilder();

    long seconds = reinforcementTimer() / 20;
    int days = (int) TimeUnit.SECONDS.toDays(seconds);
    long hours = TimeUnit.SECONDS.toHours(seconds) - (days * 24),
        minutes = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
    seconds = seconds - (TimeUnit.SECONDS.toMinutes(seconds) * 60);

    if (days > 0) builder.append(days).append("d ");
    if (days > 0 && hours > 0) builder.append(hours).append("h ");
    if (days > 0 && hours > 0 && minutes > 0) builder.append(minutes).append("m ");
    return builder.append(seconds).append('s').toString();
  }

  /**
   * Gets the displayed message for the current state.
   * @return The displayed message
   */
  default String reinforcementDisplay() {
    return reinforcementState().display() + ": " + reinforcementTimerDisplay();
  }

}
