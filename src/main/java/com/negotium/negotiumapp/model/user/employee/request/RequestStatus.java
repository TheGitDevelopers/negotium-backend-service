package com.negotium.negotiumapp.model.user.employee.request;

public enum RequestStatus {

    WAITING("Waiting"), ACCEPTED("Accepted"), REJECTED("Rejected");

    private String status;

    RequestStatus(String status){
        this.status = status;
    }
}