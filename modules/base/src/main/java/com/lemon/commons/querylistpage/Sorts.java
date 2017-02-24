package com.lemon.commons.querylistpage;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by bob on 16/2/4.
 */
public final class Sorts {
    private static Map<String, Sort> sortPools = new TreeMap<>();

	public  static Sort Default = Sorts.createSort("id@desc");
	public  static Sort ByIdAsc	= Sorts.createSort("id@asc");
    public  static Sort ByIdDesc = Sorts.createSort("id@desc");

    //-------- time related ------
    public  static Sort ByStatusTimeDesc = Sorts.createSort("statusTime@desc");
    public  static Sort ByCreateTimeDesc = Sorts.createSort("createTime@desc");
    public  static Sort ByTsDesc = Sorts.createSort("ts@desc");
    public  static Sort ByStartTimeAsc = Sorts.createSort("startTime@asc");
    public  static Sort ByStartTimeDesc	= Sorts.createSort("startTime@desc");
    public  static Sort ByAddTimeDesc = Sorts.createSort("addTime@desc");
    public  static Sort ByFinishTimeAsc = Sorts.createSort("finishTime@asc");

    //-------- numeric / id / status ------------
    public  static Sort ByStatusAsc	= Sorts.createSort("status@asc");
    public  static Sort ByGidAsc = Sorts.createSort("gid@asc");
    public  static Sort BySequenceAsc = Sorts.createSort("sequence@asc");
    public  static Sort BySequenceDesc = Sorts.createSort("sequence@desc");
    public  static Sort ByAssetDotNumGoodDesc = Sorts.createSort("asset.numGood@desc");
    public  static Sort ByGoodNumDesc = Sorts.createSort("goodNum@desc");
    public  static Sort ByCorrectNumDesc = Sorts.createSort("correctNum@desc");
    public  static Sort ByGroupSortAsc = Sorts.createSort("groupSort@asc");
    public  static Sort ByWeightDesc = Sorts.createSort("weight@desc");
    public  static Sort ByIsSelected = Sorts.createSort("isSelected@desc");
    public  static Sort ByActiveDesc = Sorts.createSort("clazzLevel+activeScore@desc");
    
    //-------- text 字典序 related ------------
    public  static Sort ByTitleAsc = Sorts.createSort("title@asc");
    public  static Sort ByGroupUserNickNameAsc = Sorts.createSort("user.nickName@asc");
    public  static Sort ByVirtualDataDesc = Sorts.createSort("virtualData@desc");


    public  static Sort ByVersionDesc = Sorts.createSort("version@desc");
    private Sorts() {
    }


    public final static Sort createSort(String sortTuple) {
        if(sortTuple==null || "".equals(sortTuple) || sortTuple.length()<5) {
            return null;
        }

        Sort sort = sortPools.get(sortTuple);
        if(sort==null) {
        	String[] pair = sortTuple.split("@");
    		Direction direction = null;
    		if(pair==null || pair.length!=2) {
    			sort = null;
    		} else if(pair[1].equalsIgnoreCase("asc")) {
    			direction = Direction.ASC;
    		} else if(pair[1].equalsIgnoreCase("desc")) {
    			direction = Direction.DESC;
    		}

    		if(direction!=null){
    			sort = new Sort(direction, pair[0]);
    		}
    		sortPools.put(sortTuple, sort);
        }
        return sort;
    }

    public final static String sort2String(Sort sort) {
        StringBuilder sb = new StringBuilder();
        Iterator<Sort.Order> it = sort.iterator();
        if(it.hasNext()) {
            Sort.Order order = it.next();
            sb.append(order.getProperty()).append("@");
            if(order.getDirection() == Direction.ASC) {
                sb.append("asc");
            } else {
                sb.append("desc");
            }
        }
        return sb.toString();
    }

}
