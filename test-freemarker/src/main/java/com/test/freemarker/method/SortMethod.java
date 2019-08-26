package com.test.freemarker.method;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class SortMethod implements TemplateMethodModelEx{

	@Override
	public Object exec(List arguments) throws TemplateModelException {

		// 获取第一个请求参数
		// 参数先转freemarker数据类型
		SimpleSequence arg0 = (SimpleSequence) arguments.get(0);
		List<BigDecimal> list = arg0.toList();
		
		Collections.sort(list, new Comparator<BigDecimal>() {

			@Override
			public int compare(BigDecimal o1, BigDecimal o2) {
				return o1.compareTo(o2);
			}
		});

		return list;
	}

}
