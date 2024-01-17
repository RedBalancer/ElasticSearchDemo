package xx.yy.zz.esdemo.service;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ShardStatistics;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class KibanaServiceTest {
    @Mock
    private ElasticsearchClient esClient;

    @InjectMocks
    private KibanaService kibanaService;

    private String index = "logast*";

    private HashMap<String, String> keys = new HashMap<>();
    private JsonData jsonData = null;
    private SearchResponse<JsonData> response = null;

    @BeforeEach
    void init() {
        keys.put("level", "INFO");
        keys.put("message", "test");
        jsonData = JsonData.fromJson("{\"trace_id\": 123456}");
        response = SearchResponse.of(
                b->b.took(100).timedOut(false).shards(ShardStatistics.of(b1->b1.total(1).failed(0).successful(1)))
                        .hits(mb->mb.hits(jsonBuilder->jsonBuilder.index(index).id("someid").source(jsonData))));
    }

    @Test
    public void testFindTraceId() throws IOException {
        when(esClient.search(any(SearchRequest.class), eq(JsonData.class))).thenReturn(response);
        String traceId = kibanaService.findTraceId(keys, index, null);
        assertEquals("123456", traceId);
    }

    @Test
    public void testGetTraceId() throws IOException {
        String value = "test";

        when(esClient.search(any(SearchRequest.class), eq(JsonData.class))).thenReturn(response);
        String traceId = kibanaService.getTraceId(value);
        assertEquals("123456", traceId);
    }
}