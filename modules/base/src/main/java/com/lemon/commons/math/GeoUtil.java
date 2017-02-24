package com.lemon.commons.math;

public class GeoUtil {

	/**
	 * 地球半径：6378.137KM
	 */
	private static float EARTH_RADIUS = 6378f;

	/**
	 * 根据经纬度和距离返回一个矩形范围
	 * 
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 * @param distance
	 *            距离(单位为米)
	 * @return [lat1, lat2, lng1, lng2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
	 */
	public static float[] getRectangle(float lng, float lat, float distance) {
		float delta = 111000;
		if (lng != 0 && lat != 0) {
			// 不要求精度的情况下，用float类型，减少计算量
			float lng1 = (float) (lng - distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta));
			float lng2 = (float) (lng + distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta));
			float lat1 = lat - (distance / delta);
			float lat2 = lat + (distance / delta);
			return new float[]{lat1, lat2, lng1, lng2};
		} else {
			// TODO ZHCH 等于0时的计算公式
			float lng1 = lng - distance / delta;
			float lng2 = lng + distance / delta;
			float lat1 = lat - (distance / delta);
			float lat2 = lat + (distance / delta);
			return new float[]{lat1, lat2, lng1, lng2};
		}
	}

	/**
	 * 得到两点间的距离 米
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double getDistanceOfMeter(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10;
		return s;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

}
