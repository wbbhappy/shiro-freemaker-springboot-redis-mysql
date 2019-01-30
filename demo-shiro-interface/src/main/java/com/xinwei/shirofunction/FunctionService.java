package com.xinwei.shirofunction;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.service.EntityService;
import java.util.List;

public interface FunctionService extends EntityService<Function, Long>{
	public List<Function> findList();
	public List<Function> findListByPid(Integer pid);
  	public Function findById(Integer id);
	public Function findByPid(Integer pid);
	public Function findByPermissionName(String permissionName);
	public PaginationResult<Function> findAllByPage(Pagination<Function> pagination);
} 
