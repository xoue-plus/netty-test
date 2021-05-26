package com.ne.sne;

import com.ne.sne.component.netty.server.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class SNeApplication implements CommandLineRunner {
    @Autowired
    private NettyClient demo;
    public static void main(String[] args) {
        SpringApplication.run(SNeApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("localhost",9900);
        demo.start();
    }
}
