package com.avaje.tests.query;

import com.avaje.ebean.BaseTestCase;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.tests.model.basic.MyAdHoc;
import com.avaje.tests.model.basic.ResetBasicData;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class TestMyAdHocSqlSelect extends BaseTestCase {

  @Test
  public void test() {

    ResetBasicData.reset();

    String sql = "select order_id, count(*) as detailCount from o_order_detail group by order_id";

    RawSql rawSql = RawSqlBuilder
      .parse(sql)
      .columnMapping("order_id", "order.id")
      .create();

    Query<MyAdHoc> query = Ebean.find(MyAdHoc.class)
      .setRawSql(rawSql)
      .where().gt("order_id", 0)
      .having().gt("detailCount", 0)
      .query();

    assertNotNull(query.findList());
    assertThat(query.getGeneratedSql()).contains(" group by order_id  having count(*) > ?");
  }

  @Test
  public void test_when_explicitColumnMapping() {

    ResetBasicData.reset();

    String sql = "select order_id, count(*) as detail_count from o_order_detail group by order_id";

    RawSql rawSql = RawSqlBuilder
      .parse(sql)
      .columnMapping("order_id", "order.id")
      .columnMapping("detail_count", "detailCount")
      .create();

    Query<MyAdHoc> query = Ebean.find(MyAdHoc.class)
      .setRawSql(rawSql)
      .where().gt("order_id", 0)
      .having().gt("detailCount", 0)
      .query();

    assertNotNull(query.findList());
    assertThat(query.getGeneratedSql()).contains(" group by order_id  having count(*) > ?");
  }


}
