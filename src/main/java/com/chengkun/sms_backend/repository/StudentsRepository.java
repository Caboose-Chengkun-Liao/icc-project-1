package com.chengkun.sms_backend.repository;

import com.chengkun.sms_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
