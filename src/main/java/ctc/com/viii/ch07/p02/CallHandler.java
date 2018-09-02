package ctc.com.viii.ch07.p02;

import java.util.ArrayList;
import java.util.List;

/*
Reference: https://github.com/careercup/CtCI-6th-Edition/blob/master/Java/Ch%2007.%20Object-Oriented%20Design/Q7_02_Call_Center/Call.java
 */
public class CallHandler {

    /* We have 3 levels of employees: respondents, managers, directors. */
    private final int LEVELS = 3;

    /* Initialize with 10 respondents, 4 managers, and 2 directors. */
    private final int NUM_RESPONDENTS = 10;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;
    /* List of employees, by level.
     * employeeLevels[0] = respondents
     * employeeLevels[1] = managers
     * employeeLevels[2] = directors
     */
    List<List<Employee>> employeeLevels;

    /* queues for each call�s rank */
    List<List<Call>> callQueues;


    public CallHandler() {
        employeeLevels = new ArrayList<>(LEVELS);
        callQueues = new ArrayList<>(LEVELS);

        List<Employee> respondents = new ArrayList<>(NUM_RESPONDENTS);

        for (int k = 0; k < NUM_RESPONDENTS - 1; k++) {
            respondents.add(new Respondent(this));
        }
        employeeLevels.add(respondents);

        // Create managers.
        List<Employee> managers = new ArrayList<>(NUM_MANAGERS);
        managers.add(new Manager(this));
        employeeLevels.add(managers);

        // Create directors.
        List<Employee> directors = new ArrayList<>(NUM_DIRECTORS);
        directors.add(new Director(this));
        employeeLevels.add(directors);

    }


    /* Gets the first available employee who can handle this call. */
    public Employee getHandlerForCall(Call call) {
        for (int level = call.getRank().getValue(); level < LEVELS - 1; level++) {
            List<Employee> employeeLevel = employeeLevels.get(level);
            for (Employee emp : employeeLevel) {
                if (emp.isFree()) {
                    return emp;
                }
            }
        }
        return null;
    }

    /* Routes the call to an available employee, or saves in a queue if no employee available. */
    public void dispatchCall(Caller caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }

    /* Routes the call to an available employee, or saves in a queue if no employee available. */
    public void dispatchCall(Call call) {
        /* Try to route the call to an employee with minimal rank. */
        Employee emp = getHandlerForCall(call);
        if (emp != null) {
            emp.receiveCall(call);
            call.setHandler(emp);
        } else {
            /* Place the call into corresponding call queue according to its rank. */
            call.reply("Please wait for free employee to reply");
            callQueues.get(call.getRank().getValue()).add(call);
        }
    }

    /* An employee got free. Look for a waiting call that he/she can serve. Return true
     * if we were able to assign a call, false otherwise. */
    public boolean assignCall(Employee emp) {
        /* Check the queues, starting from the highest rank this employee can serve. */
        for (int rank = emp.getRank().getValue(); rank >= 0; rank--) {
            List<Call> que = callQueues.get(rank);

            /* Remove the first call, if any */
            if (que.size() > 0) {
                Call call = que.remove(0);
                if (call != null) {
                    emp.receiveCall(call);
                    return true;
                }
            }
        }
        return false;
    }


}
