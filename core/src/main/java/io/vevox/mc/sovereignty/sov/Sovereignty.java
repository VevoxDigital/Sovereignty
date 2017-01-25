package io.vevox.mc.sovereignty.sov;

import io.vevox.mc.sovereignty.SovUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("unused WeakerAccess")
public class Sovereignty implements Serializable {

  /**
   * De-serializes this Sovereignty from the given array of compressed binary data.
   *
   * @param data The data to de-serialize from
   *
   * @return The de-serialized Sovereignty
   * @throws IOException            Stream errors
   * @throws ClassNotFoundException If the mapped class does not exist
   */
  @SuppressWarnings("unchecked")
  public static Sovereignty deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(SovUtils.decompress(data)));
    return (Sovereignty) in.readObject();
  }

  public static Sovereignty create() {
    return new Sovereignty(UUID.randomUUID());
  }


  /**
   * This Sovereignty's {@link UUID}.
   */
  public final UUID uuid;

  private final Set<SovPlayer> players;

  private Sovereignty(UUID uuid) {
    this.uuid = uuid;
    players = new HashSet<>();
  }

  /**
   * Gets an {@link Optional} which would contain the {@link SovPlayer} if one by the
   * given uuid is present.
   *
   * @param uuid The uuid to search for
   *
   * @return The optional player
   */
  @NotNull
  public Optional<SovPlayer> getPlayer(@NotNull UUID uuid) {
    return players.stream().filter(p -> p.uuid.equals(uuid)).findAny();
  }

  /**
   * Creates a new {@link SovPlayer} from the given uuid and adds it to this {@link Sovereignty}
   *
   * @param uuid The uuid to create with
   *
   * @return The player that was added, or the existing one if already present
   */
  @NotNull
  public SovPlayer addPlayer(@NotNull UUID uuid) {
    if (getPlayer(uuid).isPresent()) return getPlayer(uuid).get();

    SovPlayer player = new SovPlayer(uuid, this);
    player.updateName();
    players.add(player);
    return player;
  }


  /**
   * Serializes this Sovereignty to a compressed binary array.
   *
   * @return The compressed and serialized data
   * @throws IOException Stream errors
   */
  public byte[] serialize() throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream o = new ObjectOutputStream(out);
    o.writeObject(this);
    return SovUtils.compress(out.toByteArray());
  }

}
