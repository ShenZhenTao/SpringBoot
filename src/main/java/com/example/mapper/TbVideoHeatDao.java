package com.example.mapper;

import com.example.domain.TbVideoHeat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbVideoHeatDao {
    TbVideoHeat selectTbVideoHeatById(Long videoHeatId);
    TbVideoHeat selectTbVideoHeatList(TbVideoHeat tbVideoHeat);

}
