package com.study.service;

import java.util.List;

import com.study.dto.ItemDTO;

public interface ItemService {
	public List<ItemDTO> select(int num);
	public boolean insert(ItemDTO insertDTO);
	public boolean update(int num, String psize, int price);
	public boolean delete(int num);
	public List<ItemDTO> selectAll();
}