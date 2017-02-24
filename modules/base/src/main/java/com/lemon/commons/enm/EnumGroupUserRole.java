package com.lemon.commons.enm;


import java.util.ArrayList;
import java.util.List;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumGroupUserRole implements IEnum {
	//------------------fields -------------------------
	Admin     (99,"超级管理员",9),
	Creator   (3, "创建者",9),
	Assist    (5, "助教老师",8),
	Tutor     (4, "任课老师",6),
	Vip       (6, "付费Vip",3),
	Tea       (1, "旁听老师",1),
	Stu       (2, "学生",1);

	public static final List<Integer> TeacherList = new ArrayList<>();
	public static final List<Integer> StudentList = new ArrayList<>();
	static{
		TeacherList.add(Creator.getCode());
		TeacherList.add(Assist.getCode());
		TeacherList.add(Tutor.getCode());

		StudentList.add(Vip.getCode());
		StudentList.add(Tea.getCode());
		StudentList.add(Stu.getCode());
	}

	//------------------instance methods -------------------------
	private int code;
	private String value;
	private int level;
	private EnumGroupUserRole(int code, String value,Integer level) {
		this.code = code;
		this.value = value;

		this.level = level;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}

	public int getLevel(){return level;}


	//------------------static methods -------------------------
	static {
		for(EnumGroupUserRole e : EnumGroupUserRole.values()) {
			EnumCenter.registe(EnumGroupUserRole.class, e);
		}
	}

	public static final List<Integer> TEACHER_VALID = new ArrayList<>();
	public static final List<Integer> Agency_Teacher_VALID = new ArrayList<>();
	public static  final List<Integer> STUDENT_VALID = new ArrayList<>();

	public static  final List<Integer> ALL_VALID = new ArrayList<>();

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumGroupUserRole.class, code);
	}

	public static EnumGroupUserRole explain(int code) {
		return (EnumGroupUserRole)EnumCenter.explain(EnumGroupUserRole.class,code);
	}

	static{
		TEACHER_VALID.add(Creator.getCode());
		TEACHER_VALID.add(Tutor.getCode());
		TEACHER_VALID.add(Admin.getCode());
		STUDENT_VALID.add(Stu.getCode());
		STUDENT_VALID.add(Tea.getCode());

		ALL_VALID.add(Creator.getCode());
		ALL_VALID.add(Tutor.getCode());
		ALL_VALID.add(Admin.getCode());
		ALL_VALID.add(Stu.getCode());
		ALL_VALID.add(Tea.getCode());
		Agency_Teacher_VALID.add(Tutor.getCode());
	}
}