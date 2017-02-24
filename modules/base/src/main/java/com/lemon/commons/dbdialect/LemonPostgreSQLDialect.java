package com.lemon.commons.dbdialect;

import org.hibernate.dialect.PostgreSQL9Dialect;

import com.lemon.commons.enm.EnumSqlDialect;


/**
 *
操作符 	描述 	例子 	结果
+ 	加 	2 + 3 	5
- 	减 	2 - 3 	-1
 * 	乘 	2 * 3 	6
/ 	除 	4 / 2 	2
% 	模 	5 % 4 	1
^ 	幂 	2.0 ^ 3.0 	8
|/ 	平方根 	|/ 25.0 	5
||/ 	立方根 	||/ 27.0 	3
! 	阶乘 	5 ! 	120
!! 	阶乘 	!! 5 	120
@ 	绝对值 	@ -5.0 	5
& 	按位AND 	91 & 15 	11
| 	按位OR 	32 | 3 	35
# 	按位XOR 	17 # 5 	20
~ 	按位NOT 	~1 	-2
<< 	按位左移 	1 << 4 	16
>> 	按位右移 	8 >> 2 	2
 *
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月15日 下午3:36:17
 *
 */
public class LemonPostgreSQLDialect extends PostgreSQL9Dialect {

	public LemonPostgreSQLDialect() {
		super();
		//函数名必须是小写，试验大写出错
		//SQLFunctionTemplate函数第一个参数是函数的输出类型，varchar2对应new StringType()    number对应new IntegerType()
		//?1代表第一个参数,?2代表第二个参数     这是数据库wx_f_get_partystr函数只需要一个参数，所以写成wx_f_get_partystr(?1)
		registerFunction(EnumSqlDialect.Bit_And.getValue(),
				new LemonBitwiseAndSQLFunction(EnumSqlDialect.Bit_And.getValue()));

		registerFunction(EnumSqlDialect.Bit_Or.getValue(),
				new LemonBitwiseOrSQLFunction(EnumSqlDialect.Bit_Or.getValue()));

		registerFunction(EnumSqlDialect.Bit_Xor.getValue(),
				new LemonBitwiseXorSQLFunction(EnumSqlDialect.Bit_Xor.getValue()));

		registerFunction(EnumSqlDialect.Limit.getValue(),
				new LemonLimitSQLFunction(EnumSqlDialect.Limit.getValue()));

		registerFunction(EnumSqlDialect.RegReplace.getValue(),new LemonRegExpReplaceSQLFunction(EnumSqlDialect.RegReplace.getValue()));
	}
}
