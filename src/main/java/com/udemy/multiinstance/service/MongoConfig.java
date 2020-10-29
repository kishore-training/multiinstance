package com.udemy.multiinstance.service;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;



    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "embeded_db";
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {


        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    @Override
    public MongoClient mongoClient() {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        MongoClient client= null;
        mongo.setBindIp(MONGO_DB_URL);
        try {
            client =  mongo.getObject();
        }catch (Exception e){

        }
        return client;
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
}

