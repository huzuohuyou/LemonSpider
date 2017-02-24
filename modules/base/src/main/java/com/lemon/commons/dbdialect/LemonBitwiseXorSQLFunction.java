package com.lemon.commons.dbdialect;

import java.util.List;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

public class LemonBitwiseXorSQLFunction extends StandardSQLFunction {
	public LemonBitwiseXorSQLFunction(String name) {
		super(name, org.hibernate.type.IntegerType.INSTANCE);
	}

	public LemonBitwiseXorSQLFunction(String name, Type typeValue) {
		super(name, typeValue);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String render(Type type, List args, SessionFactoryImplementor arg2) {
		if (args.size() != 2) {
			throw new IllegalArgumentException("the function must be passed 2 arguments");
		}

		if (type instanceof org.hibernate.type.IntegerType || type instanceof org.hibernate.type.BigIntegerType || type instanceof org.hibernate.type.ShortType) {
			StringBuffer buffer = new StringBuffer(" ");
			buffer.append(args.get(0)).append(" # ").append(args.get(1)).append(" ");
			return buffer.toString();
		} else {
			throw new IllegalArgumentException("the operands must be type int2/int4/int8 !");
		}
	}
}
