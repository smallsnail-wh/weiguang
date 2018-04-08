package com.wh.weiguang.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.RoleDao;
import com.wh.weiguang.model.sys.RoleEntity;
import com.wh.weiguang.service.sys.RoleService;

@Service(value = "roleServiceImpl")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<RoleEntity> rolesList(int pageSize, int start) {
		return roleDao.rolesList(pageSize, start);
	}

	@Override
	public Integer rolesSize(int pageSize, int start) {
		return roleDao.rolesSize(pageSize, start);
	}

	@Override
	public void insertRole(RoleEntity roleEntity) {
		roleDao.insertRole(roleEntity);
	}

	@Override
	public void updateRole(RoleEntity roleEntity) {
		roleDao.updateRole(roleEntity);
	}

	@Override
	public void deleteRoles(List<String> groupId) {
		roleDao.deleteRoles(groupId);
	}

	@Override
	public List<RoleEntity> allRoles() {
		return roleDao.allRoles();
	}

}
