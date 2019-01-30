package com.xinwei.service;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.shirofunction.ShiroUser;
import com.xinwei.utils.ResponseJson;
import java.util.List;

public interface ShiroUserService extends EntityService<ShiroUser, Long>{
	public List<ShiroUser> findList();
	public ShiroUser findByUsername(String userName);
	public PaginationResult<ShiroUser>  findAllByPage(Pagination<ShiroUser> pagination);
  	public ResponseJson editUser(ShiroUser shiroUser);
 	public ResponseJson del(String ids)throws Exception;
	public ResponseJson editUserRole(String username,String roles);
  	/**
	* 用户审核
   	* @param userNameStr
   	* @return
   	*/
  	public ResponseJson addUser(ShiroUser shiroUser);
	public ShiroUser findByUsernameForUpdate(String username);
  	public ResponseJson checkNameOrEmallExist(ShiroUser shiroUser);
	public ResponseJson audit(String userNameStr,String status) throws Exception;
} 
