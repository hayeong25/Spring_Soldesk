package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.mapper.SampleMapper1;

@Service("service")
public class SampleServiceImpl implements SampleService {
	@Autowired
	private SampleMapper1 mapper1;
	
	@Autowired
	private SampleMapper1 mapper2;
	
	@Transactional // mapper1.insert와 mapper2.insert 모두 성공해야 commit. 그렇지 않으면 모두 rollback
	@Override
	public void addData(String data) {
		mapper1.insertCol1(data);
		mapper2.insertCol1(data);
	}

}