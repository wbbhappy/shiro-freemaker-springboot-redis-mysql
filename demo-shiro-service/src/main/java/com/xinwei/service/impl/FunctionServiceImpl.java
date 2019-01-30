package com.xinwei.service.impl;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.common.page.SpecificationUtil;
import com.xinwei.dao.FunctionDao;
import com.xinwei.shirofunction.Function;
import com.xinwei.shirofunction.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FunctionServiceImpl extends EntityServiceImpl<Function, Long, FunctionDao> implements FunctionService {

 	public List<Function> findList(){
		return entityDao.findList();
 	}

 	public Function findById(Integer id){
		return entityDao.findOne(id);
 	}

	public Function findByPermissionName(String permissionName){
		return entityDao.findByPermissionName(permissionName);
	}

  	public Function findByPid(Integer pid){
		return entityDao.findByPid(pid);
	}

  	public List<Function> findListByPid(Integer pid){
		return entityDao.findListByPid(pid);
  	}

 	public  PaginationResult<Function>  findAllByPage(Pagination<Function> pagination){
   		SpecificationUtil<Function> specificationUtil = new SpecificationUtil<Function>();
       	Map<String, Object> searchParams = new HashMap<String, Object>();
       	List<String> properties = new ArrayList<String>();
       	properties.add("id");
  	    Sort sort = new Sort(Direction.DESC, properties);
  	    sort = pagination.dataTableOrder(pagination,sort,properties);
  	    String id =  pagination.getExtra_search();
  	    searchParams.put("EQS_id", id);
       	Specification<Function> specification = specificationUtil.buildSpecification(searchParams,"and");
      	// int page= pagination.getStart()/pagination.getPageSize();
       	PageRequest PageRequest = null;//new PageRequest( page,pagination.getPageSize(), sort);
       	Page<Function> list = entityDao.findAll(specification, PageRequest);
	 	PaginationResult<Function> pa = new PaginationResult();
	 	pa.setData(list.getContent());
	 	pa.setRecordsFiltered(list.getTotalElements());
	 	pa.setRecordsTotal(list.getTotalElements());
	 	pa.setDraw(new Integer(pagination.getDraw()));
       	return pa;
  	}
	@Autowired
	public void setEntityDao(FunctionDao entityDao) {
		this.entityDao = entityDao;
	}
}
