package com.odod.service;

import java.util.Date;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
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
import com.odod.dto.SearchPositionDto;
import com.odod.exception.SavingsException;
import com.odod.util.CommonConstant;

@Service
public class PositionService {

  static final Logger logger = LoggerFactory.getLogger(PositionService.class);

  @Autowired
  private PositionEsRepository repository;

  @Autowired
  private ElasticsearchRestTemplate esTemplate;

  public long insertPositionData(PositionRequestDto position) {
    long resultCode;

    try {

      repository.save(Position.builder().userId(position.getUserId()).lon(position.getLon())
          .lat(position.getLat()).logDate(new Date()).speed(position.getSpeed())
          .accuracy(position.getAccuracy()).build());

      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_SUCC;

    } catch (Exception e) {
      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_FAIL;
      throw new SavingsException("position data insert fail");
    }

    return resultCode;
  }

  public SearchHits<PositionResponseDto> selectPositionData(SearchPositionDto dto) {
    QueryBuilder userIdSearchQuery = QueryBuilders.matchQuery("userId", dto.getEmail());
    RangeQueryBuilder dateSearchQuery = QueryBuilders.rangeQuery("logDate").from(dto.getFrom()).to(dto.getTo())
        .includeLower(true).includeUpper(false);

    Query searchQuery = new NativeSearchQueryBuilder()
        .withQuery(dateSearchQuery)
        .withQuery(userIdSearchQuery)
        .build();

    return esTemplate.search(searchQuery, PositionResponseDto.class);
  }
}
