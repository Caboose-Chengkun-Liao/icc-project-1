package com.chengkun.sms_backend.service.impl;

import com.chengkun.sms_backend.dto.StudentRequest;
import com.chengkun.sms_backend.dto.StudentResponse;
import com.chengkun.sms_backend.entity.Student;
import com.chengkun.sms_backend.exception.DuplicateResourceException;
import com.chengkun.sms_backend.exception.ResourceNotFoundException;
import com.chengkun.sms_backend.mapper.StudentMapper;
import com.chengkun.sms_backend.repository.StudentsRepository;
import com.chengkun.sms_backend.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentsRepository studentsRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public StudentResponse createStudent(StudentRequest req) {
        if(studentsRepository.existsByEmail(req.email()))
            throw new DuplicateResourceException("Email " + req.email() + " already exists");
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public StudentResponse updateStudent(Long studentId, StudentRequest req) {
        Student student = studentsRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student of id " + studentId + " doesn't exist."
                )
        );

        student.setFirstName(req.firstName());
        student.setLastName(req.lastName());
        student.setEmail(req.email());

        return StudentMapper.toResponse(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student of id " + studentId + " doesn't exist."
                )
        );

        studentsRepository.delete(student);
    }
}
