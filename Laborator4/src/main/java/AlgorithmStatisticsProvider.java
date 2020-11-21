import org.chocosolver.solver.Solver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ManagedBean(name = "algorithmStatistics")
@SessionScoped
public class AlgorithmStatisticsProvider  {

    private  String message;
    public void getAlgorithmInfo() {
        if(ProblemSolver.lastRunTime==0)
        {
            message="The algorithm has not been run";
        }
        else
        {
            message="Last algorithm run time : "+ProblemSolver.lastRunTime;
        }
    }

    public  String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }
}