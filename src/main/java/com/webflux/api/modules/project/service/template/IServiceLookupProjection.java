package com.webflux.api.modules.project.service.template;


import com.webflux.api.modules.project.core.dto.ResultProjectTasks;
import reactor.core.publisher.Flux;

public interface IServiceLookupProjection {
  Flux<ResultProjectTasks> findAllProjectTasks();
}