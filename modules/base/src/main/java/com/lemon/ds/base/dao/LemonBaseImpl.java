package com.lemon.ds.base.dao;

import com.lemon.commons.enm.EnumSearchOperator;
import com.lemon.commons.log.Log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ywp on 15/12/7.
 */
public class LemonBaseImpl<T> {
	private final static Log log = Log.getLogger(LemonBaseImpl.class);

    protected String getPre(){
        return null;
    };
	
    @PersistenceContext
    protected EntityManager em;

    protected  Query createQuery(String hql,Map<String,Object> params){

        Query query = em.createQuery(hql);

        for (String key : params.keySet()){
            query.setParameter(key,params.get(key));
        }

        return query;
    }

    @SuppressWarnings("unchecked")
	protected String getWhere(Map<String, Object> searchParams,Map<String, Object> hqlParams){
        String where = "";
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType){
            Type[] p =  ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<T>)p[0];
        }

        if (searchParams != null) {
            for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
                Object value = entry.getValue();
                if (value == null || "".equals(value)) {
                    continue;
                }
                String[] names = StringUtils.split(entry.getKey(), "_");
                if (names.length == 2) {
                    EnumSearchOperator operator = EnumSearchOperator.valueOf(names[0]);
                    Object v = value;
                    String tmpName = names[1].replaceAll("\\.","_");

                    switch (operator) {
                        case EQ4Char:
                            v = ((String)value).charAt(0);
                            where += String.format(" and %s = :%s", names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case EQ4Int:
                            v = Integer.parseInt(value.toString());
                            where += String.format(" and %s = :%s", names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case EQ:
                            if(names[1].indexOf("id") >=0){
                                v = Integer.parseInt(value.toString());
                            }
                            where += String.format(" and %s = :%s", names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case LIKE:
                            where += String.format(" and %s like :%s",names[1], tmpName);
                            hqlParams.put(tmpName, "%" + v.toString() + "%");
                            break;
                        case GT:
                            where += String.format(" and %s > :%s", names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case LT:
                            where += String.format(" and %s < :%s", names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case GTE:
                            where += String.format(" and %s >= :%s",names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case LTE:
                            where += String.format(" and %s <= :%s", names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case NE4Int:
                        case NE:
                            where += String.format(" and %s <> :%s", names[1], tmpName);
                            hqlParams.put(tmpName, v);
                            break;
                        case Between4Long: {
                            Object[] pair = (Object[]) v;
                            Long x = (Long) pair[0];
                            Long y = (Long) pair[1];
                            where += String.format(" and %s between %d and %d", names[1], x, y);
                            break;
                        }
                        case Between4Integer: {
                            Object[] pair = (Object[]) v;
                            Integer x = (Integer) pair[0];
                            Integer y = (Integer) pair[1];
                            where += String.format(" and %s between %d and %d",names[1], x, y);
                            break;
                        }
                        case ChildOf:
                            break;
                        case IN:
                            break;
                        case EXP:
                            where += " and ("+value+")";
                            break;
                        default:
                        	log.error("unsupported operation found: {}", operator);
                        	break;
                    }

                }
            }
        }
        return where;
    }


	protected Page<T> excuteHql(String hql,Map<String, Object> params,Query countQuery,Pageable pageRequest){
        return excuteHqlTo(hql, params, countQuery, pageRequest);
    }

    protected <Ta> Page<Ta> excuteHqlTo(String hql,Map<String, Object> params,Query countQuery,Pageable pageRequest){
        Object obj = countQuery.getSingleResult();
        Long total = (Long) obj;
        Page<Ta> page = null;
        int startIndex = pageRequest.getPageNumber() * pageRequest.getPageSize();

        if(total!=null && total > 0){
            if (pageRequest.getSort() != null) {
                int i = 0;
                Iterator<Sort.Order> iterator = pageRequest.getSort().iterator();
                while (iterator.hasNext()) {
                    if (i == 0) {
                        hql += " order by ";
                    } else {
                        hql += ",";
                    }
                    Sort.Order order = iterator.next();
                    String property = order.getProperty();
                    if(getPre()!=null && getPre().length() > 0){
                        if(!property.contains(getPre() + ".") && !property.contains("(")){
                            property = getPre() + "." + order.getProperty().trim();
                        }
                    }

                    hql += property + " " + order.getDirection().toString();
                    i++;
                }
            }

            Query q = createQuery(hql,params);

            q.setFirstResult(startIndex);
            q.setMaxResults(pageRequest.getPageSize());

            page = new PageImpl<Ta>(q.getResultList(), new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize()), total);
        }
        else {
            page = new PageImpl<Ta>(new ArrayList<>(), new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize()), total);
        }

        return page;
    }
}
