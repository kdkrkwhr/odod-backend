package com.odod.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

  @Value("${spring.es.url}")
  private String ELASTIC_URL;

  @Value("${spring.es.username}")
  private String ELASTIC_USER;

  @Value("${spring.es.password}")
  private String ELASTI_PASSWORD;

  @Bean
  @Override
  public RestHighLevelClient elasticsearchClient() {
    final ClientConfiguration clientConfiguration = 
        ClientConfiguration.builder().connectedTo(ELASTIC_URL).withBasicAuth(ELASTIC_USER, ELASTI_PASSWORD).build();

    return RestClients.create(clientConfiguration).rest();
  }
}
