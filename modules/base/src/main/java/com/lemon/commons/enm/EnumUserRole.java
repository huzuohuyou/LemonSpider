package com.lemon.commons.enm;



/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumUserRole implements IEnum {
	//------------------fields -------------------------
	User_Administrator	(99, "管理员"),
	User_Teacher		(1, "老师"),
	User_Student		(2, "学生"),
	User_Student_Group	(3, "学生班级组"),
	User_Orginization	(4, "教师管理层"),
	User_Parent			(5, "家长"),
	User_Normal          (98,"普通用户");

	//------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumUserRole(int code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}

	//------------------static methods -------------------------
	static {
		for(EnumUserRole e : EnumUserRole.values()) {
			EnumCenter.registe(EnumUserRole.class, e);
		}
	}

    public static IEnum explain(int code) {
        return EnumCenter.explain(EnumUserRole.class, code);
    }

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumUserRole.class, code);
	}
}