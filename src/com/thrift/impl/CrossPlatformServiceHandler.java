package com.thrift.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

public class CrossPlatformServiceHandler implements CrossPlatformService.Iface {
    private ArrayList<CrossPlatformResource> resources;

    CrossPlatformServiceHandler() {
        this.resources = new ArrayList<>();
    }

    @Override
    public CrossPlatformResource get(int id) throws InvalidOperationException, TException {
        if (resources == null) {
            throw new InvalidOperationException(404, "Resource not found!");
        }

        return resources.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(() -> new InvalidOperationException());
    }

    @Override
    public void save(CrossPlatformResource resource) throws InvalidOperationException, TException {
        this.resources.add(resource);
    }

    @Override
    public List<CrossPlatformResource> getList() throws InvalidOperationException, TException {
        return this.resources;
    }

    @Override
    public boolean ping() throws InvalidOperationException, TException {
        return true;
    }
}
