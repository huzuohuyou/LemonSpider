package com.lemon.commons.enm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public enum EnumStatusCode implements IEnum{

    Publish         (0,"发布版本"),

	Normal 			(200, "正常"),
	Need_Auth		(201, "需要验证"),
	Not_Publish     (202,"未发布"),
	Hidden  		(203, "隐藏"),	//

	Finish			(206, "已结束"),
	Delete			(303, "删除"),
	Default     	(209,"默认状态"),//活动
	UnCheck     	(211,"未审核"),//活动
	Posted      	(210,"审核通过"),//活动
	Rejected    	(216,"被驳回"),//活动
	UnUse       	(212,"未使用"),//活动
	Locked      	(213,"被锁定"),
	Used        	(214,"已使用"),
	Ready       	(215,"筹备中"),
	NoProcessing	(216,"未处理"),
	Processed 		(217,"已处理"),
	School_Auth 	(220,"已认证"),//收费学校认证
	Order_Normal    (222,"已下单"),
	Order_Paying    (221,"正在支付"),
	Order_PaySuccess (223,"支付成功"),
	Order_PayFailed  (224,"支付失败"),
	Order_Refunding  (225,"正在退款"),
	Order_Refunded   (226,"已退款")
	;



	private int ch;
	private String value;
	private EnumStatusCode(int ch, String value) {
		this.ch = ch;
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}

	//------------------static methods -------------------------
	static {
		for(EnumStatusCode e : EnumStatusCode.values()) {
			EnumCenter.registe(EnumStatusCode.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumStatusCode.class, code);
	}

    public static EnumStatusCode explain(int code) {
        IEnum ie = EnumCenter.explain(EnumStatusCode.class, code);
        if(ie == null) {
            return null;
        } else {
            return (EnumStatusCode)ie;
        }
    }

	public static final List<Integer> COMMON_STATUS_ALL = new ArrayList<Integer>();
	public static final List<Integer> COMMON_STATUS_VALID = new ArrayList<Integer>();
	public static final List<Integer> COMMON_STATUS_SHOW = new ArrayList<Integer>();
	public static final List<Integer> COMMON_STATUS_HIDE = new ArrayList<Integer>();


	public static final List<Integer> CLAZZ_STATUS_VALID = new ArrayList<Integer>();

	public static final Map<Integer, String> CODE_EXPLAIN = new HashMap<Integer, String>();

	static{
		COMMON_STATUS_ALL.add(EnumStatusCode.Normal.getCode());
		COMMON_STATUS_ALL.add(EnumStatusCode.Hidden.getCode());
		COMMON_STATUS_ALL.add(EnumStatusCode.Need_Auth.getCode());
		COMMON_STATUS_ALL.add(EnumStatusCode.Delete.getCode());

		COMMON_STATUS_VALID.add(EnumStatusCode.Normal.getCode());
		COMMON_STATUS_VALID.add(EnumStatusCode.Need_Auth.getCode());

		COMMON_STATUS_SHOW.add(EnumStatusCode.Normal.getCode());

		CLAZZ_STATUS_VALID.add(EnumStatusCode.Normal.getCode());
		CLAZZ_STATUS_VALID.add(EnumStatusCode.Hidden.getCode());
		CLAZZ_STATUS_VALID.add(EnumStatusCode.Need_Auth.getCode());
	}
}