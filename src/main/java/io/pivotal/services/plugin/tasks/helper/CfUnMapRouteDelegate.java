package io.pivotal.services.plugin.tasks.helper;

import io.pivotal.services.plugin.CfProperties;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.cloudfoundry.operations.routes.UnmapRouteRequest;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import reactor.core.publisher.Mono;

/**
 * Helper responsible for unmapping a route
 *
 * @author Biju Kunjummen
 */
public class CfUnMapRouteDelegate {

    private static final Logger LOGGER = Logging.getLogger(CfUnMapRouteDelegate.class);

    public Mono<Void> unmapRoute(CloudFoundryOperations cfOperations, CfProperties cfProperties) {

        return cfOperations.routes()
            .unmap(UnmapRouteRequest
                .builder()
                .applicationName(cfProperties.name())
                .domain(cfProperties.domain())
                .host(cfProperties.host())
                .path(cfProperties.path())
                .build()).doOnSubscribe((s) -> {
                LOGGER.lifecycle("Unmapping hostname '{}' in domain '{}' with path '{}' of app '{}'", cfProperties.host(),
                    cfProperties.domain(), cfProperties.path(), cfProperties.name());
            });


    }

}
