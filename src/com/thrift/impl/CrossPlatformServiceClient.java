package com.thrift.impl;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

public class CrossPlatformServiceClient {
    public static void main(String[] args) {
        TTransport transport = null;

        try {
            transport = new TSocket("localhost", 9090); // Địa chỉ server
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            CrossPlatformService.Client client = new CrossPlatformService.Client(protocol);

            // Gọi các phương thức của service
            boolean alive = client.ping();
            System.out.println("Ping response: " + alive);

            CrossPlatformResource resource = new CrossPlatformResource();
            resource.setId(1);
            resource.setName("Test Resource");
            resource.setSalutation("Hello!");

            client.save(resource);
            System.out.println("Resource saved successfully.");

            CrossPlatformResource fetched = client.get(1);
            System.out.println("Fetched resource: " + fetched.getName());

            List<CrossPlatformResource> resources = client.getList();
            System.out.println("Resource list size: " + resources.size());

        } catch (InvalidOperationException e) {
            System.err.println("InvalidOperationException: " + e.getDescription());
        } catch (TTransportException e) {
            System.err.println("Transport error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                transport.close();
            }
        }
    }
}
