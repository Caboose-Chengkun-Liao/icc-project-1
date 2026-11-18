package com.chengkun.sms_backend.service;

import com.chengkun.sms_backend.dto.StudentRequest;
import com.chengkun.sms_backend.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest req);

    StudentResponse getStudentById(Long studentId);

    List<StudentResponse> getAllStudents();

    StudentResponse updateStudent(Long studentId, StudentRequest req);

    void deleteStudent(Long studentId);
}
