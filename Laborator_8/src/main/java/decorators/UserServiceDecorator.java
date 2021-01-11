package decorators;

import dataLayer.DatePeriod;
import producers.DatePeriodProvider;
import interfaces.IUserService;
import annotations.Period;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Date;

@Decorator
public class UserServiceDecorator implements IUserService {
    @Inject
    @Delegate
    @Default
    IUserService service;

    @Inject @Period
    DatePeriod period;

    @Override
    public int getNextId() throws SQLException {
        return service.getNextId();
    }

    @Override
    public boolean checkUser(String username, String password) throws SQLException {
        return service.checkUser(username,password);
    }

    @Override
    public boolean checkUsername(String username) throws SQLException {
        return service.checkUsername(username);
    }

    @Override
    public boolean registerUser(String username, String password, String role) throws SQLException {
        Date currentDate=new Date();
        this.period= DatePeriodProvider.getPeriod();
        if(currentDate.after(period.getStartDate()) && currentDate.before(period.getEndDate()))
        {
            service.registerUser(username,password,role);
            return true;
        }
        else
            {
            return false;
        }
    }

    @Override
    public int getId(String username) throws SQLException {
        return service.getId(username);
    }

    @Override
    public String getRole(String username) throws SQLException {
        return service.getRole(username);
    }
}
