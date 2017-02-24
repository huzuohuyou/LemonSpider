package com.lemon.commons.util.ds;
//package com.lemon.util.ds;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.lemon.core.WoshiConstants;
//import com.lemon.core.exception.WoshiCommonException;
//import com.woshi.ds.po.um.SystemMessage;
//
//import edu.ustc.util.MyLog;
//
//public class SystemMessageHandler {
//	
//	private final static MyLog log = MyLog.getLogger(SystemMessageHandler.class);
//	private static final String  FIND_NEWEST_SYSTEM_MESSAGE = "from SystemMessage t where t.ownerId = ? order by t.id desc ";
//	private static final String  COUNT_SYSTEM_MESSAGE = "select count(t.id) from SystemMessage t where t.ownerId = ?";
//
//	public static void getMessageList(Map<String, Object> paraMap){
//		List<SystemMessage> messageList = null;
//		Integer messagePageCount = 0;
//		Session session = null;
//		Integer userId = (Integer)paraMap.get("user_id");
//		Integer page = (Integer)paraMap.get("page");
//		
//		try {
//			session = DatabaseManager.getSession();
//			Query query = session.createQuery(FIND_NEWEST_SYSTEM_MESSAGE);
//			query.setInteger(0, userId);
//			query.setFirstResult((page - 1) * WoshiConstants.PAGE_TEACHER_SYSTEM_MESSAGE_COUNT_ONE_PAGE);
//			query.setMaxResults(WoshiConstants.PAGE_TEACHER_SYSTEM_MESSAGE_COUNT_ONE_PAGE);
//			messageList = query.list();
//			
//			Query countQuery = session.createQuery(COUNT_SYSTEM_MESSAGE);
//			countQuery.setInteger(0, userId);
//			Integer messageCount = ((Long)countQuery.uniqueResult()).intValue();
//			messagePageCount = PaginatingUtil.getPageCount(messageCount.intValue(), WoshiConstants.PAGE_TEACHER_SYSTEM_MESSAGE_COUNT_ONE_PAGE);
//		} catch (WoshiCommonException e) {
//			log.error(e);
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//		
//		paraMap.put("msgList", messageList);
//		paraMap.put("pageCount", messagePageCount);
//	}
//	
//	public static void readMessage(Integer messageId){
//		Session session = null;
//		Transaction tran = null;
//		
//		try {
//			session = DatabaseManager.getSession();
//			tran = session.beginTransaction();
//			SystemMessage msg = (SystemMessage)session.get(SystemMessage.class, messageId);
//			msg.setReadFlag((short)1);
//			msg.setReadTime(new Date());
//			session.update(msg);
//			tran.commit();
//		} catch (WoshiCommonException e) {
//			log.error(e);
//			if(tran != null){
//				tran.rollback();
//			}
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//	}
//	
//	public static void addMessage(Map<String, Object> paraMap){
//		Session session = (Session)paraMap.get("session");
//		Integer ownerId = (Integer)paraMap.get("owner_id");
//		Integer senderId = (Integer)paraMap.get("sender_id");
//		Integer type = (Integer)paraMap.get("type");
//		String subject = (String)paraMap.get("subject");
//		String content = (String)paraMap.get("content");
//		
//		try{
//			SystemMessage msg = new SystemMessage();
//			msg.setContent(content);
//			msg.setFollowId(0l);
//			msg.setOwnerId(ownerId);
//			msg.setReadFlag((short)0);
//			msg.setSenderId(senderId);
//			msg.setSendTime(new Date());
//			msg.setSubject(subject);
//			msg.setType(type);
//			
//			session.save(msg);
//			UserNewMsgCountCache.getIns().abolishUserMsgCount(ownerId);
//		} catch (Exception e) {
//			log.error(e);
//		}
//	}
//}
