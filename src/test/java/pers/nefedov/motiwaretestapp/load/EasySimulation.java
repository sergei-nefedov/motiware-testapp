package pers.nefedov.motiwaretestapp.load;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.internal.HttpCheckBuilders.status;
import static io.gatling.javaapi.jdbc.JdbcDsl.jdbcFeeder;

public class EasySimulation extends Simulation {
    FeederBuilder<Object> formingfProjectsIdFeeder = jdbcFeeder("jdbc:postgresql://localhost:5432/testapp_db", "test_app", "password", "SELECT id FROM projects WHERE condition='FORMING'").random();
    FeederBuilder<Object> approvingProjectsIdFeeder = jdbcFeeder("jdbc:postgresql://localhost:5432/testapp_db", "test_app", "password", "SELECT id FROM projects WHERE condition='APPROVING'").random();
    FeederBuilder<Object> worksIdFeeder = jdbcFeeder("jdbc:postgresql://localhost:5432/testapp_db", "test_app", "password", "SELECT id FROM works").random();

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

    ScenarioBuilder scn = scenario("ProjectConditionChangeSimulation")
            .repeat(25).on(
                    feed(formingfProjectsIdFeeder).
                            exec(http("Send project to approving")
                                    .patch("/project/to_approving").queryParam("projectId", "#{id}")
                                    .check(status().is(200)))
                            .pause(Duration.ofMillis(200)))
            .repeat(25).on(
                    feed(approvingProjectsIdFeeder).
                            exec(http("Return project for refinement")
                                    .patch("/project/to_refinement").queryParam("projectId", "#{id}")
                                    .check(status().is(200)))
                            .pause(Duration.ofMillis(200)))
            .repeat(25).on(
                    feed(formingfProjectsIdFeeder).
                            exec(http("Get works by project")
                                    .get("/work/get_by_project/#{id}")
                                    .check(status().is(200)))
                            .pause(Duration.ofMillis(200)))
            .repeat(25).on(
                    feed(worksIdFeeder).
                            exec(http("Change work")
                                    .patch("/work/patch").body(StringBody("""
                                            {"id": "#{id}", "name": "New name", "averageCompletionPercentage": 0}""")).asJson()
                                    .check(status().is(200)))
                            .pause(Duration.ofMillis(200))
            );

    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }


}


