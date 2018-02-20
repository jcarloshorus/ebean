package org.tests.m2m;

import io.ebean.BaseTestCase;
import io.ebean.Ebean;
import org.ebeantest.LoggedSqlCollector;
import org.junit.Test;
import org.tests.model.basic.MnocRole;
import org.tests.model.basic.MnocUser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class TestM2MDeleteNoCascade extends BaseTestCase {

  @Test
  public void test() {

    MnocRole r0 = new MnocRole("r0");
    MnocRole r1 = new MnocRole("r0");

    Ebean.save(r0);
    Ebean.save(r1);

    MnocUser u0 = new MnocUser("usr0");
    u0.addValidRole(r0);
    u0.addValidRole(r1);

    Ebean.save(u0);

    MnocUser loadedUser = Ebean.find(MnocUser.class, u0.getUserId());
    List<MnocRole> validRoles = loadedUser.getValidRoles();

    assertEquals(2, validRoles.size());

    LoggedSqlCollector.start();
    // don't fail the delete but automatically delete from the intersection
    // table ... such that we don't force developers to fetch and clear the roles
    Ebean.delete(u0);

    List<String> sql = LoggedSqlCollector.stop();
    assertThat(sql).hasSize(2);
    assertThat(sql.get(0)).contains("delete from mnoc_user_mnoc_role where mnoc_user_user_id = ?");
    assertThat(sql.get(1)).contains("delete from mnoc_user where user_id=? and version=?");
  }
}
