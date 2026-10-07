package com.awaasai.backend.application;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "loan_type", nullable = false)
    private String loanType;

    @Column(name = "application_status", nullable = false)
    private String applicationStatus;

    public Application() {
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}