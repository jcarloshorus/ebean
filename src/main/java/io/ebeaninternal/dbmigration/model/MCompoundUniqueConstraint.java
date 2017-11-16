package io.ebeaninternal.dbmigration.model;

import io.ebeaninternal.dbmigration.migration.CreateIndex;
import io.ebeaninternal.dbmigration.migration.DropIndex;
import io.ebeaninternal.dbmigration.migration.UniqueConstraint;

/**
 * A unique constraint for multiple columns.
 * <p>
 * Note that unique constraint on a single column is instead
 * a boolean flag on the associated MColumn.
 * </p>
 */
public class MCompoundUniqueConstraint {

  private final String name;

  /**
   * Flag if true indicates this was specifically created for a OneToOne mapping.
   */
  private final boolean oneToOne;

  /**
   * The columns combined to be unique.
   */
  private final String[] columns;

  MCompoundUniqueConstraint(String[] columns, boolean oneToOne, String name) {
    this.name = name;
    this.columns = columns;
    this.oneToOne = oneToOne;
  }

  MCompoundUniqueConstraint(CreateIndex createIndex) {
    this.name = createIndex.getIndexName();
    this.oneToOne = false;
    this.columns = createIndex.getColumns().split(",");
  }

  MCompoundUniqueConstraint(UniqueConstraint uniqueConstraint) {
    this.name = uniqueConstraint.getName();
    this.oneToOne = false;
    this.columns = uniqueConstraint.getColumnNames().split(",");
  }

  /**
   * Return the columns for this unique constraint.
   */
  public String[] getColumns() {
    return columns;
  }

  /**
   * Return true if this unique constraint is specifically for OneToOne mapping.
   */
  public boolean isOneToOne() {
    return oneToOne;
  }

  /**
   * Return the constraint name.
   */
  public String getName() {
    return name;
  }

  /**
   * Return a CreateIndex migration for this index.
   */
  public CreateIndex createIndex(String tableName) {
    CreateIndex create = new CreateIndex();
    create.setIndexName(name);
    create.setTableName(tableName);
    create.setColumns(join());
    return create;
  }

  /**
   * Create a DropIndex migration for this index.
   */
  public DropIndex dropIndex(String tableName) {
    DropIndex dropIndex = new DropIndex();
    dropIndex.setIndexName(name);
    dropIndex.setTableName(tableName);
    return dropIndex;
  }

  private String join() {
    StringBuilder sb = new StringBuilder(50);
    for (int i = 0; i < columns.length; i++) {
      if (i > 0) {
        sb.append(",");
      }
      sb.append(columns[i]);
    }
    return sb.toString();
  }

  /**
   * Return true if the attributes are different.
   */
  boolean isDiff(MCompoundUniqueConstraint newOne) {
    return oneToOne != newOne.oneToOne || !join().equals(newOne.join());
  }
}
