package jpa.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class StudentServiceTest {

    StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testValidateStudent() {
        boolean exists = studentService.validateStudent("ard.def@goog.com", "qweras12");
        assertEquals(exists, false);
    }
}