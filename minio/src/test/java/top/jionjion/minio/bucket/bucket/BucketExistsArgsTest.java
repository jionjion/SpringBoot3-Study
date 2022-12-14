package top.jionjion.minio.bucket.bucket;

import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * BucketExistsArgs 测试存储桶是否存在
 *
 * @author Jion
 */
@Slf4j
class BucketExistsArgsTest {

    private MinioClient minioClient;

    @BeforeEach
    void init() {
        // 创建连接
        this.minioClient =
                MinioClient.builder()
                        .endpoint("http://127.0.0.1:9000")
                        .credentials("minioadmin", "minioadmin")
                        .build();
    }

    @Test
    void bucketExists() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found =
                minioClient.bucketExists(io.minio.BucketExistsArgs.builder().bucket("cache").build());
        if (found) {
            log.info("cache 存在");
        } else {
            log.info("cache 不存在");
        }
    }
}
