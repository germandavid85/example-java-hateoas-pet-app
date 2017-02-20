package com.kadmandu.petme;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories
@PropertySources(value = { @PropertySource("application.properties") })
public class MongoConfiguration extends AbstractMongoConfiguration
{
    /**
     * The base package to scan for persistence objects.
     */
    private static final List<String> MAPPING_BASE_PACKAGE = Collections.singletonList("com.kadmandu.petme.repository");

    @Value("${spring.data.mongodb.dbname}")
    private String dbName;

    @Value("${spring.data.mongodb.host}")
    private String dbHost;

    @Value("${spring.data.mongodb.port}")
    private int dbPort;

    @Override
    protected String getDatabaseName()
    {
        return dbName;
    }

    @Override
    public Mongo mongo() throws UnknownHostException
    {
        return new MongoClient(new ServerAddress(dbHost, dbPort));
    }

    @Override
    protected List<String> getMappingBasePackages()
    {
        return MAPPING_BASE_PACKAGE;
    }

}