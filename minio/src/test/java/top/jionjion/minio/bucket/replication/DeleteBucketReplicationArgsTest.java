package top.jionjion.minio.bucket.replication;

import io.minio.DeleteBucketReplicationArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * DeleteBucketReplicationArgs 删除存储桶的存储桶复制配置
 *
 * @author Jion
 */
class DeleteBucketReplicationArgsTest {


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
    void deleteBucketReplication() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.deleteBucketReplication(
                DeleteBucketReplicationArgs.builder().bucket("cache").build());

    }
}
