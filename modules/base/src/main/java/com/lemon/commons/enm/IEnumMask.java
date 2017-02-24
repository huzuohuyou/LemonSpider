package com.lemon.commons.enm;
/** 
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月14日 上午11:20:26
 * 
 */
public interface IEnumMask extends IEnum {
	//询问bitMask的枚举对应的位是否为1
	boolean isIncludeBit(int bitMask);
	
	//将bitMask的枚举对应的bit位置1
	int addMaskBit(int bitMask);
	
	//将bitMask的枚举对应的bit位清0
	int clearMaskBit(int bitMask);
}
