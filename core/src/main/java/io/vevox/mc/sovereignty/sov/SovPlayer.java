package io.vevox.mc.sovereignty.sov;

import io.vevox.mc.sovereignty.SovConfig;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * A player registered under a {@link Sovereignty}
 *
 * @author Matthew Struble
 */
public class SovPlayer implements Serializable {

  public final UUID uuid;
  public final Sovereignty sovereignty;

  @NotNull
  String name;

  SovPlayer(UUID uuid, Sovereignty sovereignty) {
    this.uuid = uuid;
    this.sovereignty = sovereignty;

    name = uuid.toString();
  }

  /**
   * Gets the name of this player.
   *
   * @return The name
   */
  @NotNull
  public final String name() {
    return name;
  }

  // I have no idea why IntelliJ keeps throwing ConstantConditions here. There *is* a NPE check.

  /**
   * Triggers an update of this player's name using {@link SovConfig#playerNameFunction}. If the function is
   * null, this player's {@link #uuid} is used.
   */
  @SuppressWarnings("ConstantConditions")
  public void updateName() {
    name = SovConfig.playerNameFunction != null ? SovConfig.playerNameFunction.apply(this) : uuid.toString();
  }

  /**
   * Determines if the player is online using {@link SovConfig#playerOnlinePredicate}. If the predicate
   * is null, <code>false</code> is returned.
   *
   * @return Whether or not the player is online.
   */
  @SuppressWarnings("ConstantConditions")
  public boolean online() {
    return SovConfig.playerOnlinePredicate != null && SovConfig.playerOnlinePredicate.test(this);
  }


  @Override
  public boolean equals(@NotNull Object o) {
    return o instanceof SovPlayer && ((SovPlayer) o).uuid.equals(uuid);
  }

}
