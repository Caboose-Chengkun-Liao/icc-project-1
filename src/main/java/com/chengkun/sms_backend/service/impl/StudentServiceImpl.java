package com.chengkun.sms_backend.service.impl;

import com.chengkun.sms_backend.dto.StudentRequest;
import com.chengkun.sms_backend.dto.StudentResponse;
import com.chengkun.sms_backend.entity.Student;
import com.chengkun.sms_backend.exception.ResourceNotFoundException;
import com.chengkun.sms_backend.mapper.StudentMapper;
import com.chengkun.sms_backend.repository.StudentsRepository;
import com.chengkun.sms_backend.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentsRepository studentsRepository;

    @Override
    public StudentResponse createStudent(StudentRequest req) {
        Student student = StudentMapper.toEntity(req);
        Student savedStudent = studentsRepository.save(student);
        return StudentMapper.toResponse(savedStudent);
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student of id " + studentId + " doesn't exist."
                )
        );
        return StudentMapper.toResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentsRepository.findAll().stream()
                .map(StudentMapper::toResponse)
                .toList();
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest req) {
        Student student = studentsRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student of id " + studentId + " doesn't exist."
                )
        );

        student.setFirstName(req.firstName());
        student.setLastName(req.lastName());
        student.setEmail(req.email());

        Student updated = studentsRepository.save(student);

        return StudentMapper.toResponse(updated);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student of id " + studentId + " doesn't exist."
                )
        );

        studentsRepository.delete(student);
    }
}
