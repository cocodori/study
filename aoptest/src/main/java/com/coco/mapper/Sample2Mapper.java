package com.coco.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample2Mapper {
	@Insert("INSERT INTO tbl_sample2(col2) values(#{data})")
	public int insertCol2(String data);
}
