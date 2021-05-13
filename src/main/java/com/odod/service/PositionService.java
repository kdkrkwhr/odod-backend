package com.odod.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import com.odod.domain.es.Position;
import com.odod.domain.es.PositionEsRepository;
import com.odod.dto.PositionRequestDto;
import com.odod.dto.PositionResponseDto;
import com.odod.exception.SavingsException;
import com.odod.util.CommonConstant;

@Service
public class PositionService {

  static final Logger logger = LoggerFactory.getLogger(PositionService.class);

  @Autowired
  private PositionEsRepository repository;

  @Autowired
  private ElasticsearchRestTemplate esTemplate;

  public Map<String, Object> insertPositionData(PositionRequestDto position) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();
    long resultCode;

    try {

      repository.save(Position.builder().userId(position.getUserId()).lon(position.getLon())
          .lat(position.getLat()).logDate(new Date()).build());

      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_SUCC;

    } catch (Exception e) {
      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_FAIL;
      throw new SavingsException("");
    }

    result.put(CommonConstant.ResponseUtil.API_RESULT_CODE_KEY, resultCode);
    return result;
  }

  public SearchHits<PositionResponseDto> selectPositionData(String userId) {
    Query searchQuery = new NativeSearchQueryBuilder()
        .withQuery(QueryBuilders.matchQuery("userId", userId))
        .build();

    return esTemplate.search(searchQuery, PositionResponseDto.class);
  }
}
