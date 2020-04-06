package com.zero.controller;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String index() {
        Map<String, List<ServiceInstance>> msl = new HashMap<>();
        List<String> services = client.getServices();
        for(String service : services){
            List<ServiceInstance> sis = client.getInstances(service);
            msl.put(service,sis);
        }

        return "Hello World";
    }

    @RequestMapping(value="/serviceurl",method= RequestMethod.GET)
    public Map<String,List<ServiceInstance>> discovery() {
        Map<String, List<ServiceInstance>> msl = new HashMap<>();
        List<String> services = client.getServices();
        for(String service : services){
            List<ServiceInstance> sis = client.getInstances(service);
            msl.put(service,sis);
        }
        return msl;
    }
}
