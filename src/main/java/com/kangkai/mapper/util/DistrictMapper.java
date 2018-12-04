package com.kangkai.mapper.util;

import java.util.List;
import java.util.Map;

import com.kangkai.pojo.District;

public interface DistrictMapper {

	List<District> selectAll(Map<String, Object> map);

	List<District> selectAll2(Map<String, Object> map);


}
