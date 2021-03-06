package io.pivotal.services.plugin;

import io.pivotal.services.plugin.tasks.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Root of the Cloud Foundry Client Plugin
 *
 * @author Biju Kunjummen
 */
public class CfClientPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().create("cf-push", CfPushTask.class);
        project.getTasks().create("cf-map-route", CfMapRouteTask.class);
        project.getTasks().create("cf-unmap-route", CfUnMapRouteTask.class);
        project.getTasks().create("cf-delete-app", CfDeleteAppTask.class);
        project.getTasks().create("cf-delete-route", CfDeleteRouteTask.class);
        project.getTasks().create("cf-rename-app", CfRenameAppTask.class);
        project.getTasks().create("cf-get-app-detail", CfAppDetailsTask.class);
        project.getTasks().create("cf-start-app", CfAppStartTask.class);
        project.getTasks().create("cf-stop-app", CfAppStopTask.class);
        project.getTasks().create("cf-restart-app", CfAppRestartTask.class);
        project.getTasks().create("cf-restage-app", CfRestageTask.class);
        project.getTasks().create("cf-push-autopilot", CfAutoPilotTask.class);
        project.getTasks().create("cf-create-services", CfCreateServicesTask.class);
        CfBlueGreenStage1Task bgStage1Task = project.getTasks().create("cf-push-blue-green-1", CfBlueGreenStage1Task.class);
        CfBlueGreenStage2Task bgStage2Task = project.getTasks().create("cf-push-blue-green", CfBlueGreenStage2Task.class);
        bgStage2Task.dependsOn(bgStage1Task);

        project.getExtensions().create("cfConfig", CfPluginExtension.class, project);
    }
}
