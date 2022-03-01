package com.webflux.api.core.config.annotations;

import com.webflux.api.core.config.testcontainer.TestcontainersConfig;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
@TestcontainersConfig
@ResourceConfig
@StartupConfig
@ActiveProfiles("gr-test-tc")
public @interface MergedResourceTc {
}