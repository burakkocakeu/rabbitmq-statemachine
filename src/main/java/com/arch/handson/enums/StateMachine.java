package com.arch.handson.enums;

public enum StateMachine {
    Submitted {
        @Override
        public StateMachine nextState() {
            return Escalated;
        }

        @Override
        public String responsiblePerson() {
            return "Employee";
        }
    },
    Escalated {
        @Override
        public StateMachine nextState() {
            return Approved;
        }

        @Override
        public String responsiblePerson() {
            return "Team Leader";
        }
    },
    Approved {
        @Override
        public StateMachine nextState() {
            return this;
        }

        @Override
        public String responsiblePerson() {
            return "Department Manager";
        }
    };

    public abstract StateMachine nextState();
    public abstract String responsiblePerson();
}
