package util;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    // 将数据库中的 tinyint 映射为 Integer
    public MyJavaTypeResolver() {
        super();
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
