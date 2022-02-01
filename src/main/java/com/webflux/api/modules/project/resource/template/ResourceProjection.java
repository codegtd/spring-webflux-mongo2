package com.webflux.api.modules.project.resource.template;

import com.webflux.api.modules.project.core.exceptions.ProjectExceptionsThrower;
import com.webflux.api.modules.project.core.dto.ProjectDto;
import com.webflux.api.modules.project.service.template.IServiceProjection;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.webflux.api.modules.project.core.routes.template.RoutesProjection.TEMPL_PROJECTION;
import static com.webflux.api.modules.project.core.routes.template.RoutesProjection.TEMPL_ROOT_PROJECTION;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(TEMPL_ROOT_PROJECTION)
public class ResourceProjection {

  private final MediaType JSON = MediaType.APPLICATION_JSON;


  IServiceProjection serviceProjection;
  private final ProjectExceptionsThrower projectExceptionsThrower;

  @GetMapping(TEMPL_PROJECTION)
  @ResponseStatus(OK)
  public Flux<ProjectDto> findAllProjectDto() {

    return
         serviceProjection
              .findAllProjectDto()
         ;

  }


}