package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {

    LoanService service = new LoanService();

    @Test
    void testValidEligibility() {
        assertTrue(service.isEligible(25, 30000));
    }

    @Test
    void testInvalidAge() {
        assertFalse(service.isEligible(18, 30000));
    }

    @Test
    void testInvalidSalary(){
        assertFalse(service.isEligible(22,22000));
    }

    @Test
    void testvalidSalary(){
        assertTrue(service.isEligible(22,27000));
    }


    @Test
    void testValidEMI() {
        double emi = service.calculateEMI(120000, 1);
        assertEquals(10000, emi);
    }

    @Test
    void testInvalidLoanAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateEMI(0, 2);
        });
    }

    @Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateEMI(100000, 0);
        });
    }

    @Test
    void testLoanCategory() {
        assertAll(
                () -> assertEquals("Premium", service.getLoanCategory(800)),
                () -> assertEquals("Standard", service.getLoanCategory(650)),
                () -> assertEquals("High Risk", service.getLoanCategory(500))
        );
    }

    @Test
    void testNotNullCategory() {
        assertNotNull(service.getLoanCategory(750));
    }
}
