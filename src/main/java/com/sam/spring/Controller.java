package com.sam.spring;

import com.sam.courseSchedule.CourseSchedule;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/planner", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CourseSchedule> runPlanner(@RequestBody CourseSchedule courseSchedule) {
        SolverFactory<CourseSchedule> solverFactory = SolverFactory.createFromXmlResource("courseScheduleSolverConfiguration.xml");
        Solver<CourseSchedule> solver = solverFactory.buildSolver();

        CourseSchedule solvedCourseSchedule = solver.solve(courseSchedule);

        return new ResponseEntity<CourseSchedule>(solvedCourseSchedule, HttpStatus.OK);
    }

}
