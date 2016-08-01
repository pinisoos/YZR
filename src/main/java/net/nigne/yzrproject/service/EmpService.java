package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.EmpVO;

public interface EmpService {
	
	public List<EmpVO> getList();
	public void empInsert(int no) throws Exception;
	
}
