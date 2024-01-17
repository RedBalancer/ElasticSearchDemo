package xx.yy.zz.esdemo.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhraseQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName KibanaService.java
 * @Description @TODO
 * @createTime 2024年01月12日 13:45:00
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class KibanaService {
    private final ElasticsearchClient esClient;

    public String findTraceId(HashMap<String, String> keys, String index, String dateTime) {
        BoolQuery.Builder booleanQuery = new BoolQuery.Builder();
        keys.forEach((k,v)->booleanQuery.must(MatchPhraseQuery.of(m->m.field(k).query(v))._toQuery()));

        SearchRequest.Builder searchBuilder = new SearchRequest.Builder()
                .index(index)
                .query(q->q.bool(b->b.must(booleanQuery.build()._toQuery())));

        SearchResponse<JsonData> searchResult = null;
        try {
            searchResult = esClient.search(searchBuilder.build(), JsonData.class);
        } catch (IOException e) {
            log.error("Error: " + e.getMessage());
        }
        List<String> traceIds = searchResult.hits().hits().stream()
                .filter(h->h.source()!=null)
                .map(h->h.source().toJson().asJsonObject().get("trace_id").toString())
                .collect(Collectors.toList());
        if(!ObjectUtils.isEmpty(traceIds)) {
            return traceIds.get(0);
        }
        return null;
    }

    public String getTraceId(String value) {
        var keys = new HashMap<String, String>();
        keys.put("level", "INFO");
        keys.put("message", value);

        return findTraceId(keys, "*", null);
    }
}
