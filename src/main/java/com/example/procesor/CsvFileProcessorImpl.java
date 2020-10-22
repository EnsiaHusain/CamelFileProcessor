package com.example.procesor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class CsvFileProcessorImpl extends RouteBuilder {

    @Value("${input.file.location}")
    private String inputFileLocation;

    @Value("${output.file.location}")
    private String outputFilePath;

    @Override
    public void configure() throws Exception{
        Integer sum = 0;
        CsvDataFormat csvDataFormat = new CsvDataFormat();
        csvDataFormat.setDelimiter(",");


        from("file:"+inputFileLocation).unmarshal(csvDataFormat).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                String values = exchange.getIn().getBody(String.class);

               String[] strings = values.split(",");
                Integer sum = 0;
                for(String ss:strings){
                    sum =sum+Integer.parseInt(ss);
                }
                exchange.getIn().setBody(sum);
            }
        }).marshal().csv().to("file:"+outputFilePath).end();

    }

}
