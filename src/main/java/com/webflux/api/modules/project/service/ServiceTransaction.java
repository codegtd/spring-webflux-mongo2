package com.webflux.api.modules.project.service;

import com.webflux.api.modules.project.core.exceptions.ProjectExceptionsThrower;
import com.webflux.api.modules.project.entity.Project;
import com.webflux.api.modules.task.core.exceptions.TaskExceptionsThrower;
import com.webflux.api.modules.task.entity.Task;
import com.webflux.api.modules.task.repo.ITaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service("serviceTransaction")
@RequiredArgsConstructor
public class ServiceTransaction implements IServiceTransaction {

  private final ProjectExceptionsThrower projectThrower;
  private final TaskExceptionsThrower taskThrower;
  private final IServiceRepo serviceRepo;
  private final ITaskRepo taskRepo;

  /*
  EXCEPTIONS:
  A) SERVICE: BLOW-UP EXCEPTIONS IN THE SERVICE
  B) CONTROLLER: TREAT/HANDLE EXCEPTIONS IN THE CONTROLLER(ON-ERROR-RESUME)
   */
  //  @Transactional(transactionManager="transactionManager1")
  @Transactional
  @Override
  public Mono<Task> createProjectTransaction(Project project, Task task) {

    // @formatter:off
    return
         Mono.just(project)
             .flatMap(proj1 -> {
               if (proj1.getName().isEmpty()) return projectThrower.throwProjectNameIsEmptyException();
               return Mono.just(proj1); })
             .flatMap(serviceRepo::save)
             .flatMap(proj3 -> {
               task.setProjectId(proj3.get_id());
               return Mono.just(task); })
             .flatMap(task1 -> {
               if (task1.getName().isEmpty()) return taskThrower.throwTaskNameIsEmptyException();
               if (task1.getName().length() < 3) return taskThrower.throwTaskNameLessThanThreeException();
               return Mono.just(task1);
             })
             .flatMap(taskRepo::save)
         ;
    // @formatter:on
  }

}