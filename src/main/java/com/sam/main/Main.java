package com.sam.main;

import com.sam.courseSchedule.CourseSchedule;
import com.sam.lecture.Lecture;
import com.sam.room.Room;
import com.sam.teacher.Teacher;
import com.sam.timeslot.Timeslot;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String args[]) {
        System.out.println("Hello World");
        /*Lecture lecture = new Lecture(123, 1, "Bennett");
        Lecture lecture1 = new Lecture(123, 1, "Hulk");
        Lecture lecture2 = new Lecture(123, 1, "IronMan");
        Lecture lecture3 = new Lecture(123, 1, "Thor");
        Lecture lecture4 = new Lecture(123, 1, "AntMan");*/
        Teacher teacher = new Teacher("Bennett");
        Teacher teacher1 = new Teacher("Hulk");
        Teacher teacher2 = new Teacher("IronMan");
        Teacher teacher3 = new Teacher("Thor");
        Teacher teacher4 = new Teacher("AntMan");
        /*Lecture lecture = new Lecture(teacher);
        Lecture lecture1 = new Lecture(teacher1);
        Lecture lecture2 = new Lecture(teacher2);
        Lecture lecture3 = new Lecture(teacher3);
        Lecture lecture4 = new Lecture(teacher4);*/
        Lecture lecture = new Lecture(teacher, 1);
        Lecture lecture1 = new Lecture(teacher1, 2);
        Lecture lecture2 = new Lecture(teacher2, 3);
        Lecture lecture3 = new Lecture(teacher3, 4);
        Lecture lecture4 = new Lecture(teacher4, 5);
        Lecture lecture5 = new Lecture(teacher, 6);
        Lecture lecture6 = new Lecture(teacher1, 7);

        /*List<Integer> rooms = new ArrayList<>();
        rooms.add(123);
        rooms.add(201);
        rooms.add(303);*/
        Room room = new Room(123, null);
        Room room1 = new Room(201, null);
        Room room2 = new Room(303, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room1);
        rooms.add(room2);

        List<Integer> periods = new ArrayList<>();
        periods.add(1);
        periods.add(2);
        periods.add(3);
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(lecture);
        lectures.add(lecture1);
        lectures.add(lecture2);
        lectures.add(lecture3);
        lectures.add(lecture4);
        lectures.add(lecture5);
        lectures.add(lecture6);

        List<Timeslot> timeslotList = new ArrayList<>();
        Timeslot timeslot = new Timeslot();
        timeslotList.add(timeslot);
        CourseSchedule courseSchedule = new CourseSchedule(rooms, periods, lectures, null, timeslotList);

        SolverFactory<CourseSchedule> solverFactory = SolverFactory.createFromXmlResource("courseScheduleSolverConfiguration.xml");
        Solver<CourseSchedule> solver = solverFactory.buildSolver();

        CourseSchedule solvedCourseSchedule = solver.solve(courseSchedule);
        System.out.println(solvedCourseSchedule.printCourseSchedule());
        System.out.println(solvedCourseSchedule.getScore().getHardScore());
        System.out.println(solvedCourseSchedule.getScore().getSoftScore());
        System.out.println(solvedCourseSchedule.getScore().isFeasible());
    }

}
