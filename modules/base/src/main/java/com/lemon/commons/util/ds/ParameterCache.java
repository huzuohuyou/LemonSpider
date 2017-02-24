package com.lemon.commons.util.ds;
//package com.lemon.util.ds;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.lemon.core.WoshiConstants;
//import com.woshi.ds.po.WoshiParameters;
//
//import edu.ustc.util.MyLog;
//
//public class ParameterCache extends BaseCache {
//	private static MyLog log = MyLog.getLogger(ParameterCache.class);
//
//	private static final String FIND_ALL_PARAMETERS = "from WoshiParameters t ";
//
//	//object[0]: woshiparemeter object
//	//object[1]: indicate whether dirty?modified? init is false.
//	private Map<String, Object[]> parameterMap = null;
//	private ParameterCache(){
//		super(WoshiConstants.CACHE_INVALIDATE_TIME_LONG);
//		reload();
//	}
//
//	private static ParameterCache _this = null; //lazy init
//	public static ParameterCache getIns(){
//		if( _this == null
//			|| _this.isInvalidate() ){
//			_this = new ParameterCache();
//		}
//		return _this;
//	}
//
//	public String getParameter(String code) {
//		return ((WoshiParameters)(parameterMap.get(code)[0])).getValue();
//	}
//
//	@Override
//	protected synchronized void reload() {
//		parameterMap = new ConcurrentHashMap<String, Object[]>();
//
//		Session session = null;
//		try{
//			session = DatabaseManager.getSession();
//			Query query = session.createQuery(FIND_ALL_PARAMETERS);
//			@SuppressWarnings("unchecked")
//			List<WoshiParameters> queryParameterList = (List<WoshiParameters>)query.list();
//			for(WoshiParameters parameter : queryParameterList){
//				parameterMap.put(parameter.getCode(), new Object[]{parameter, false});
//			}
//			log.info("[ParameterCache] Init parameter cache successfully.");
//		}catch(Exception e){
//			log.error("[ParameterCache] Fail to init parameter cache map.", e);
//			//throw new WoshiCommonException("[ParameterCache] Fail to init parameter cache map.", e, "1104");
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//
//		super.reload();
//	}
//
//	public void flushByKey(String code, String newValue) {
//		Object[] v = parameterMap.get(code);
//		if(v==null || code==null) {
//			return;
//		}
//
//		((WoshiParameters)v[0]).setValue(newValue);
//
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = DatabaseManager.getSession();
//			tx = session.beginTransaction();
//			session.update(v[0]);
//			tx.commit();
//		} catch (Exception e) {
//			log.error("flush WoshiParameters failed!!!", e);
//			if(tx != null)
//				tx.rollback();
//		} finally {
//			if(session != null)
//				session.close();
//		}
//	}
//
//	public void flushAllDirty2db() {
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = DatabaseManager.getSession();
//			tx = session.beginTransaction();
//
//			boolean hasOne = false;
//			Iterator<Object[]> it = parameterMap.values().iterator();
//			while(it.hasNext()) {
//				Object[] v = it.next();
//				if(((Boolean)v[1]).booleanValue()) {
//					session.update(v[0]);
//					hasOne = true;
//				}
//			}
//
//			if(hasOne) {
//				tx.commit();
//			}
//		} catch (Exception e) {
//			log.error("flush WoshiParameters failed!!!", e);
//			if(tx != null)
//				tx.rollback();
//		} finally {
//			if(session != null)
//				session.close();
//		}
//	}
//
//}
