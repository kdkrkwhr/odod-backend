package com.odod.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.odod.domain.es.Position;
import com.odod.domain.es.PositionEsRepository;
import com.odod.dto.PositionResponseDto;
import com.odod.util.CommonConstant;

@Service
public class PositionService {

  static final Logger logger = LoggerFactory.getLogger(PositionService.class);

  @Resource
  PositionEsRepository repository;

  public Map<String, Object> insertPositionData(PositionResponseDto position) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();
    long resultCode = 0;

    try {

      repository.save(Position.builder().userId(position.getUserId()).lon(position.getLon())
          .lat(position.getLat()).logDate(new Date()).build());

      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_SUCC;

    } catch (Exception e) {
      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_FAIL;
    }

    result.put(CommonConstant.ResponseUtil.API_RESULT_CODE_KEY, resultCode);
    return result;
  }
}
