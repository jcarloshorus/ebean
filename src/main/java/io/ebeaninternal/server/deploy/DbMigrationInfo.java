package io.ebeaninternal.server.deploy;

import io.ebean.annotation.Platform;
import io.ebeaninternal.dbmigration.migration.Ddl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class to hold the DDL-migration information that is needed to do correct alters.
 *
 * @author Roland Praml, FOCONIS AG
 */
public class DbMigrationInfo {

  private final List<Ddl> preAdd;
  private final List<Ddl> postAdd;
  private final List<Ddl> preAlter;
  private final List<Ddl> postAlter;
  private final List<Platform> platforms;

  public DbMigrationInfo(String[] preAdd, String[] postAdd, String[] preAlter, String[] postAlter, Platform[] platforms) {
    this.preAdd = toDdl(preAdd);
    this.postAdd = toDdl(postAdd);
    this.preAlter = toDdl(preAlter);
    this.postAlter = toDdl(postAlter);
    this.platforms = toList(platforms);
  }

  private List<Ddl> toDdl(String[] scripts) {
    if (scripts.length == 0) {
      return Collections.emptyList();
    } else {
      List<Ddl> ddl = new ArrayList<>(scripts.length);
      for (String script : scripts) {
        Ddl e = new Ddl();
        e.setContent(script);
        ddl.add(e);
      }
      return ddl;
    }
  }

  private <T> List<T> toList(T[] scripts) {
    if (scripts.length == 0) {
      return Collections.emptyList();
    } else {
      return Collections.unmodifiableList(Arrays.asList(scripts));
    }
  }

  public List<Ddl> getPreAdd() {
    return preAdd;
  }

  public List<Ddl> getPostAdd() {
    return postAdd;
  }

  public List<Ddl> getPreAlter() {
    return preAlter;
  }

  public List<Ddl> getPostAlter() {
    return postAlter;
  }

  public List<Platform> getPlatforms() {
    return platforms;
  }

  public String joinPlatforms() {
    if (platforms.isEmpty()) {
      return null;
    } else {
      StringBuilder sb = new StringBuilder();
      for (Platform p : platforms) {
        if (sb.length() > 0) {
          sb.append(',');
        }
        sb.append(p.name().toLowerCase());
      }
      return sb.toString();
    }
  }


}
