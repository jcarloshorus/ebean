package io.ebeaninternal.dbmigration.model;

import io.ebeaninternal.dbmigration.migration.CreateIndex;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MIndexTest {

  private MIndex index = new MIndex("ix_a", "tab", new String[]{"a", "b"});

  @Test
  public void createIndex() throws Exception {

    CreateIndex createIndex = new CreateIndex();
    createIndex.setIndexName("ix_a");
    createIndex.setTableName("foo");
    createIndex.setColumns("col1");

    MIndex index = new MIndex(createIndex);

    CreateIndex index1 = index.createIndex();

    assertEquals(createIndex.getIndexName(), index1.getIndexName());
    assertEquals(createIndex.getTableName(), index1.getTableName());
    assertEquals(createIndex.getColumns(), index1.getColumns());
    assertEquals(createIndex.isUnique(), index1.isUnique());
  }


  @Test
  public void getColumns() {

    MIndex index = new MIndex("ix_a", "tab", new String[]{"a", "b"});

    List<String> columns = index.getColumns();

    assertEquals(2, columns.size());
    assertEquals("a", columns.get(0));
    assertEquals("b", columns.get(1));
  }

  @Test
  public void change_when_match() {

    MIndex index0 = new MIndex("ix_a", "tab", new String[]{"a", "b"});
    assertFalse(index0.changed(index));
  }

  @Test
  public void change_when_nameDiff() {

    MIndex index0 = new MIndex("ix_b", "tab", new String[]{"a", "b"});
    assertTrue(index0.changed(index));
  }

  @Test
  public void change_when_tableDiff() {

    MIndex index0 = new MIndex("ix_a", "tab2", new String[]{"a", "b"});
    assertTrue(index0.changed(index));
  }

  @Test
  public void change_when_columnOrderDiff() {

    MIndex index0 = new MIndex("ix_a", "tab", new String[]{"b", "a"});
    assertTrue(index0.changed(index));
  }

  @Test
  public void change_when_columnMissingDiff() {

    MIndex index0 = new MIndex("ix_a", "tab", new String[]{"a"});
    assertTrue(index0.changed(index));
  }

  @Test
  public void change_when_columnAddedDiff() {

    MIndex index0 = new MIndex("ix_a", "tab", new String[]{"a", "b", "c"});
    assertTrue(index0.changed(index));
  }

  @Test
  public void change_when_column1NameDiff() {

    MIndex index0 = new MIndex("ix_a", "tab", new String[]{"a", "bs"});
    assertTrue(index0.changed(index));
  }

  @Test
  public void change_when_column0NameDiff() {

    MIndex index0 = new MIndex("ix_a", "tab", new String[]{"a2", "b"});
    assertTrue(index0.changed(index));
  }

}
