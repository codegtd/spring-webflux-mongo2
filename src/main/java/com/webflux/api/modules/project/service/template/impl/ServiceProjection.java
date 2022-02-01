package com.webflux.api.modules.project.service.template.impl;

import com.webflux.api.modules.project.core.dto.ProjectDto;
import com.webflux.api.modules.project.repo.template.RepoProjection;
import com.webflux.api.modules.project.service.template.IServiceProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Service("serviceProjection")
public class ServiceProjection implements IServiceProjection {

  @Autowired
  RepoProjection repoProjection;

  @Override
  public Flux<ProjectDto> findAllProjectDto() {

    ProjectionOperation projection =
         project()
              .andExpression("name")
              .as("name")
              .andExpression("desc")
              .as("description");

    Aggregation aggregation = newAggregation(projection);

    return this.repoProjection.aggreg(aggregation, "project", ProjectDto.class);

  }

}