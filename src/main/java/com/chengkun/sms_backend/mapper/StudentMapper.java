package com.chengkun.sms_backend.mapper;

import com.chengkun.sms_backend.dto.StudentRequest;
import com.chengkun.sms_backend.dto.StudentResponse;
import com.chengkun.sms_backend.entity.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequest req) {
        return new Student(
                null,
                req.firstName(),
                req.lastName(),
                req.email()
        );
    }

    public static StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
