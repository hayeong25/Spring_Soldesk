package com.study.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.dto.ItemDTO;
import com.study.mapper.ItemMapper;

@Service("service")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper mapper;

	@Override
	public List<ItemDTO> select(int num) {
		return mapper.select(num);
	}

	@Override
	public boolean insert(ItemDTO insertDTO) {
		return mapper.insert(insertDTO) == 1? true : false;
	}

	@Override
	public boolean update(int num, String psize, int price) {
		return mapper.update(num, psize, price) == 1 ? true : false;
	}

	@Override
	public boolean delete(int num) {
		return mapper.delete(num) == 1 ? true : false;
	}

	@Override
	public List<ItemDTO> selectAll() {
		return mapper.selectAll();
	}
}