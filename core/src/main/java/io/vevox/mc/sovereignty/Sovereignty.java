package io.vevox.mc.sovereignty;

import sun.misc.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("unused WeakerAccess")
public class Sovereignty {

  /**
   * The current mapping format.
   */
  public static final int MAPPING_FORMAT = 1;

  /**
   * De-serializes this Sovereignty from the given array of compressed binary data.
   *
   * @param data The data to de-serialize from
   *
   * @return The de-serialized and {@link #unmap(Map) un-mapped} sovereignty
   * @throws IOException            Stream errors
   * @throws ClassNotFoundException If the mapped class does not exist
   */
  @SuppressWarnings("unchecked")
  public Sovereignty deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(SovUtils.decompress(data)));
    Map<String, Object> map = (Map<String, Object>) in.readObject();
    in.close();
    return unmap(map);
  }

  /**
   * Un-maps a Sovereignty from the given map
   *
   * @param map The map to un-map from
   *
   * @return The Sovereignty
   * @throws IllegalArgumentException If the map is not a valid Sovereignty
   */
  public Sovereignty unmap(Map<String, Object> map) throws IllegalArgumentException {
    if (!map.containsKey("format") || !(map.get("format") instanceof Integer))
      throw new IllegalArgumentException("missing or invalid format");

    switch ((int) map.get("format")) {
      case 1:

        if (!map.containsKey("uuid") || !(map.get("uuid") instanceof UUID))
          throw new IllegalArgumentException("invalid or missing UUID");

        return new Sovereignty((UUID) map.get("uuid"));

      default:
        throw new IllegalArgumentException("unknown mapping format: " + map.get("format"));
    }
  }

  public Sovereignty create() {
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
   * Maps this Sovereignty to a {@link Map} of string keys and object data.
   *
   * @return The map
   */
  public Map<String, Object> map() {
    Map<String, Object> map = new HashMap<>();

    map.put("uuid", uuid);
    map.put("format", MAPPING_FORMAT);

    return map;
  }

  /**
   * Serializes this Sovereignty's {@link #map() map} to a compressed binary array.
   *
   * @return The compressed and serialized data
   * @throws IOException Stream errors
   */
  public byte[] serialize() throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream o = new ObjectOutputStream(out);
    o.writeObject(map());
    return out.toByteArray();
  }

}
