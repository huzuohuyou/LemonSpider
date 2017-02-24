//package com.lemon.util.ds;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//
//import com.lemon.core.Code;
//import com.lemon.core.WoshiConstants;
//import com.lemon.core.exception.WoshiCommonException;
//import com.woshi.ds.po.RebateMatrix;
//import com.woshi.ds.po.finance.Ticket;
//import com.woshi.ds.po.finance.UserTicket;
//import com.woshi.ds.po.um.User;
//import com.woshi.ds.po.um.UserScoreJournal;
//
//
//import edu.ustc.util.MyLog;
//
//public class UserRebateUtil {
//	private final static MyLog log = MyLog.getLogger(UserRebateUtil.class);
//
//	public static final String
//			CHARGE_SCORE = "charge_score",
//			CONSUME_SCORE = "consume_score",
//			EVALUATE_SCORE = "comment_score",
//			UPLOAD_COURSEWARE_SCORE = "upload_score",
//			UPLOAD_LECTURE_SCORE = "upload_lecture_score",
//			OTHERS_DOWNLOAD_LECTURE_SCORE = "others_download_lecture_score",
//			SHARE_LECTURE_SCORE = "share_lecture_score",
//			ANSWER_QUESTION_SCORE = "answer_question_score",
//			OTHER_ADD_SCORE = "other_add_score",
//			LOGIN_SOCRE = "login_score",
//			UPLOAD_MEDIA_SOCRE = "upload_media_score",
//			COMPLETE_INFO_SOCRE = "complete_info_score",
//			DISCUSS_SCORE = "discuss_score",
//			SCORE_LOTTERY = "score_lottery",
//			SCORE_OTHER = "score_other",
//
//			WITHDRAW_SCORE = "withdraw_score",
//			DOWNLOAD_LECTURE_SCORE = "download_score",
//			KICKPUZZLE_SERVICE = "kickpuzzle_service",
//			QUIZ_SERVICE = "quiz_service",
//			GENERATE_COURSEWARE_SERVICE = "generate_courseware_service",
//			TEACHER_RECEIVE_BAD_EVALUATE = "teacher_receive_bad_evaluate",
//			TEACHER_RECEIVE_PERFECT_EVALUATE = "teacher_receive_perfect_evaluate",
//			TEACHER_RECEIVE_NORMAL_EVALUATE = "teacher_receive_normal_evaluate",
//			OTHER_MINUS_SCORE = "other_minus_score";
//
//	public static final Map<String, String> SCORE_ACTION_EXPLAIN = new HashMap<String, String>();
//	static{
//		SCORE_ACTION_EXPLAIN.put(CHARGE_SCORE, "充值返积分");
//		SCORE_ACTION_EXPLAIN.put(EVALUATE_SCORE, "评论返积分");
//		SCORE_ACTION_EXPLAIN.put(UPLOAD_COURSEWARE_SCORE, "上传得积分");
//		SCORE_ACTION_EXPLAIN.put(OTHERS_DOWNLOAD_LECTURE_SCORE, "别人下载我得积分");
//		SCORE_ACTION_EXPLAIN.put(SHARE_LECTURE_SCORE, "分享得积分");
//		SCORE_ACTION_EXPLAIN.put(ANSWER_QUESTION_SCORE, "答疑返积分");
//		SCORE_ACTION_EXPLAIN.put(WITHDRAW_SCORE, "提现扣积分");
//		SCORE_ACTION_EXPLAIN.put(CONSUME_SCORE, "消费返积分");
//		SCORE_ACTION_EXPLAIN.put(DOWNLOAD_LECTURE_SCORE, "下载资料扣积分");
//		SCORE_ACTION_EXPLAIN.put(OTHER_ADD_SCORE, "其他返积分");
//		SCORE_ACTION_EXPLAIN.put(LOGIN_SOCRE, "登录赠积分");
//		SCORE_ACTION_EXPLAIN.put(COMPLETE_INFO_SOCRE, "完善资料赠积分");
//		SCORE_ACTION_EXPLAIN.put(DISCUSS_SCORE, "讨论赠积分");
//		SCORE_ACTION_EXPLAIN.put(SCORE_LOTTERY, "积分抽奖");
//		SCORE_ACTION_EXPLAIN.put(UPLOAD_MEDIA_SOCRE, "上传视频赠积分");
//		SCORE_ACTION_EXPLAIN.put(SCORE_OTHER, "其他赠送积分");
//	}
//
//	private Map<String, Integer> ASSESS_DICT = null;
//	private UserRebateUtil(){
//		//first time run this method. load the map.
//		ASSESS_DICT = DictCache.getIns().getReDict(DictCache.TYPE_USER_SCORE);
//	}
//
//	private static UserRebateUtil _assess = null;
//	public static UserRebateUtil getIns(){
//		if(_assess==null){
//			_assess = new UserRebateUtil();
//		}
//		return _assess;
//	}
//
//
//	private final static String GET_USERSCORE_JOURNAL_BY_UID = "from UserScoreJournal j where j.uid=? order by id desc";
//	@SuppressWarnings("unchecked")
//	public static List<UserScoreJournal> getUserScoreJournalByUid(int uid, Integer page){
//		List<UserScoreJournal> ls = new ArrayList<UserScoreJournal>();
//
//		Session session = null;
//		try {
//			session = DatabaseManager.getSession();
//			Query query = session.createQuery(GET_USERSCORE_JOURNAL_BY_UID);
//			query.setInteger(0, uid);
//			query.setFirstResult((page -1) * WoshiConstants.PAGE_FINANCE_JOURNAL_ONE_PAGE);
//			query.setMaxResults(WoshiConstants.PAGE_FINANCE_JOURNAL_ONE_PAGE);
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
//	private final static String GET_SCORE_JOURNAL_COUNT_BY_TID = "select count(j.id) from UserScoreJournal j where j.uid=? ";
//	public static int getUserScoreJournalCount(int uid) {
//		Session session = null;
//		int rt = 0;
//
//		try {
//			session = DatabaseManager.getSession();
//			Query q = session.createQuery(GET_SCORE_JOURNAL_COUNT_BY_TID);
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
//	public static long sumNetUserScoreByUid(int uid) {
//		String sql = "select sum(case when typ>0 then delta when typ<0 then -delta end) as summ from um.user_score_journal where uid=? ";
//		return sumUserScoreByUid(uid, sql);
//	}
//
//	public static long sumRawUserScoreByUid(int uid) {
//		String sql = "select sum(delta) as summ from um.user_score_journal where uid=? ";
//		return sumUserScoreByUid(uid, sql);
//	}
//
//	private static long sumUserScoreByUid(int uid, String sql) {
//		Session session = null;
//		long rt = 0;
//
//		try {
//			session = DatabaseManager.getSession();
//			SQLQuery q = session.createSQLQuery(sql);
//			q.setInteger(0, uid);
//			Object o = q.uniqueResult();
//
//			if(o != null) {
//				rt = ((java.math.BigInteger)o).longValue();
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
//	/* ---------------------------- 新的积分程序 ---------------------------*/
//	/**
//	 * stong add
//	 * 根据事件和用户信息查询应该授予或者扣减的积分和券值
//	 */
//	private static final String FIND_REBATE_MATRIX = "from RebateMatrix t where t.id.code = ? and t.id.userType = ? ";
//	public static void queryRebateValue(RebateStruct struct) throws WoshiCommonException {
//		Session session = struct.getSession();
//
//		try{
//			int userLevel = struct.getUser().getStarLevel().intValue();
//
//			//查询Matrix
//			Query query = session.createQuery(FIND_REBATE_MATRIX);
//			query.setInteger(0, struct.getCode());
//			query.setInteger(1, struct.getUser().getUserType());
//			List<RebateMatrix> matrixList = query.list();
//
//			//如果有该等级的用户配置，直接使用；如果没有，看是否有默认配置；都没有则抛出异常
//			RebateMatrix defaultMatrix = null;
//			RebateMatrix resultMatrix = null;
//			for(RebateMatrix matrix : matrixList){
//				if(matrix.getId().getUserStarLevel().intValue() == 0){
//					defaultMatrix = matrix;
//				}else if(matrix.getId().getUserStarLevel().intValue() == userLevel){
//					resultMatrix = matrix;
//				}
//			}
//			if(resultMatrix == null){
//				resultMatrix = defaultMatrix;
//			}
//			if(resultMatrix == null){
//				throw new WoshiCommonException("There is no rebate matrix for user: " + struct.getUser().getId() + ".");
//			}
//
//			//计算积分
//			String additionalRule = resultMatrix.getAdditionalRule();
//			Integer scoreDelta = resultMatrix.getScoreStdValue() * resultMatrix.getScoreCoeff();
//			//计算券
//			Ticket ticket = null;
//			if(resultMatrix.getTicketType() != null && resultMatrix.getTicketType().intValue() != 0){
//				ticket = (Ticket)session.get(Ticket.class, resultMatrix.getTicketType());
//			}
//
//			//如果有额外条件，按照额外条件计算
//			if(additionalRule != null && !"".equals(additionalRule)){
//				Gson gson = new GsonBuilder().serializeNulls().create();
//				Map<String, String> ruleJsonMap = gson.fromJson(additionalRule, new TypeToken<Map<String, String>>(){}.getType());
//				Map<String, String> paraJsonMap = gson.fromJson(struct.getAdditionalPara(), new TypeToken<Map<String, String>>(){}.getType());
//				//根据金额变化积分
//				if(ruleJsonMap.containsKey("MoneyRatePositive")){
//					Double moneyRate = Double.valueOf(ruleJsonMap.get("MoneyRatePositive"));
//					Double moneyAmount = Double.valueOf(paraJsonMap.get("MoneyAmount"));
//					scoreDelta = (int)(moneyAmount * moneyRate);
//				}//根据评价内容变化积分
//				else if(ruleJsonMap.containsKey("EvaluateRule")){
//					if(ruleJsonMap.get("EvaluateRule").equals("gradient")){
//						Integer understand = Integer.valueOf(paraJsonMap.get("undersand"));
//						Integer parent = Integer.valueOf(paraJsonMap.get("parent"));
//						if(understand.intValue() == -1 || parent.intValue() == -1){
//							scoreDelta = Integer.valueOf(ruleJsonMap.get("bad"));
//						} else if(understand.intValue()==2 && parent.intValue()==2) {
//							scoreDelta = Integer.valueOf(ruleJsonMap.get("good"));
//						} else {
//							scoreDelta = Integer.valueOf(ruleJsonMap.get("normal"));
//						}
//					}
//				}//评论字数多返消费券
//				else if(ruleJsonMap.containsKey("SmsCardWord") || ruleJsonMap.containsKey("WebCardWord")){
//					String source = paraJsonMap.get("source");
//					int wordCount = 50;
//					if("sms".equals(source)){
//						wordCount = Integer.valueOf(ruleJsonMap.get("SmsCardWord"));
//					}else if("web".equals(source)){
//						wordCount = Integer.valueOf(ruleJsonMap.get("WebCardWord"));
//					}
//					if(Integer.valueOf(paraJsonMap.get("sayCount")).intValue() <= wordCount){
//						ticket = null;
//					}
//				}
//			}
//			//设置查询结果
//			struct.setScoreDelta(scoreDelta);
//			struct.setTicket(ticket);
//		}catch(WoshiCommonException e){
//			log.error("fail to query rebate value.", e);
//			throw ex;
//		}catch(Exception e){
//			log.error("fail to query rebate value.", e);
//			throw new WoshiCommonException(ex, "");
//		}
//	}
///*
// * 传进来的参数user不要执行session.update();
//*/
//	public void modifyRebate(User user, User relateUser, String eventName, String additionalPara, Session session) throws WoshiCommonException{
//
//		log.info("Update user: " + user.getId() + "'s score with event: " + eventName + ".");
//		Date now = new Date();
//
//		//EventName转为EventCode
//		Integer eventCode = ASSESS_DICT.get(eventName);
//		if(eventCode == null){
//			eventCode = 0;
//		}
//
//		//查询积分变化量以及得券情况
//		RebateStruct struct = new RebateStruct();
//		struct.setUser(user);
//		struct.setCode(eventCode);
//		struct.setAdditionalPara(additionalPara);
//		struct.setSession(session);
//		queryRebateValue(struct);
//
//		Integer scoreDelta = struct.getScoreDelta();
//		Ticket ticketType = struct.getTicket();
//
//		//添加积分日志表新纪录，并修改积分
//		if(scoreDelta.intValue() != 0){
//			try {
//				user.setScore(user.getScore() + scoreDelta);
//				UserScoreJournal scoreJournal = new UserScoreJournal();
//				scoreJournal.setUid(user.getId());
//				if(relateUser != null){
//					scoreJournal.setSponsorId(relateUser.getId());
//				}
//				scoreJournal.setDelta(scoreDelta);
//				scoreJournal.setTyp(eventCode.shortValue());
//				scoreJournal.setTimetag(now);
//				session.save(scoreJournal);
//			  //session.saveOrUpdate(user);
//
//				log.info("add score: " + scoreDelta + " points for user: " + user.getId() + ".");
//			} catch (Exception e) {
//				log.error(e);
//				throw new WoshiCommonException(e, "");
//			}
//		}
//
//		//添加券新纪录
//		if(ticketType != null){
//			try{
//				UserTicket ticket = new UserTicket();
//				ticket.setAmount(ticketType.getAmount());
//				ticket.setCreateTime(now);
//				ticket.setStatus(Code.C200);
//				ticket.setStatusTime(now);
//				ticket.setUserId(user.getId());
//				ticket.setName(ticketType.getName());
//				ticket.setDescription(ticketType.getDescription());
//				ticket.setTicketType(ticketType.getId());
//				if(ticketType.getInvalidDate() != null){
//					ticket.setInvalidTime(ticketType.getInvalidDate());
//				}else{
//					ticket.setInvalidTime(new Date(now.getTime() + 3600*1000*24l*ticketType.getInvalidDays()));
//				}
//				session.save(ticket);
//				log.info("add ticket: " + ticketType.getName() + " for user: " + user.getId() + ".");
//			} catch (Exception e) {
//				log.error(e);
//				throw new WoshiCommonException(e, "");
//			}
//		}
//	}
//}
