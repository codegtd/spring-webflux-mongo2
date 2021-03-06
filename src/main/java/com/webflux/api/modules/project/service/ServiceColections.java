package com.webflux.api.modules.project.service;

import com.webflux.api.modules.project.entity.ProjectChild;
import com.webflux.api.modules.project.repo.TemplColections;
import com.webflux.api.modules.task.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("serviceColections")
@AllArgsConstructor
public class ServiceColections implements IServiceColections {


  TemplColections repoColections;

  /*╔══════════════════════════════════╗
    ║ REACTIVE-MONGO-TEMPLATE-CRITERIA ║
    ╚══════════════════════════════════╝*/

  @Override
  public Mono<ProjectChild> addTemplMultCollections(Mono<Task> task) {

    return repoColections.addTemplMultCollections(task);
  }

  @Override
  public Mono<ProjectChild> updateTemplMultCollections(Mono<Task> task) {

    return repoColections.updateTemplMultCollections(task);
  }

  @Override
  public Mono<ProjectChild> DeleteTemplMultCollections(
       String projectId,
       String taskId) {

    return repoColections.deleteTemplMultCollections(projectId, taskId);
  }


  @Override
  public Mono<Void> deleteAllCollectionsTemplate() {

    return repoColections.dropCollectionsTemplate();
  }

  @Override
  public Flux<String> checkCollectionsTemplate() {

    return repoColections.checkCollectionsTemplate();
  }


}