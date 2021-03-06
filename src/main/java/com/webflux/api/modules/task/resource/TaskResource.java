package com.webflux.api.modules.task.resource;

import com.webflux.api.modules.task.entity.Task;
import com.webflux.api.modules.task.service.IServiceTask;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.webflux.api.modules.task.core.RoutesTask.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

// ==> EXCEPTIONS IN CONTROLLER:
// *** REASON: IN WEBFLUX, EXCEPTIONS MUST BE IN CONTROLLER - WHY?
//     - "Como stream pode ser manipulado por diferentes grupos de thread,
//     - caso um erro aconteça em uma thread que não é a que operou a controller,
//     - o ControllerAdvice não vai ser notificado "
//     - https://medium.com/nstech/programa%C3%A7%C3%A3o-reativa-com-spring-boot-webflux-e-mongodb-chega-de-sofrer-f92fb64517c3
@RestController
@RequiredArgsConstructor
@RequestMapping(TASK_ROOT)
public class TaskResource {

  @Autowired
  private final IServiceTask taskService;

  @PostMapping(TASK_CREATE)
  @ResponseStatus(CREATED)
  public Mono<Task> save(@RequestBody Task task) {

    return taskService.save(task);

  }

  @GetMapping(TASK_FIND_ALL)
  @ResponseStatus(OK)
  public Flux<Task> findAll() {

    return taskService.findAll();

  }

  //  public Mono<ServerResponse> delete(ServerRequest request) {
  //
  //    String id = request.pathVariable("id");
  //
  //        return
  //             ok()
  //                  .contentType(JSON)
  //                  .body(
  //                       taskService.deleteById(id),Void.class
  //                       )
  //                  .log();
  //  }
}