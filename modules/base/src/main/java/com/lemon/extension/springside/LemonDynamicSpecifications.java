package com.lemon.extension.springside;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Lists;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月18日 下午9:16:47
 *
 */
public class LemonDynamicSpecifications {

	@SuppressWarnings("rawtypes")
	public static <T> Specification<T> bySearchFilter(final Collection<LemonSearchFilter> filters, final Class<T> entityClazz) {
		return new Specification<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {

					List<Predicate> predicates = Lists.newArrayList();
					for (LemonSearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.operator) {
							case EQ4Char:
								predicates.add(builder.equal(expression,filter.value));
								break;
							case EQ4Int:;
							case EQ:
								if(filter.value.toString().toLowerCase().equals("null")){
									predicates.add(builder.isNull(expression));
								}else {
									predicates.add(builder.equal(expression, filter.value));
								}
								break;
							case LIKE:
								predicates.add(builder.like(expression, "%" + filter.value + "%"));
								break;
							case GT:
								predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
								break;
							case LT:
								predicates.add(builder.lessThan(expression, (Comparable) filter.value));
								break;
							case GTE:
								predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
								break;
							case LTE:
								predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
								break;
							case NE4Int:
							case NE:
								predicates.add(builder.notEqual(expression, filter.value));
								break;
							case Between4Long:
							{
								Object[] pair = (Object[])filter.value;
								Long x = (Long)pair[0];
								Long y = (Long)pair[1];
								predicates.add(builder.between(expression, x, y));
								break;
							}
							case Between4Integer:
							{
								Object[] pair = (Object[])filter.value;
								Integer x = (Integer)pair[0];
								Integer y = (Integer)pair[1];
								predicates.add(builder.between(expression, x, y));
								break;
							}
							case Between4Date:
							{
								Object[] pair = (Object[])filter.value;
								Date x = (Date)pair[0];
								Date y = (Date)pair[1];
								predicates.add(builder.between(expression, x, y));
								break;
							}
							case ChildOf:
								predicates.add(builder.like(expression, filter.value+"%"));
								break;
							case IN:
								predicates.add(builder.in(expression).value(filter.value));
								break;
							case IsNull:
								predicates.add(builder.isNull(expression));
								break;
							case  NotNull:
								predicates.add(builder.isNotNull(expression));
								break;
						}
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
}
