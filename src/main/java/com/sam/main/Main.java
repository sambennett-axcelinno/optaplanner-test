package com.sam.main;

import com.sam.courseSchedule.CourseSchedule;
import com.sam.lecture.Lecture;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        System.out.println("Hello World");
        Lecture lecture = new Lecture(123, 1, "Bennett");
        Lecture lecture1 = new Lecture(123, 1, "Hulk");
        Lecture lecture2 = new Lecture(123, 1, "IronMan");
        Lecture lecture3 = new Lecture(123, 1, "Thor");
        Lecture lecture4 = new Lecture(123, 1, "AntMan");
        List<Integer> rooms = new ArrayList<>();
        rooms.add(123);
        rooms.add(201);
        rooms.add(303);
        List<Integer> periods = new ArrayList<>();
        periods.add(1);
        periods.add(2);
        //periods.add(3);
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(lecture);
        lectures.add(lecture1);
        lectures.add(lecture2);
        lectures.add(lecture3);
        lectures.add(lecture4);
        CourseSchedule courseSchedule = new CourseSchedule(rooms, periods, lectures, null);
        /*System.out.println(courseSchedule.getRoomList());
        System.out.println(courseSchedule.getPeriodList());
        System.out.println(courseSchedule.getLectureList().toString());
        System.out.println(courseSchedule.getScore());*/

        SolverFactory<CourseSchedule> solverFactory = SolverFactory.createFromXmlResource("courseScheduleSolverConfiguration.xml");
        Solver<CourseSchedule> solver = solverFactory.buildSolver();

        CourseSchedule solvedCourseSchedule = solver.solve(courseSchedule);
        System.out.println(solvedCourseSchedule.printCourseSchedule());
        System.out.println(solvedCourseSchedule.getScore().getHardScore());
    }

}
