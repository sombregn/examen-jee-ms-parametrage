import {Component, OnInit} from '@angular/core';
import {StudentRequest} from '../../../services/models/student-request';
import {StudentsService} from '../../../services/services/students.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';


@Component({
  selector: 'app-form-student',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './form-student.component.html',
  styleUrl: './form-student.component.css'
})

export class FormStudentComponent implements OnInit{

  errorMsg: Array<string> = []
  isEditMode = false;


  studentRequest: StudentRequest = {
    registrationNu: "",
    id: undefined,
    firstName: '',
    lastName: '',
    emailPerso: '',
    emailPro: '',
    phoneNumber: '',
    address: '',
    archive: false
  }
  constructor(
    private studentService: StudentsService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastrService: ToastrService
  ) {
  }
  ngOnInit(): void {
    const studentId = this.activatedRoute.snapshot.params['studentId'];
    if (studentId){
      this.isEditMode = true;
      this.studentService.getStudentById({
        'id': studentId
      }).subscribe({
        next: (student) => {
          this.studentRequest = {
            id: student.id,
            firstName: student.firstName as string,
            lastName: student.lastName as string,
            emailPerso: student.emailPerso as string,
            emailPro: student.emailPro as string,
            phoneNumber: student.phoneNumber as string,
            address: student.address as string,
            archive: false,
            registrationNu: student.registrationNu as string
          };
        }
      })
    }
  }

  saveStudent() {
    if (this.isEditMode) {
      this.updateStudent();
    } else {
      this.addStudent();
    }
  }

  private addStudent(){
    this.studentService.saveStudent({
      body: this.studentRequest
    }).subscribe({
      next: data => {
        this.toastrService.success("Student Add sucess")
        this.router.navigate(['/students']);
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.toastrService.error('Sorry try again !!');
        }
      }
    })
  }

  private updateStudent(){
    this.studentService.updateStudent({
      body: this.studentRequest
    }).subscribe({
      next: () => {
        this.toastrService.success("Student Update succes");
        this.router.navigate(['/students']);
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.toastrService.error("Sorry can not update Student");
        }
      }
    })
  }

}
