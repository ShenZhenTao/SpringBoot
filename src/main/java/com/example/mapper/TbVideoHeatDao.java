package com.example.mapper;

import com.example.entity.TbVideoHeat;

public interface TbVideoHeatDao {
    TbVideoHeat selectTbVideoHeatById(Long videoHeatId);
    TbVideoHeat selectTbVideoHeatList(TbVideoHeat tbVideoHeat);

}
