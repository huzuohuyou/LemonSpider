package com.lemon.commons.dbdialect;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

import java.util.List;

/**
 * Created by ywp on 16/4/13.
 */
public class LemonRegExpReplaceSQLFunction extends StandardSQLFunction {
    public LemonRegExpReplaceSQLFunction(String name) {
        super(name,org.hibernate.type.IntegerType.INSTANCE);
    }


    @SuppressWarnings("rawtypes")
    @Override
    public String render(Type type, List args, SessionFactoryImplementor arg2) {
        if (args.size() != 3) {
            throw new IllegalArgumentException("the function must be passed 3 arguments, such as a%b");
        }

        String format = "regexp_replace(%s,%s,%s)";
        return String.format(format,args.get(0),args.get(1),args.get(2));
    }
}
