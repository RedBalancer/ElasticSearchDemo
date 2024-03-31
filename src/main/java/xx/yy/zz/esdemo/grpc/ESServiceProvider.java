package xx.yy.zz.esdemo.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import xx.yy.zz.es.interfaces.EsRequest;
import xx.yy.zz.es.interfaces.EsResponse;
import xx.yy.zz.es.services.EsServicesGrpc;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName ESServiceProvider.java
 * @Description @TODO
 * @createTime 2024年02月26日 10:42:00
 */

//@RequiredArgsConstructor
@GrpcService
@Slf4j
public class ESServiceProvider extends EsServicesGrpc.EsServicesImplBase {
//    private final ManagedChannel managedChannel;

    @Override
    public void retrieveTraceId(EsRequest request, StreamObserver<EsResponse> responseObserver) {
        String keys = request.getKeys();
        String index = request.getIndex();
        log.debug("Input request keys: {}, index: {}", keys, index );

        EsResponse esResponse = EsResponse.newBuilder()
                .setTraceId( "my_trace_id")
                .setStatus(true)
                .build();

        responseObserver.onNext(esResponse);
        responseObserver.onCompleted();
    }
}
