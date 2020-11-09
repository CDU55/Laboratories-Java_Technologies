import DataLayer.SolveResult;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@ManagedBean(name = "fetcher")
@RequestScoped
public class ResultFetcher {

    private List<SolveResult> results;
    @Min(value = 1,message = "The number of timeslots must be positive")
    private int timeslots;

    private Object file;

    public List<SolveResult> getResults() throws ClassNotFoundException, SQLException, ParseException, IOException {
        ProblemSolver solver;
        if(file!=null)
        {
            UploadedFile fileConverted=(UploadedFile)file;
            solver=new ProblemSolver(fileConverted.getInputstream());
        }
        else if(this.timeslots!=0)
        {
            solver=new ProblemSolver(this.timeslots);
        }
        else
        {
            solver=new ProblemSolver(10);
        }
        if(solver.solve())
        {
            this.results=solver.result();
        }
        else
        {
            this.results=new ArrayList<SolveResult>();
        }
        return results;
    }

    public void setResults(List<SolveResult> results) {
        this.results = results;
    }

    public int getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(int timeslots) {
        this.timeslots = timeslots;
    }

    public String toDisplayResult()
    {
        return "displayResult.xhtml";
    }

    public UploadedFile getFile() {
        return (UploadedFile)file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
