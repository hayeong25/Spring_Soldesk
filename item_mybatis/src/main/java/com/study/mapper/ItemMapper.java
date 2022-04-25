package com.study.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.study.dto.ItemDTO;

public interface ItemMapper {
	public List<ItemDTO> select(int num);
	public int insert(ItemDTO insertDTO);
	public int update(@Param("num")int num, @Param("psize")String psize, @Param("price")int price);
	public int delete(int num);
	public List<ItemDTO> selectAll();
}