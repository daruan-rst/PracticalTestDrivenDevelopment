package com.virtualpairprogrammers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RepaymentAmountTests {

    @Spy
    LoanApplication loanApplication;

    LoanCalculatorController controller;
    LoanRepository repository;
    JavaMailSender mailSender;
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp(){
        loanApplication = spy(new LoanApplication());
        controller = new LoanCalculatorController();

        repository = mock(LoanRepository.class);
        mailSender = mock(JavaMailSender.class);
        restTemplate = mock(RestTemplate.class);

        controller.setData(repository);
        controller.setMailSender(mailSender);
        controller.setRestTemplate(restTemplate);
    }

    @Test
    public void test1YearWholePounds(){

        loanApplication.setPrincipal(1200);
        loanApplication.setTermInMonths(12);
        doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal("110"), loanApplication.getRepayment());
    }

    @Test
    public void test2YearWholePounds(){

        loanApplication.setPrincipal(1200);
        loanApplication.setTermInMonths(24);
        doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal("60"), loanApplication.getRepayment());
    }

    @Test
    public void test5YearWithRounding(){

        loanApplication.setPrincipal(5000);
        loanApplication.setTermInMonths(60);
        doReturn(new BigDecimal(6.5)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal("111"), loanApplication.getRepayment());
    }
}
