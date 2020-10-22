package com.example.procesor;

import junit.framework.TestCase;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;


@SpringBootTest
class ProcesorApplicationTests extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() throws Exception{
        return new CsvFileProcessorImpl();
    }

    @Test
    public void checkIfFileExistsInOutputDir() throws InterruptedException{

        Thread.sleep(5000);
        File file = new File("src/test/resources/output/");

        assertTrue(file.isDirectory());
        assertNotNull(file.listFiles());
        assertEquals(1,file.listFiles().length);

    }
}
