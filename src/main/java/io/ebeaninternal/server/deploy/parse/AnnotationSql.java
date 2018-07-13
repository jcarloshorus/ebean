package io.ebeaninternal.server.deploy.parse;

import io.ebean.annotation.Sql;
import io.ebean.util.AnnotationUtil;
import io.ebeaninternal.server.deploy.BeanDescriptor;
import io.ebeaninternal.server.deploy.BeanDescriptorManager;

/**
 * Read the class level deployment annotations.
 */
class AnnotationSql extends AnnotationParser {

  AnnotationSql(DeployBeanInfo<?> info, ReadAnnotationConfig readConfig, BeanDescriptorManager factory) {
    super(info, readConfig, factory);
  }

  @Override
  public void parse() {
    Class<?> cls = descriptor.getBeanType();
    Sql sql = AnnotationUtil.findAnnotationRecursive(cls, Sql.class);
    if (sql != null) {
      descriptor.setEntityType(BeanDescriptor.EntityType.SQL);
    }
  }

}
