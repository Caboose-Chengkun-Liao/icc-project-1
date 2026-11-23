package com.chengkun.sms_backend.controller;

import com.chengkun.sms_backend.dto.StudentRequest;
import com.chengkun.sms_backend.dto.StudentResponse;
import com.chengkun.sms_backend.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentRequest req) {
        StudentResponse savedStudent = studentService.createStudent(req);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping(params = "id")
    public ResponseEntity<StudentResponse> getStudentsById(@RequestParam Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest req) {
        return ResponseEntity.ok(studentService.updateStudent(id, req));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteStudent(@RequestParam Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
