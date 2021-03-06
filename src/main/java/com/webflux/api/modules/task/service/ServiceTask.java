package com.webflux.api.modules.task.service;

import com.webflux.api.modules.task.entity.Task;
import com.webflux.api.modules.task.repo.ITaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("taskService")
@RequiredArgsConstructor
public class ServiceTask implements IServiceTask {

  private final ITaskRepo taskRepo;

  @Override
  public Mono<Task> save(Task task) {

    return taskRepo.save(task);
  }

  @Override
  public Flux<Task> findAll() {

    return taskRepo.findAll();
  }

  //  @Override
  //  public Mono<Void> deleteById(String id) {
  //
  //    return taskRepo.deleteById(id);
  //  }

}