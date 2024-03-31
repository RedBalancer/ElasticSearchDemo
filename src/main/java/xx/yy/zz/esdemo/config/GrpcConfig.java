package xx.yy.zz.esdemo.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName GRpcConfig.java
 * @Description @TODO
 * @createTime 2024年02月26日 10:43:00
 */

@Configuration
public class GrpcConfig {
    @Value("${grpc.server.host}")
    private String grpcHost;

    @Value("${grpc.server.port}")
    private int grpcPort;

    @Bean(name="managedChannel")
    ManagedChannel getChannel() {
        return ManagedChannelBuilder.forAddress(grpcHost, grpcPort).usePlaintext().build();
    }
}
