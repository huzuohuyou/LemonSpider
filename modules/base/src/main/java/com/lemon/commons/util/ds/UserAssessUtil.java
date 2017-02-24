package com.lemon.commons.util.ds;
//package com.lemon.util.ds;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//
//
//import com.lemon.core.WoshiConstants;
//import com.lemon.core.exception.WoshiCommonException;
//import com.woshi.ds.po.um.User;
//import com.woshi.ds.po.um.UserAssess;
//import com.woshi.ds.po.um.UserAssessDetail;
//
//import edu.ustc.util.MyLog;
//
//public class UserAssessUtil {
//	private final static MyLog log = MyLog.getLogger(UserAssessUtil.class);
//
//	public static final String IMPROVEMENT = "improvement",
//			   PARENT_EVALUATE = "parent_evaluate",
//			   UNDERSTAND_EVALUATE = "understand_evaluate",
//			   COURSES_TEACHED = "courses_teached",
//			   SHARED_POINTS = "shared_points",
//			   TOTAL_POINTS = "total_points";
//
//	private Map<String, Integer> ASSESS_DICT = null;
//	@SuppressWarnings("unchecked")
//	private UserAssessUtil(){
//		//first time run this method. load the map.
//		ASSESS_DICT = DictCache.getIns().getReDict(DictCache.TYPE_TEACHER_ASSESS);
//
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = DatabaseManager.getSession();
//
//			//next, test whether teacher_assess table empty or incomplete
//			String SQL_TEACHER_NOTIN_ASSESS = "select id from um.user_ where id not in (select distinct tid from um.user_assess) order by id ";
//			SQLQuery sq = session.createSQLQuery(SQL_TEACHER_NOTIN_ASSESS);
//			log.info("sq:   "+sq);
//
//			Iterator<Integer> it2 = sq.list().iterator();
//			 tx = session.beginTransaction();
//			while(it2.hasNext()) {
//				UserAssess ta = new UserAssess(it2.next());
//				ta.setTotalPoints(0);
//				session.save(ta);
//			}
//			tx.commit();
//		} catch (WoshiCommonException e) {
//			log.error(e);
//		}catch (Exception e) {
//			if (tx != null){
//				try{
//					tx.rollback();
//				}catch(Exception e2){
//				}
//			}
//		}
//		finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//	}
//
//	private static UserAssessUtil _assess = null;
//	public static UserAssessUtil getIns(){
//		if(_assess==null){
//			_assess = new UserAssessUtil();
//		}
//		return _assess;
//	}
//
//	/**
//	 * 当发现没有uid对应的数据时，创建一个全为0的记录，更新到数据库的同时，并返回这个对象。
//	 * @param uid
//	 */
//	private UserAssess createInitialReport(Integer uid){
//		UserAssess bt = new UserAssess(uid);
//
//		Session session = null;
//		Transaction tx=null;
//		try {
//			session = DatabaseManager.getSession();
//			 tx = session.beginTransaction();
//			session.save(bt);
//			tx.commit();
//		} catch (WoshiCommonException e) {
//			log.error("[TeacherAssessUtil] teacher:" + uid +" save to db failed.", e);
//		} catch (Exception e) {
//			if(tx!=null)
//			{
//				tx.rollback();
//			}
//			log.error(e);
//		} finally{
//			if(session != null) {
//				session.close();
//			}
//		}
//		return bt;
//	}
//
//	private static final String GET_SUMMARY_REPORT = "from UserAssess t where t.tid=? ";
//	/**
//	 * 根据teacherId得到对应的唯一的Assess向量。
//	 * @param uid
//	 * @return
//	 */
//	public UserAssess getUniqueAssessReport(Integer uid){
//		Session session = null;
//		UserAssess bt = null;
//
//		try{
//			session = DatabaseManager.getSession();
//			Query query = session.createQuery(GET_SUMMARY_REPORT);
//			query.setInteger(0, uid);
//			Object o = query.uniqueResult();
//			if(o!=null){
//				bt = (UserAssess)o;
//			}else{
//				bt = createInitialReport(uid);
//			}
//			log.debug("successful to getUniqueAssessReport for user:" + bt.toString() );
//		}catch(WoshiCommonException e){
//			log.error("Fail to getUniqueAssessReport for user:{}.", uid, e);
//		}catch(Exception e){
//			log.error("Fail to getUniqueAssessReport for user:{}.", uid, e);
//		}finally{
//			if(session != null){
//				session.close();
//			}
//		}
//
//		return bt;
//	}
//
//	/**
//	 * @see com.woshi.ds.po.um.UserAssess
//	 * @param user
//	 * @return
//	 */
//	public String getSummaryAssess(User user) {
//		UserAssess ta = getUniqueAssessReport(user.getId());
//		return ta.prettyDesc(user.getStarLevel());
//	}
//
//	private final static String GET_ASSESSDETAIL_BY_TID = "from UserAssessDetail j where j.tid=? order by timetag asc";
//	@SuppressWarnings("unchecked")
//	public List<UserAssessDetail> getAssessDetailByTid(int teacherId){
//		Session session = null;
//		List<UserAssessDetail> ls = new ArrayList<UserAssessDetail>();
//
//		try {
//			session = DatabaseManager.getSession();
//			Query query = session.createQuery(GET_ASSESSDETAIL_BY_TID);
//			query.setInteger(0, teacherId);
//			ls = query.list();
//		} catch (WoshiCommonException e) {
//			log.error(e);
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//
//		return ls;
//	}
//
//	private final static String GET_ASSESSDETAIL_COUNT_BY_TID = "select count(*) from UserAssessDetail j where j.tid=? ";
//	public int getAssessDetailCountByTid(int teacherId) {
//		Session session = null;
//		int rt = 0;
//
//		try {
//			session = DatabaseManager.getSession();
//			Query q = session.createQuery(GET_ASSESSDETAIL_COUNT_BY_TID);
//			q.setInteger(0, teacherId);
//			Object o = q.uniqueResult();
//
//			if(o != null) {
//				rt = ((Long)o).intValue();
//			}
//		} catch (WoshiCommonException e) {
//			log.error(e);
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//
//		return rt;
//	}
//
//	private static final String QUERY_TEACHERASSESS_BYID = "from UserAssess t where tid=? ";
//	public int updateAssess(int teacherId, int who, String which, int delta, boolean ifAdmin) {
//		//test delta's valid.
//		//log.info("teacherId:"+teacherId);
//		//log.info("who:"+who);
//		//log.info("which:"+which);
//		//log.info("delta:"+delta);
//	//	log.info("ifAdmin:"+ifAdmin);
//
//		if(!ifAdmin){
//			if(delta==1 || delta==2 || delta==3 || delta==4 || delta==5 || TOTAL_POINTS.equals(which)){
//			} else {
//				log.error("[TeacherAssessUtil@updateAssess]you are not allowed to update teacher assess vector as you want!!");
//				return 0;
//			}
//		}
//
//		//add a new record into detail table.
//		UserAssessDetail tad = new UserAssessDetail();
//		tad.setTid(teacherId);
//		tad.setSponsorId(who);
//		tad.setDelta(delta);
//		Integer typ = ASSESS_DICT.get(which);
//		if(typ==null){
//			typ = 0;
//		}
//		tad.setTyp(typ);
//		tad.setTimetag(new java.util.Date());
//
//		Session session = null;
//		int rt = 0;
//		try {
//			session = DatabaseManager.getSession();
//
//			//update TeacherAssess table.
//			Query q = session.createQuery(QUERY_TEACHERASSESS_BYID);
//			q.setInteger(0, teacherId);
//			List<UserAssess> a=q.list();
//			if(a.size()==0)
//			{
//				new UserAssessUtil();
//			}
//			UserAssess ta = (UserAssess)q.uniqueResult();
//			log.info("ta"+ta);
//			if(IMPROVEMENT.equals(which)){
//				ta.setImprovement(ta.getImprovement()+delta);
//				rt = ta.getImprovement();
//
//				short newRank = updateAssessRank(WoshiConstants.UA_TEACHER_MAX_V2_POINTS, rt);
//				ta.setV2Rank(newRank);
//
//			} else if(PARENT_EVALUATE.equals(which)) {
//				ta.setParentEvaluate(ta.getParentEvaluate()+delta-2);
//				rt = ta.getParentEvaluate();
//
//				short newRank = updateAssessRank(WoshiConstants.UA_TEACHER_MAX_V1_POINTS, rt);
//				ta.setV1Rank(newRank);
//
//			} else if(UNDERSTAND_EVALUATE.equals(which)) {
//				ta.setUnderstandEvaluate(ta.getUnderstandEvaluate()+delta-2);
//				rt = ta.getUnderstandEvaluate();
//
//				short newRank = updateAssessRank(WoshiConstants.UA_TEACHER_MAX_V3_POINTS, rt);
//				ta.setV3Rank(newRank);
//
//			} else if(COURSES_TEACHED.equals(which)) {
//				ta.setCoursesTeached(ta.getCoursesTeached()+delta);
//				rt = ta.getCoursesTeached();
//				short newRank = updateAssessRank(WoshiConstants.UA_TEACHER_MAX_V4_POINTS, rt);
//				ta.setV4Rank(newRank);
//
//			} else if(SHARED_POINTS.equals(which)) {
//				ta.setSharedPoints(ta.getSharedPoints()+delta);
//				rt = ta.getSharedPoints();
//				short newRank = updateAssessRank(WoshiConstants.UA_TEACHER_MAX_V5_POINTS, rt);
//				ta.setV5Rank(newRank);
//
//			} else if(TOTAL_POINTS.equals(which)){
//				ta.setTotalPoints(ta.getTotalPoints()+delta);
//				rt = ta.getTotalPoints();
//				int newRank = updateAssessRank(WoshiConstants.UA_TEACHER_MAX_TOTAL_POINTS, rt);
//				ta.setTotalRank(newRank);
//			} else {
//				//current noting todo.
//			}
//			//calculate the total_points based on the newly assess vector.
//			int newHighScore = ta.calculateAndUpdTotalPoints();
//			//update rank accordingly.
//			int newRank = updateAssessRank(WoshiConstants.UA_TEACHER_MAX_TOTAL_POINTS, newHighScore);
//			ta.setTotalRank(newRank);
//
//			Transaction tx = null;
//			try {
//				tx = session.beginTransaction();
//				session.save(tad);
//				session.update(ta);
//				tx.commit();
//			} catch (HibernateException e) {
//				log.error(e);
//				if(tx!=null)
//					tx.rollback();
//			}
//		} catch (WoshiCommonException e) {
//			log.error(e);
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//
//		return rt;
//	}
//
////	private static final String LIST_TEACHERASSESS_BY_TOTAL = "from UserAssess order by totalPoints desc";
////	@SuppressWarnings("unchecked")
////	public int queryTeacherAssessRank(int newScore){
////		int highestScore = Integer.valueOf(ParameterCache.getIns().getParameter(WoshiConstants.UA_TEACHER_MAX_TOTAL_POINTS));//ASSESS_DICT.get(CURRENT_MAX_TOTAL_POINTS);
////		if(newScore>highestScore) {
////			highestScore = newScore;
////			ParameterCache.getIns().flushByKey(WoshiConstants.UA_TEACHER_MAX_TOTAL_POINTS);
////
////			Session ses = null;
////			try {
////				ses = DatabaseManager.getSession();
////
////				//update TeacherAssess table.
////				Query q = ses.createQuery(LIST_TEACHERASSESS_BY_TOTAL);
////				Transaction tx = ses.beginTransaction();
////				Iterator<UserAssess> it = q.list().iterator();
////				while(it.hasNext()){
////					UserAssess ta = it.next();
////					ta.setTotalRank((int)(ta.getTotalPoints()*100.f/highestScore));
////					ses.update(ta);
////				}
////				tx.commit();
////			} catch (WoshiCommonException e) {
////				log.error(e);
////			} finally {
////				if(ses != null) {
////					ses.close();
////				}
////			}
////		}
////
////		int newRank = (int)(newScore*100.f/highestScore);
////		return newRank;
////	}
//
//	/**
//	 * 仅当最高分发生变化时，更新所有人的排行，即TotalScore.
//	 * 其他情况，仅更新对应用户的rank。
//	 * @param higherScore
//	 */
//	private static final String LIST_USER_ASSESS_BY = "from UserAssess order by totalPoints desc";
//	public static short updateAssessRank(String which, int newScore) {
//		int highestScore = -1;
//		try{
//			highestScore = Integer.valueOf(ParameterCache.getIns().getParameter(which));
//		} catch (NumberFormatException e) {
//			return 0;
//		}
//
//		if(newScore>highestScore) {
//			highestScore = newScore;
//			ParameterCache.getIns().flushByKey(which, ""+highestScore);
//
//			Session session = null;
//			Transaction tx =null;
//			try {
//				session = DatabaseManager.getSession();
//
//				//update TeacherAssess table.
//				Query q = session.createQuery(LIST_USER_ASSESS_BY);
//				 tx = session.beginTransaction();
//				Iterator<UserAssess> it = q.list().iterator();
//				while(it.hasNext()){
//					UserAssess ta = it.next();
//					if(WoshiConstants.UA_TEACHER_MAX_TOTAL_POINTS.equalsIgnoreCase(which)) {
//						ta.setTotalRank((int)(ta.getTotalPoints()*100.f/highestScore));
//					} else if(WoshiConstants.UA_TEACHER_MAX_V1_POINTS.equalsIgnoreCase(which)) {
//						short nrank = (short)(ta.getV1()*100.f/highestScore);
//						ta.setV1Rank(nrank);
//					} else if(WoshiConstants.UA_TEACHER_MAX_V2_POINTS.equalsIgnoreCase(which)) {
//						short nrank = (short)(ta.getV2()*100.f/highestScore);
//						ta.setV2Rank(nrank);
//					} else if(WoshiConstants.UA_TEACHER_MAX_V3_POINTS.equalsIgnoreCase(which)) {
//						short nrank = (short)(ta.getV3()*100.f/highestScore);
//						ta.setV3Rank(nrank);
//					} else if(WoshiConstants.UA_TEACHER_MAX_V4_POINTS.equalsIgnoreCase(which)) {
//						short nrank = (short)(ta.getV4()*100.f/highestScore);
//						ta.setV4Rank(nrank);
//					} else if(WoshiConstants.UA_TEACHER_MAX_V5_POINTS.equalsIgnoreCase(which)) {
//						short nrank = (short)(ta.getV5()*100.f/highestScore);
//						ta.setV5Rank(nrank);
//					}
//
//					session.update(ta);
//				}
//				tx.commit();
//			} catch (WoshiCommonException e) {
//				log.error(e);
//			}catch (Exception e) {
//				log.error(e);
//				if(tx!=null)
//				{
//					tx.rollback();
//				}
//			}
//			finally {
//				if(session != null) {
//					session.close();
//				}
//			}
//		}
//
//		short newRank = (short)(newScore*100.f/highestScore);
//		return newRank;
//	}
//}
