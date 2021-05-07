package com.odod.domain.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionEsRepository extends ElasticsearchRepository<Position, String> {

}
