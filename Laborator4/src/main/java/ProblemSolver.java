
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DataLayer.DataProvider;
import DataLayer.SolveResult;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;

import javax.naming.NamingException;

public class ProblemSolver {

    Solver solver;
    int meetingsNumber;
    int agentsNumber;
    int timeslots;
    int[][] attendance;
    int[][] distance;

    public static long lastRunTime=0;
    IntVar[] meeting;


    ProblemSolver(int times) throws SQLException, ClassNotFoundException, ParseException, NamingException {
            meetingsNumber = DataProvider.getMeetingsNumber();
            agentsNumber = DataProvider.getPersonsNumber();
            timeslots = times;
            attendance = DataProvider.getAttendance();
            distance = DataProvider.getDistances();
            solver = new Solver("meeting scheduling problem"); // create an instance of Solver
            meeting = VF.enumeratedArray("all meetings", meetingsNumber, 0, timeslots - 1, solver);
            for (int m1 = 0; m1 < meetingsNumber; m1 ++) {
                for (int m2 = m1 + 1; m2 < meetingsNumber; m2++) {
                    boolean canCoincide = meetingsSameTime(m1,m2);
                    if(!canCoincide) {
                        Constraint eq1 = ICF.arithm(meeting[m1],"-",meeting[m2],">",distance[m1][m2]);
                        Constraint eq2 = ICF.arithm(meeting[m2],"-",meeting[m1],">",distance[m1][m2]);

                        solver.post(LCF.or(eq1,eq2));
                    }
                }
            }
        }

    ProblemSolver(InputStream file) throws  SQLException, ClassNotFoundException, ParseException {
        try (Scanner sc = new Scanner(file)) {
            meetingsNumber = sc.nextInt();
            agentsNumber = sc.nextInt();
            timeslots = sc.nextInt();
            attendance = new int[agentsNumber][meetingsNumber];
            distance = new int[meetingsNumber][meetingsNumber];
            for (int i = 0; i < agentsNumber; i++) {
                int n = 0;
                sc.next();
                for (int j = 0; j < meetingsNumber; j++) {
                    attendance[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < meetingsNumber; i++) {
                sc.next();
                for (int j = 0; j < meetingsNumber; j++) {
                    distance[i][j] = sc.nextInt();
                }
            }
        }
        solver = new Solver("meeting scheduling problem"); // create an instance of Solver
        meeting = VF.enumeratedArray("all meetings", meetingsNumber, 0, timeslots - 1, solver);
        for (int m1 = 0; m1 < meetingsNumber; m1 ++) {
            for (int m2 = m1 + 1; m2 < meetingsNumber; m2++) {
                boolean canCoincide = meetingsSameTime(m1,m2);
                if(!canCoincide) {
                    Constraint eq1 = ICF.arithm(meeting[m1],"-",meeting[m2],">",distance[m1][m2]);
                    Constraint eq2 = ICF.arithm(meeting[m2],"-",meeting[m1],">",distance[m1][m2]);

                    solver.post(LCF.or(eq1,eq2));
                }
            }
        }
    }

    private boolean meetingsSameTime(int meetingId1, int meetingId2) {
        for (int  i = 0; i < agentsNumber; i++) {
            if(attendance[i][meetingId1] == 1 && attendance[i][meetingId2] == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean solve() {
        long startTime = System.nanoTime();

        boolean result= solver.findSolution();
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        lastRunTime=totalTime;
        return result;

    }

    public List<SolveResult> result() {
        List<SolveResult> results=new ArrayList<SolveResult>();
        for (int i = 0; i < meetingsNumber; i++) {
            SolveResult result=new SolveResult();
            result.setMeetingIndex(i+1);
            result.setTimeslot(meeting[i].getValue()+1);
            results.add(result);
        }
        return results;
    }


}