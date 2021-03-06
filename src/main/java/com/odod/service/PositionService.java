package com.odod.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.odod.domain.position.Position;
import com.odod.domain.position.PositionEsRepository;
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

  @Autowired
  private RestHighLevelClient client;

  public long insertPositionData(PositionRequestDto position) {
    long resultCode;

    try {

      repository.save(Position.builder().userId(position.getUserId()).lon(position.getLon())
          .lat(position.getLat()).logDate(new Date()).speed(position.getSpeed())
          .accuracy(position.getAccuracy()).build());

      resultCode = CommonConstant.Response.API_RESULT_CODE_SUCC;

    } catch (Exception e) {
      resultCode = CommonConstant.Response.API_RESULT_CODE_FAIL;
      throw new SavingsException("position data insert fail");
    }

    return resultCode;
  }

  public SearchHits<PositionResponseDto> selectPositionDataFromTo(SearchPositionDto dto) throws IOException {
    RangeQueryBuilder dateSearchQuery = QueryBuilders.rangeQuery("logDate").from(dto.getFrom())
        .to(dto.getTo()).includeLower(true).includeUpper(false);

    Query searchQuery = new NativeSearchQueryBuilder().withQuery(dateSearchQuery).build();
    
    return esTemplate.search(searchQuery, PositionResponseDto.class);
  }

  public List<SearchHits<PositionResponseDto>> selectPositionData(SearchPositionDto dto) throws IOException {
    List<Query> queryList = new ArrayList<>();

    QueryBuilder userIdSearchQuery = QueryBuilders.matchQuery("userId", dto.getEmail());
    RangeQueryBuilder dateSearchQuery = QueryBuilders.rangeQuery("logDate").from(dto.getFrom())
        .to(dto.getTo()).includeLower(true).includeUpper(false);

    Query searchQueryUserId = new NativeSearchQueryBuilder().withQuery(userIdSearchQuery).build();
    Query searchQueryDate = new NativeSearchQueryBuilder().withQuery(dateSearchQuery).build();

    queryList.add(searchQueryUserId);
    queryList.add(searchQueryDate);

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

    return esTemplate.multiSearch(queryList, PositionResponseDto.class);
  }
}
