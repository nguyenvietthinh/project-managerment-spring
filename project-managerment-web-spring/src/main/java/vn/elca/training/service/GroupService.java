package vn.elca.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.elca.training.dao.IGroupRepository;
import vn.elca.training.dom.Group;

public class GroupService implements IGroupService{
	 @Autowired
	    private IGroupRepository groupRepository;
	@Override
	public List<Group> findAll() {
		
		return groupRepository.findAll();
	}

}
