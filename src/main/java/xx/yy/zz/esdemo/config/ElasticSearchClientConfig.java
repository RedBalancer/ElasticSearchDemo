package xx.yy.zz.esdemo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientOptions;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName ElasticSearchClientConfig.java
 * @Description @TODO
 * @createTime 2024年01月12日 11:03:00
 */

@Configuration
public class ElasticSearchClientConfig {
    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private int port;

    @Value("${es.schema}")
    private String schema;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient client = RestClient.builder(new HttpHost(host, port, schema))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    return httpClientBuilder.addInterceptorLast(
                            (HttpResponseInterceptor) (resp, context) -> {
                                resp.addHeader("X-Elastic-Product", "Elasticsearch");
                            });
                }).build();

        RequestOptions requestOptions = RequestOptions.DEFAULT.toBuilder()
                .addHeader("Context-Type", "application/json")
                .addHeader("X-Elastic-Product", "Elasticsearch")
                .build();

        JacksonJsonpMapper mapper = new JacksonJsonpMapper();
        mapper.objectMapper().registerModule(new JavaTimeModule());

        ElasticsearchTransport transport = new RestClientTransport(client, mapper, new RestClientOptions(requestOptions));

        return new ElasticsearchClient(transport);
    }

}
