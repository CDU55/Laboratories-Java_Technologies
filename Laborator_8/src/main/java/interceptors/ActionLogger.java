package interceptors;

import annotations.Logged;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Logged
public class ActionLogger implements Serializable {
    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {
        String message="Method "+invocationContext.getMethod().getName() + "called";
        LogWriter.writeTemporaryLog(message);
        LogWriter.writeAllTimeLog(message);
        return invocationContext.proceed();
    }

}
