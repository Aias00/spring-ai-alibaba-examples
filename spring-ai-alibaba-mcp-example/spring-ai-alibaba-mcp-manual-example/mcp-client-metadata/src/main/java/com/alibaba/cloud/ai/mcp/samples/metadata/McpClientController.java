package com.alibaba.cloud.ai.mcp.samples.metadata;

import com.google.common.collect.Maps;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class McpClientController {
    
    @GetMapping("/mcp/client/metadata")
    public String clientMetadata() {
//        latitude=39.9042&longitude=116.4074
        String url = "http://localhost:7878/sse";
        Map<String, String> headers = Maps.newHashMap();
        headers.put("demoKey", "demoValue");
        // 构建 transport 和 client
        HttpClientSseClientTransport.Builder transportBuilder = HttpClientSseClientTransport.builder(url);
        transportBuilder.customizeRequest(req -> headers.forEach(req::header));
        HttpClientSseClientTransport transport = transportBuilder.build();
        McpSyncClient client = McpClient.sync(transport).build();
        client.initialize();
        return null;
    }
}
