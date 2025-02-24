package com.isi.dev.tpparametrage.student;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping("/students")
@Tag(name = "Students")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody @Valid StudentRequest studentRequest){
        return  ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(studentRequest));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable("id") Long id){
        log.info("Get student by id: {}", id);
        return  ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PutMapping()
    public ResponseEntity<StudentResponse> updateStudent(@Valid @RequestBody StudentRequest studentRequest){
        return  ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(studentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
