package com.example.domain;

import lombok.Data;

/**
 * 番剧热度
 */
@Data
public class TbVideoHeat {
    private Long videoHeatId;
    private Long videoId;
    private Integer weekHeat;
    private Integer monthHeat;
    private Integer yearHeat;
}
