package com.odod.service;

import java.io.IOException;
import java.util.Date;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class PositionService {

  static final Logger logger = LoggerFactory.getLogger(PositionService.class);

  @Autowired
  private PositionEsRepository repository;

  @Autowired
  private ElasticsearchRestTemplate esTemplate;

  private JPAQueryFactory factory;

  @Autowired
  private RestHighLevelClient client;

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

  public SearchHits<PositionResponseDto> selectPositionData(SearchPositionDto dto) throws IOException {
    QueryBuilder userIdSearchQuery = QueryBuilders.matchQuery("userId", dto.getEmail());
    RangeQueryBuilder dateSearchQuery = QueryBuilders.rangeQuery("logDate").from(dto.getFrom())
        .to(dto.getTo()).includeLower(true).includeUpper(false);

    Query searchQuery = new NativeSearchQueryBuilder().withQuery(dateSearchQuery).withQuery(userIdSearchQuery).build();

    MultiSearchRequest request = new MultiSearchRequest();
    SearchRequest firstSearchRequest = new SearchRequest();
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchQuery("userId", dto.getEmail()));
    firstSearchRequest.source(searchSourceBuilder);
    request.add(firstSearchRequest);
    SearchRequest secondSearchRequest = new SearchRequest();
    searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.rangeQuery("logDate").from(dto.getFrom()).to(dto.getTo()).includeLower(true).includeUpper(false));
    secondSearchRequest.source(searchSourceBuilder);
    request.add(secondSearchRequest);

    MultiSearchResponse response = client.msearch(request, RequestOptions.DEFAULT);

    return esTemplate.search(searchQuery, PositionResponseDto.class);
  }
}
