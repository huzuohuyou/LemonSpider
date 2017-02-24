package com.lemon.commons.util.ds;
///**
// * 该类提供了诸多（或所有)静态的和老师、学生财务相关的方法。
// * 大致的layout是，提供一组public的老师和学生的财务操作接口，
// * 然后这两个接口调用一个私有的通用的user接口，可能会因为不同的角色，具体的称谓不太一样。
// * 除了财务操作，还有财务的流水记录等操作。
// *
// * 应该把所有的财务的新功能也都在这个类里实现，并根据操作的具体类型放到合适的块。
// * 该方法可能会被@see com.woshi.event.handler.EventFinanceHandler 调用
// */
//package com.lemon.util.ds;
//
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.lemon.core.WoshiConstants;
//import com.lemon.core.exception.WoshiCommonException;
//import com.woshi.ds.po.OtoCourse;
//import com.woshi.ds.po.OtoOrder;
//import com.woshi.ds.po.OtoOrderHistory;
//import com.woshi.ds.po.finance.FastCash;
//import com.woshi.ds.po.finance.PriceMatrix;
//import com.woshi.ds.po.finance.TeacherPriceMatrix;
//import com.woshi.ds.po.finance.UserBalance;
//import com.woshi.ds.po.finance.UserFinanceJournal;
//
//import edu.ustc.util.MyLog;
//
//public class UserFinanceUtil {
//	private final static MyLog log = MyLog.getLogger(UserFinanceUtil.class);
//
//	/**
//	 * operation related
//	 *
//	 */
//
//
//	public static final Float CORP_COMMISSION_L1 = 0.2f,
//								  CORP_COMMISSION_L2 = 0.25f,
//								  CORP_COMMISSION_L3 = 0.3f,
//								  CORP_COMMISSION_L4 = 0.35f,
//								  CORP_COMMISSION_L5 = 0.4f;
//
//	//推广期间，课时押金暂不扣，或说是比例为0
//	public static final float MORTAGE_RATE = 0.0f; //押金占老师应得课时费用的比例
//
//	public final static String USER_CHARGE = "user_charge",
//							   USER_WITHDRAW = "user_withdraw";
//
//
//	/**
//--------------------------------------------------------------------------------------
//	 * 老师、学生相关的账户收支平衡、账户变动记录等常规操作。
//	 *
//	 */
//
//
//	/**
//	 * 当发现没有uid对应的财务数据时，创建一个全为0的记录，更新到数据库的同时，并返回这个对象。
//	 * @param uid
//	 */
//	public static UserBalance createTeacherInitialRecord(Integer tid) {
//		return createInitialRecord(tid, WoshiConstants.TYPE_ACCOUNT_TEACHER);
//	}
//	public static UserBalance createStudentInitalRecord(Integer sid) {
//		return createInitialRecord(sid, WoshiConstants.TYPE_ACCOUNT_STUDENT);
//	}
//	private static UserBalance createInitialRecord(Integer uid, Integer userType){
//		UserBalance bt = new UserBalance(uid);
//		bt.init();
//		bt.setUserType(userType.shortValue());
//
//		Session ses = null;
//		try {
//			ses = DatabaseManager.getSession();
//			Transaction tx = ses.beginTransaction();
//			ses.save(bt);
//			tx.commit();
//			ses.refresh(bt);
//		} catch (WoshiCommonException e) {
//			log.error(" teacher:" + uid +" save to db failed.", e);
//		} catch (Exception e) {
//			log.error(e);
//		} finally {
//			if(ses != null) {
//				ses.close();
//			}
//		}
//		return bt;
//	}
//
//
//	/**
//	 * 获得老师的唯一的当前财务收支的记录，如果不存在，则自动调用createInitialRecord方法产生全0的记录
//	 * @param uid
//	 * @return
//	 */
//	public static UserBalance getTeacherFinanceBalance(Integer uid) {
//		return getUserFinanceBalance(uid, WoshiConstants.TYPE_ACCOUNT_TEACHER);
//	}
//	public static UserBalance getStudentFinanceBalance(Integer uid) {
//		return getUserFinanceBalance(uid, WoshiConstants.TYPE_ACCOUNT_STUDENT);
//	}
//	public static UserBalance getUserFinanceBalance(Integer uid, Integer userType){
//		Session session = null;
//		UserBalance bt = null;
//
//		try{
//			session = DatabaseManager.getSession();
//			Object o = session.get(UserBalance.class, uid);
//			if(o!=null){
//				bt = (UserBalance)o;
//			}else{
//				bt = createInitialRecord(uid, userType);
//			}
//
//			log.debug(" user:" + bt.toString() );
//		}catch(WoshiCommonException e){
//			log.error(" Fail to get avail time by teacher:{}.", uid, e);
//		}catch(Exception e){
//			log.error(" Fail to get avail time by teacher:{}.", uid, e);
//		}finally{
//			if(session != null){
//				session.close();
//			}
//		}
//
//		return bt;
//	}
//
//	private final static String GET_JOURNAL_BY_TID = "from UserFinanceJournal j where j.userId=? order by j.id desc ";
//	public static List<UserFinanceJournal> getUserFinanceJournal(Integer uid, Integer page){
//		Session session = null;
//		List<UserFinanceJournal> ls = null;
//		try {
//			session = DatabaseManager.getSession();
//			Query query = session.createQuery(GET_JOURNAL_BY_TID);
//			query.setInteger(0, uid);
//			query.setFirstResult((page -1) * WoshiConstants.PAGE_FINANCE_JOURNAL_ONE_PAGE);
//			query.setMaxResults(WoshiConstants.PAGE_FINANCE_JOURNAL_ONE_PAGE);
//			ls = (List<UserFinanceJournal>)query.list();
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
//
//	private final static String GET_JOURNAL_COUNT_BY_TID = "select count(*) from UserFinanceJournal j where j.userId=? ";
//	public static int getUserFinanceJournalCount(int uid) {
//		Session session = null;
//		int rt = 0;
//
//		try {
//			session = DatabaseManager.getSession();
//			Query q = session.createQuery(GET_JOURNAL_COUNT_BY_TID);
//			q.setInteger(0, uid);
//
//			Object o = q.uniqueResult();
//			if(o != null) {
//				rt = ((Long)o).intValue();
//			}
//
//			rt = PaginatingUtil.getPageCount(rt, WoshiConstants.PAGE_FINANCE_JOURNAL_ONE_PAGE);
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
//
//
//
//	/**
//-------------------------------------------------------------------------
//	 * 下面是和不同星级老师的PriceMatrix相关的接口。
//	 * 不同星级的老师，教不同年级，有一组最高价和最低价。
//	 * 每个老师可以根据自己的实际情况，设定自己的最高价和最低价，以及当前的报价，
//	 * 但老师设置的最高、最低价必须落在缺省的最高、最低价之内。
//	 *
//	 * 数据库相关的表是 price_matrix & teacher_price_matrix.
//	 */
//
//	@SuppressWarnings("unchecked")
//	public static List<PriceMatrix> getDefaultPriceMatrix() {
//		Session session = null;
//		List<PriceMatrix> ls = null;
//
//		try {
//			session = DatabaseManager.getSession();
//			ls = session.createCriteria(PriceMatrix.class).list();
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
//	private final static String SQL_PRICE_MATRIX_BY_STAR = "from PriceMatrix m where m.level=?";
//	public static PriceMatrix getDefaultPriceMatrixByStar(int star) {
//		Session session = null;
//		PriceMatrix rt = null;
//
//		try {
//			session = DatabaseManager.getSession();
//			Query q = session.createQuery(SQL_PRICE_MATRIX_BY_STAR);
//			q.setInteger(0, star);
//			rt = (PriceMatrix)q.uniqueResult();
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
//	/**
//	 * 获得teacherId对应老师的价格矩阵，如果没有该记录，则根据PriceMatrix中的记录生成缺省的记录。
//	 * @param teacherId
//	 * @return
//	 */
//	public static TeacherPriceMatrix getMyPriceMatrix(int teacherId) {
//		return getMyPriceMatrix(teacherId, true);
//	}
//
//	private final static String SQL_GET_TEACHER_LEVEL = "select star_level from um.user_ t where t.id=?";
//	private final static String SQL_GET_TEACHER_PM = "from TeacherPriceMatrix t where t.id.uid=? and t.id.currentLevel=?";
//	private final static String SQL_DUMP_NEW =
//			" insert into finance.teacher_price_matrix(uid, current_level, course_teached, course_left, school_maskcode, price_hs, price_hs_low, price_hs_high, price_js, price_js_low, price_js_high, price_es, price_es_low, price_es_high, corp_commission) " +
//			" select ? as uid, level, 0, upgrade, 7, price_hs, price_hs_low, price_hs_high, price_js, price_js_low, price_js_high, price_es, price_es_low, price_es_high, 0.2 " +
//			" from finance.price_matrix m " +
//			" where m.level=? ";
//	/**
//	 * 获得teacherId对应老师的价格矩阵，
//	 * 如果没有该记录，同时createOnEmpty=true，则根据PriceMatrix中的记录生成缺省的记录。
//	 *     如果createOnEmpty=false,则仅仅返回空记录，或null值。
//	 * @param teacherId
//	 * @param createOnEmpty
//	 * @return
//	 */
//	public static TeacherPriceMatrix getMyPriceMatrix(int teacherId, boolean createOnEmpty) {
//		Session session = null;
//		TeacherPriceMatrix rt = null;
//
//		try {
//			session = DatabaseManager.getSession();
//			SQLQuery sq = session.createSQLQuery(SQL_GET_TEACHER_LEVEL);
//			sq.setInteger(0, teacherId);
//			Object o = sq.list().get(0);
//			short level = 2;
//			if(o!=null) {
//				level = ((Integer)o).shortValue();
//			}
//
//			if(rt==null) {
//				Query query = session.createQuery(SQL_GET_TEACHER_PM);
//				query.setInteger(0, teacherId);
//				query.setShort(1, level);
//				rt = (TeacherPriceMatrix)query.uniqueResult();
//				if(createOnEmpty && rt==null) {
//					Transaction tran = session.beginTransaction();
//					sq = session.createSQLQuery(SQL_DUMP_NEW);
//					sq.setInteger(0, teacherId);
//					sq.setShort(1, level);
//					sq.executeUpdate();
//					tran.commit();
//
//					rt = (TeacherPriceMatrix)query.uniqueResult();
//				}
//			}
//
//			return rt;
//		} catch (WoshiCommonException e) {
//			log.error(e);
//			return null;
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//	}
//
//
//
//	/**
//------------------------------------------------------------------------------------
//	 * 下面是和订单、课程相关的财务操作，包括押金、按次将钱返给老师等等。
//	 *
//	 */
//
//
//	public static boolean addStudentFinanceJournal(int uid, int fromWho, double delta, Integer type, String forWhat, Session session) throws WoshiCommonException{
//		return addUserFinanceJournal(WoshiConstants.TYPE_ACCOUNT_STUDENT, uid, fromWho, delta, type, forWhat, session);
//	}
//	public static boolean addTeacherFinanceJournal(int uid, int fromWho, double delta, Integer type, String forWhat, Session session) throws WoshiCommonException {
//		return addUserFinanceJournal(WoshiConstants.TYPE_ACCOUNT_TEACHER, uid, fromWho, delta, type, forWhat, session);
//	}
//	private static boolean addUserFinanceJournal(int userType, int uid, int fromWho, double delta, Integer type, String forWhat, Session session) throws WoshiCommonException {
//		//add a new record into detail table
//		UserFinanceJournal ufjournal = new UserFinanceJournal();
//		ufjournal.setUserId(uid);
//		ufjournal.setRelateUid(fromWho);
//		ufjournal.setCashAmount(delta);
//		ufjournal.setDescription(forWhat);
//		ufjournal.setJournalTypeid(type);
//		ufjournal.setJournalTime(new java.util.Date());
//
//		session.save(ufjournal);
//
//		UserBalance ubalance = (UserBalance)session.get(UserBalance.class, uid);
//		ubalance.setLastBalance(ubalance.getLastBalance() + delta);
//
//		session.update(ubalance);
//
//		return true;
//	}
//
//	public static void finishCourseFinance(OtoCourse course) {
//		int mask = course.getMask().intValue();
//		if(mask!=7)
//			return;
//
//		int orderId = course.getOrderId();
//
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = DatabaseManager.getSession();
//			OtoOrder oorder = (OtoOrder)session.get(OtoOrder.class, orderId);
//
//			tx = session.beginTransaction();
//
//			if(finishCourseFinance(session, course, oorder)) {
//				tx.commit();
//			} else {
//				tx.rollback();
//			}
//		} catch (WoshiCommonException e) {
//			log.error(e);
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//	}
//
//
//	public static boolean finishCourseFinance(Session session, OtoCourse course, OtoOrder order) {
//		double allCourseFee = order.getFeePerCourse() * course.getCourseTeached();
//		double teacherCourseFee = allCourseFee * (1.0-order.getCorpCommission());
//		{//teacher journal
//			UserFinanceJournal jt = new UserFinanceJournal();
//			jt.setUserId(order.getTeacherId());
//			jt.setRelateUid(order.getStudentId());
//			jt.setJournalTypeid(DictCache.DICT_FINANCE_COURSE_FEE);
//
//			jt.setCashAmount(teacherCourseFee);
//			jt.setDescription("课时费用到账");
//			jt.setJournalTime(new java.util.Date());
//			session.save(jt);
//		}
//		{//student journal
//			UserFinanceJournal jt = new UserFinanceJournal();
//			jt.setUserId(order.getStudentId());
//			jt.setRelateUid(order.getTeacherId());
//			jt.setJournalTypeid(DictCache.DICT_FINANCE_COURSE_FEE);
//
//			jt.setCashAmount(-allCourseFee);
//			jt.setDescription("课时费用支出");
//			jt.setJournalTime(new java.util.Date());
//			session.save(jt);
//		}
//		/**
//		 * 假设delta正数，表示学生违约，老师是应多得。
//		 * 如果为负数，表示是老师违约，学生应少出。
//		 */
//		double teacherDeltaFee = 0.0d;
//		if(course.getTeacherDeltaFee()!=null) {
//			teacherDeltaFee = course.getTeacherDeltaFee();
//			if(Math.abs(teacherDeltaFee) > 0.01) {
//				//teacher journal
//				UserFinanceJournal jt = new UserFinanceJournal();
//				jt.setUserId(order.getTeacherId());
//				jt.setRelateUid(order.getStudentId());
//				jt.setJournalTypeid(DictCache.DICT_FINANCE_COURSE_CHANGE_PENALTY);
//
//				jt.setCashAmount(teacherDeltaFee);
//				jt.setDescription("课程更改引发的惩罚性补偿");
//				jt.setJournalTime(new java.util.Date());
//				session.save(jt);
//			}
//		}
//
//		double studentDeltaFee = 0.0d;
//		if(course.getStudentDeltaFee() != null) {
//			studentDeltaFee = course.getStudentDeltaFee();
//			if(Math.abs(studentDeltaFee) > 0.01) {
//				//student journal
//				UserFinanceJournal jt = new UserFinanceJournal();
//				jt.setUserId(order.getStudentId());
//				jt.setRelateUid(order.getTeacherId());
//				jt.setJournalTypeid(DictCache.DICT_FINANCE_COURSE_CHANGE_PENALTY);
//
//				jt.setCashAmount(studentDeltaFee);
//				jt.setDescription("课程更改引发的惩罚性补偿");
//				jt.setJournalTime(new java.util.Date());
//				session.save(jt);
//			}
//		}
//
//		UserBalance teacherBalance = (UserBalance)session.get(UserBalance.class, order.getTeacherId());
//		double newFee = teacherBalance.getTotalCourseFee() + teacherCourseFee + teacherDeltaFee;
//		teacherBalance.setTotalCourseFee(newFee);
//		session.update(teacherBalance);
//
//		UserBalance studentBalance = (UserBalance)session.get(UserBalance.class, order.getStudentId());
//		double newFee2 = studentBalance.getTotalCourseFee() - allCourseFee - studentDeltaFee;
//		studentBalance.setTotalCourseFee(newFee2);
//		session.update(studentBalance);
//
//		order.setFinishedCourse(order.getFinishedCourse()+course.getCourseTeached());
//		double deltaFee = course.getCourseTeached() * order.getFeePerCourse();
//		double oldExpense = order.getStudentExpense()==null? 0.0d : order.getStudentExpense().doubleValue();
//		order.setStudentExpense(oldExpense + deltaFee);
//
//		double tExpense = order.getTeacherIncome()==null? 0.0d : order.getTeacherIncome().doubleValue();
//		order.setTeacherIncome(tExpense + deltaFee * (1-order.getCorpCommission()) );
//
//		double oIncome = order.getOurIncome()==null? 0.0d : order.getOurIncome().doubleValue();
//		order.setOurIncome(oIncome + deltaFee * order.getCorpCommission());
//		session.update(order);
//		OtoOrderHistory history = OtoOrderHistory.createHistory4OtoOrder(order);
//		session.save(history);
//
//		//判断学生账户余额是否还够下一次上课的费用，如果不够，给学生和管理员分别一封通知
//		if(studentBalance.getCurrentBalance() - deltaFee < 0) {
//			Map<String, Object> paraMap = new HashMap<String, Object>();
//			paraMap.put("session", session);
//			paraMap.put("owner_id", order.getStudentId());
//			paraMap.put("sender_id", 0);
//			paraMap.put("type", 1);
//			String s1 = WoshiConstants.MONEY_DEICAML_FORMAT.format(studentBalance.getCurrentBalance());
//			String s2 = WoshiConstants.MONEY_DEICAML_FORMAT.format(deltaFee);
//			String s3 = WoshiConstants.MONEY_DEICAML_FORMAT.format(studentBalance.getCurrentBalance() - deltaFee);
//			paraMap.put("subject", "您的账户余额不足支付下次的课时费用");
//			paraMap.put("content", "您的账户余额还有：" + s1 + "元，下次课的课时费用可能为"+ s2 + "，还差"+ s3 + "元。如果您还有课程没有上完，请您在下次上课前尽快续费。<a class=\"font-blue curpoint\" mail-target='/v2/woshi/student/self/order/finance-flow.jsp'>查看我的资金流水</a>");
//			SystemMessageHandler.addMessage(paraMap);
//		}
//
////		//给老师通知
////		Map<String, Object> paraMap = new HashMap<String, Object>();
////		paraMap.put("session", session);
////		paraMap.put("owner_id", order.getTeacherId());
////		paraMap.put("sender_id", 0);
////		paraMap.put("type", 1);
////		User student = UserUtil.getUserById(order.getStudentId());
////		paraMap.put("subject", "您给" + student.getFullName() + "同学在" + format.format(course.getClassTime()) + "上的课程已经被评价，当次课酬已发放");
////		paraMap.put("content", "恭喜您，您给" + student.getFullName() + "同学在" + format.format(course.getClassTime()) + "上的课程已经被评价， <a class=\"font-blue curpoint\" mail-target=\"/t-theysay/" + order.getTeacherId() + "\">查看评价详情</a>；" +
////				"该课程的课时费也已经发放，您可以<a class=\"font-blue curpoint\" mail-target=\"/teacher/home\">查看账户总览</a> 或 <a class=\"font-blue curpoint\" mail-target=\"/v2/woshi/teacher/self/order/finance-flow.jsp\">查看资金流水明细</a>。");
////		SystemMessageHandler.addMessage(paraMap);
//
//		return true;
//	}
//
//	/**
//	 *
//	 * @param order
//	 * @param completed
//	 * 		true:课程全部上完，退还全部押金；
//	 * 		false:课程未全部上完，押金不予退还
//	 */
//	public static void finishOrderFinance(OtoOrder order, boolean completed) {
//		Session session = null;
//		Transaction tx = null;
//		try {
//			session = DatabaseManager.getSession();
//
//			tx = session.beginTransaction();
//
//			UserFinanceJournal jt = new UserFinanceJournal();
//			jt.setUserId(order.getTeacherId());
//			jt.setRelateUid(order.getStudentId());
//			if(completed) {
//				jt.setJournalTypeid(DictCache.DICT_FINANCE_END_FINISH);
//				jt.setCashAmount(order.getTeacherMortgage());
//				jt.setDescription("订单完成，返还押金");
//			} else {
//				jt.setJournalTypeid(DictCache.DICT_FINANCE_END_UNFINISH);
//				jt.setCashAmount(.0);
//				jt.setDescription("未完课结课，不退押金");
//			}
//			jt.setJournalTime(new java.util.Date());
//			session.save(jt);
//
//			tx.commit();
//		} catch (WoshiCommonException e) {
//			log.error(e);
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//	}
//
//
//	public static FastCash getNewestFastCashLog(Integer uid) {
//		Session session = null;
//		try {
//			session = DatabaseManager.getSession();
//			Query q = session.createQuery("from FastCash f where f.applyUser=? order by f.id desc");
//			q.setInteger(0, uid);
//			q.setMaxResults(1);
//			List<FastCash> list = q.list();
//			if(list==null || list.size()<1) {
//
//				return null;
//			}
//			return list.get(0);
//		} catch (WoshiCommonException e) {
//			log.error(e);
//			return null;
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//	}
//
//	/**
//	 *
//<div class='section4' id='process'>
//        <div class='node fore ready'>
//        	<ul>
//        		<li class='txt1'>&nbsp;</li>
//        		<li class='txt2'>老师申请取现</li>
//        		<li class='txt3'>2012-05-04 <br -='\"> 14:43:30</li>
//        	</ul>
//        </div>
//		<div class='proce ready'><ul><li class='txt1'>&nbsp;</li></ul></div>
//		<div class='node ready'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>客服审核通过</li><li class='txt3'></li></ul></div>
//		<div class='proce ready'><ul><li class='txt1'>&nbsp;</li></ul></div>
//		<div class='node ready'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>财务审核通过</li><li class='txt3'></li></ul></div>
//		<div class='proce wait'><ul><li class='txt1'>&nbsp;</li></ul></div>
//		<div class='node wait'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>已转账请查收</li><li class='txt3'>&nbsp;</li></ul></div>
//</div>
//	 *
//	 * @param fc
//	 * @return
//	 */
//	public static String prettyPrintFastCash(FastCash fc) {
//		if(fc==null) {
//			return "<span>未曾取现</span>";
//		}
//
//		StringBuilder sb = new StringBuilder();
//		sb.append("<div class='section4' id='process'>");
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//		if( fc.getStatus().intValue() == 1) {
//			sb.append("<div class='node fore wait'>")
//			  .append("<ul>")
//			  .append("<li class='txt1'>&nbsp;</li>")
//			  .append("<li class='txt2'>").append(DictCache.getIns().getDict("fast_cash").get(1)).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime1()==null?"":sdf.format(fc.getTime1())).append("</li>")
//			  .append("</ul>")
//			  .append("</div>");
//		} else {
//			sb.append("<div class='node fore ready'>")
//			  .append("<ul>")
//			  .append("<li class='txt1'>&nbsp;</li>")
//			  .append("<li class='txt2'>").append(DictCache.getIns().getDict("fast_cash").get(1)).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime1()==null?"":sdf.format(fc.getTime1())).append("</li>")
//			  .append("</ul>")
//			  .append("</div>");
//		}
//
//		if( fc.getStatus() >= 2) {
//			sb.append("<div class='proce ready'><ul><li class='txt1'>&nbsp;</li></ul></div>");
//			sb.append("<div class='node ready'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>")
//			  .append(DictCache.getIns().getDict("fast_cash").get(2)).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime2()==null?"":sdf.format(fc.getTime2())).append("</li>")
//			  .append("</ul></div>");
//		} else {
//			sb.append("<div class='proce wait'><ul><li class='txt1'>&nbsp;</li></ul></div>");
//			sb.append("<div class='node wait'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>")
//			  .append(DictCache.getIns().getDict("fast_cash").get(2)).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime2()==null?"":sdf.format(fc.getTime2())).append("</li>")
//			  .append("</ul></div>");
//		}
//
//		if( fc.getStatus() >= 4) {
//			sb.append("<div class='proce ready'><ul><li class='txt1'>&nbsp;</li></ul></div>");
//			sb.append("<div class='node ready'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>")
//			  .append(DictCache.getIns().getDict("fast_cash").get(4)).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime3()==null?"":sdf.format(fc.getTime3())).append("</li>")
//			  .append("</ul></div>");
//		} else {
//			sb.append("<div class='proce wait'><ul><li class='txt1'>&nbsp;</li></ul></div>");
//			sb.append("<div class='node wait'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>")
//			  .append(DictCache.getIns().getDict("fast_cash").get(4)).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime3()==null?"":sdf.format(fc.getTime3())).append("</li>")
//			  .append("</ul></div>");
//		}
//
//		if( fc.getStatus() >= 8) {
//			sb.append("<div class='proce ready'><ul><li class='txt1'>&nbsp;</li></ul></div>");
//			sb.append("<div class='node ready'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>")
//			  .append(DictCache.getIns().getDict("fast_cash").get(fc.getStatus())).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime4()==null?"":sdf.format(fc.getTime4())).append("</li>")
//			  .append("</ul></div>");
//		} else {
//			sb.append("<div class='proce wait'><ul><li class='txt1'>&nbsp;</li></ul></div>");
//			sb.append("<div class='node wait'><ul><li class='txt1'>&nbsp;</li><li class='txt2'>")
//			  .append(DictCache.getIns().getDict("fast_cash").get(8)).append("</li>")
//			  .append("<li class='txt3'>").append(fc.getTime4()==null?"":sdf.format(fc.getTime4())).append("</li>")
//			  .append("</ul></div>");
//		}
//
//		sb.append("</div>");
//
//		return sb.toString();
//	}
//
//
//
//	/**
//	 * journal related
//	 *
//	 */
//
//
//}
