package com.webflux.api.core.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

// ========================== PropertySource + ConfigurationProperties =============================
// Check - PropertySource: https://www.baeldung.com/configuration-properties-in-spring-boot
// Getter+Setter are CRUCIAL for PropertySource + ConfigurationProperties works properly
@PropertySource(value = "classpath:application-prod.yml", factory = YamlFileConverter.class)
@ConfigurationProperties(prefix = "udemy.mongodb.replicaset")
@Setter
@Getter
// =================================================================================================
@Slf4j
@Profile("prod")
@Configuration
@EnableReactiveMongoRepositories(
     basePackages = {
          "com.webflux.api.modules.project.repo",
          "com.webflux.api.modules.task.repo"})
public class ConfigDbProd extends AbstractReactiveMongoConfiguration {

  private String username;
  private String password;
  private String database;
  private String name;
  private String primary;
  private String port;
  private String authenticationDatabase;


  // 01) REACTIVE-MONGO-TEMPLATE-BEANS:
  @Override
  public MongoClient reactiveMongoClient() {
/*
     ╔═══════════════════════════════╗
     ║      REPLICASET-MONGO-DB      ║
     ╚═══════════════════════════════╝
*/
    String connectionURI = "mongodb://"
         + username + ":" + password +
         "@" + primary + ":" + port + "/"
         + database
         + "?replicaSet=" + name
         + "&authSource=" + authenticationDatabase;

    String connectionURI2 = "mongodb://"
         + username + ":" + password + "@"
         + primary + ":" + port + ","
         + username + ":" + password + "@"
         + "node2" + ":" + "9142,"
         + username + ":" + password + "@"
         + "node3" + ":" + "9242/"
         + database
         + "?replicaSet=" + name
         + "&authSource=" + authenticationDatabase;

    System.out.println("Connection ------>  URI ------> :" + connectionURI);
    System.out.println("Connection ------>  URI ------> :" + connectionURI2);

    return MongoClients.create(connectionURI);
  }


  @Override
  protected String getDatabaseName() {

    return database;
  }


  @Bean
  public ReactiveMongoTemplate reactiveRepoTemplate() {

    return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
  }

  /*
   ╔════════════════════════════════════════════════╗
   ║           TRANSACTION-MANAGER-BEAN             ║
   ╠════════════════════════════════════════════════╣
   ║ THIS TRANSACTION-MANAGER-BEAN IS NECESSARY IN: ║
   ║ A) APP-CONTEXT  -> @Configuration              ║
   ║    - SRC/MAIN/JAVA/com/webflux/api/core/config ║
   ║                                                ║
   ║ B) TEST-CONTEXT -> @TestConfiguration          ║
   ║    - SRC/TEST/JAVA/com/webflux/api/core/config ║
   ╚════════════════════════════════════════════════╝
  */
  @Bean
  ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory factory) {

    return new ReactiveMongoTransactionManager(factory);
  }


  // 03) GRID-FS-BEANS:
  //  @Bean
  //  public ReactiveGridFsTemplate reactiveGridFsTemplate() throws Exception {
  //    return new ReactiveGridFsTemplate(reactiveMongoDbFactory(),mongoConverter);
  //  }
}