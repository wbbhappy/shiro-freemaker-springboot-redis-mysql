package com.xinwei.service;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.shirofunction.ShiroRole;
import java.util.List;

public interface ShiroRoleService extends EntityService<ShiroRole, Long>{
	public List<ShiroRole> findRoleList();
	public ShiroRole findById(Integer id);
	public ShiroRole findByRoleName(String rolename);
	public  PaginationResult<ShiroRole>  findAllByPage(Pagination<ShiroRole> pagination);
} 
