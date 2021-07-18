package com.arch.handson.controller;

import com.arch.handson.enums.StateMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UNIT TEST - No annotation, path, servlet or request and response in the life cycle.
 *
 * Unit testing means testing individual modules of an application in isolation (without any interaction with dependencies)
 *  to confirm that the code is doing things right.
 */
class CustomerControllerUTTest {

    @Test
    void list() {
        // Testing controller's list method
        CustomerController controller = new CustomerController();
        String response = controller.list("burak");
        assertEquals("Hello, burak", response);

        // Testing StateMachine's responsiblePerson method
        StateMachine state = StateMachine.Submitted;
        assertEquals("Employee", state.responsiblePerson());
        assertEquals(StateMachine.Submitted, state);
        state = state.nextState();
        assertEquals("Team Leader", state.responsiblePerson());
        assertEquals(StateMachine.Escalated, state);
        state = state.nextState();
        assertEquals("Department Manager", state.responsiblePerson());
        assertEquals(StateMachine.Approved, state);
    }
}