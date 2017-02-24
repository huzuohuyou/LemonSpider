package com.lemon.ds.base.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lemon.commons.Cons;
import com.lemon.commons.Cons.Pagination;
import com.lemon.commons.Lemon;
import com.lemon.commons.log.Log;
import com.lemon.commons.querylistpage.Sorts;
import com.lemon.ds.base.dao.LemonRepo;
import com.lemon.extension.springside.LemonDynamicSpecifications;
import com.lemon.extension.springside.LemonSearchFilter;

@Service
public abstract class LemonService<T, ID extends Serializable, DAO extends LemonRepo<T, ID>> extends Lemon {
	protected final static Log log = Log.getLogger(LemonService.class);

	@Autowired(required = false)
	protected DAO serviceDao;

	public T saveEntity(T entity) {
		if (entity != null)
			return serviceDao.save(entity);
		return null;
	}

	public T findOneById(ID id) {
		return serviceDao.findOne(id);
	}

	public boolean exists(ID id) {
		return serviceDao.exists(id);
	}

	public Iterable<T> findAll() {
		return serviceDao.findAll();
	}

	public long count() {
		return serviceDao.count();
	}

	public void delete(ID id) {
		if (id != null)
			serviceDao.delete(id);
	}

	public void delete(T entity) {
		if (entity != null)
			serviceDao.delete(entity);
	}

	public void delete(Iterable<? extends T> entities) {
		if (entities != null)
			serviceDao.delete(entities);
	}

	@Override
	public String toString() {
		return "Service: " + ToStringBuilder.reflectionToString(this);
	}

	// ------------------------- 创建分页请求 -------------------------

	protected PageRequest buildPageRequest(int pageNum) {
		return buildPageRequest(pageNum, Pagination.DefaultPageSize, Sorts.ByIdDesc);
	}

	protected PageRequest buildPageRequest(int pageNum, int pageSize) {
		return buildPageRequest(pageNum, pageSize, Sorts.ByIdDesc);
	}

	protected final PageRequest buildPageRequest(int pageNum, int pageSize, String sortTuple) {
		return buildPageRequest(pageNum, pageSize, Sorts.createSort(sortTuple));
	}

	protected PageRequest buildPageRequest(int pageNum, int pageSize, Sort sort) {
		if (sort == null) {
			return new PageRequest(pageNum - 1, pageSize);
		} else {
			return new PageRequest(pageNum - 1, pageSize, sort);
		}
	}

	protected PageRequest buildPageRequest(Map<String, Object> pagingParams) {
		Integer pageSize = Cons.Pagination.DefaultPageSize;
		if (pagingParams.containsKey(Pagination.KeyPageSize)) {
			try {
				String ss = (String) pagingParams.get(Pagination.KeyPageSize);
				if (ss != null && !"".equals(ss))
					pageSize = Integer.parseInt(ss);
			} catch (Exception e) {
				pageSize = Pagination.DefaultPageSize;
			}
		}
		Integer pageNum = 1;
		if (pagingParams.containsKey(Pagination.KeyPageNum)) {
			try {
				String ss = (String) pagingParams.get(Pagination.KeyPageNum);
				if (ss != null && !"".equals(ss))
					pageNum = Integer.parseInt(ss);
			} catch (Exception e) {
				pageNum = 1;
			}
		}

		Sort sort = null;
		if (pagingParams.containsKey(Pagination.KeySortBy)) {
            String ss = (String)pagingParams.get(Pagination.KeySortBy);
            if(ss!=null) {
                try {
                    sort = Sorts.createSort(ss);
                } catch (Exception e) {
                    sort = Sorts.ByIdDesc;
                }
            }
		}
		PageRequest pageRequest = buildPageRequest(pageNum, pageSize, sort);
		return pageRequest;
	}

	// 该方法没什么用，就是防止子类创建这样的方法
	// protected final Specification<T> buildSpecification(Map<String, Object>
	// searchParams) {
	// return null;
	// }

	// ------------------------- 创建通用分页查询条件组合 -------------------------

	protected Specification<T> buildSpecification(final Class<T> entityClazz, Map<String, Object> searchParams) {
		Map<String, LemonSearchFilter> filters = LemonSearchFilter.parse(searchParams);
		Specification<T> spec = LemonDynamicSpecifications.bySearchFilter(filters.values(), entityClazz);
		return spec;
	}

	protected Page<T> doFindBySpec(final Class<T> entityClazz, Map<String, Object> searchParams,
			PageRequest pageRequest) {
		Specification<T> spec = buildSpecification(entityClazz, searchParams);
		return serviceDao.findAll(spec, pageRequest);
	}

	public Page<T> findBySpec(final Class<T> entityClazz, Map<String, Object> searchParams, int pageNum) {
		return doFindBySpec(entityClazz, searchParams, buildPageRequest(pageNum));
	}

	/**
	 * 
	 * @param entityClazz
	 * @param searchParams
	 * @param pageNum
	 * @param pageSize
	 * @param sort
	 * @return
	 */
	public Page<T> findBySpec(final Class<T> entityClazz, Map<String, Object> searchParams, int pageNum, int pageSize,
			Sort sort) {
		return doFindBySpec(entityClazz, searchParams, buildPageRequest(pageNum, pageSize, sort));
	}

	/**
	 * 通用的查询基类，参数依次为：类、所有查询条件的map、所有分页相关的map、所有排序相关的List（排序可以有多个，但为了性能考虑，最好传一个）
	 * 
	 * @param entityClazz
	 * @param searchParams
	 * @param pagingParams
	 * @return
	 */
	public Page<T> findBySpec(final Class<T> entityClazz, Map<String, Object> searchParams,
			Map<String, Object> pagingParams) {
		return doFindBySpec(entityClazz, searchParams, buildPageRequest(pagingParams));
	}


	public Page<T> findBySpec(Map<String, Object> searchParams,
							  Map<String, Object> pagingParams) {
		Class<T> entityClass = null;
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<T>) p[0];
		}
		if (entityClass != null) {
			return findBySpec(entityClass, searchParams, pagingParams);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Page<T> findBySpec(Map<String, Object> searchParams, int pageNum) {
		Class<T> entityClass = null;
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<T>) p[0];
		}
		if (entityClass != null) {
			return findBySpec(entityClass, searchParams, pageNum);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Page<T> findBySpec(Map<String, Object> searchParams, int pageNum, int pageSize, Sort sort) {
		Class<T> entityClass = null;
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<T>) p[0];
		}
		if (entityClass != null) {
			return findBySpec(entityClass, searchParams, pageNum, pageSize, sort);
		}
		return null;
	}

	// ------------------------- 创建通用全部List查询条件组合 -------------------------

	@SuppressWarnings("unchecked")
	public List<T> findAll(Map<String, Object> searchParams, Sort sort) {
		Class<T> entityClass = null;
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<T>) p[0];
		}
		if (entityClass != null) {
			Specification<T> spec = buildSpecification(entityClass, searchParams);
			return serviceDao.findAll(spec, sort);
		}
		return null;
	}

	public List<T> findListBySpec(final Class<T> entityClazz, Map<String, Object> searchParams) {
		Specification<T> spec = buildSpecification(entityClazz, searchParams);
		return serviceDao.findAll(spec);
	}
}
