package io.vevox.mc.sovereignty.sov;

import io.vevox.mc.sovereignty.SovUtils;
import io.vevox.mc.sovereignty.UnknownMappingFormatException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

  private Sovereignty(UUID uuid) {
    this.uuid = uuid;
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
