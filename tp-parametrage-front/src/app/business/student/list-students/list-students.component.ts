import {Component, OnInit} from '@angular/core';
import {StudentResponse} from '../../../services/models/student-response';
import {StudentsService} from '../../../services/services/students.service';
import {ToastrService} from 'ngx-toastr';
import {Router, RouterLink} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-list-students',
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './list-students.component.html',
  styleUrl: './list-students.component.css'
})
export class ListStudentsComponent implements OnInit{

  studentResponse: StudentResponse [] = []

  constructor(
    private studentService : StudentsService,
    private toastr: ToastrService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getAllStudents()
  }

  private getAllStudents(){
    this.studentService.getStudents()
      .subscribe({
        next: (students: StudentResponse[]) => {
          this.studentResponse = students
        },
        error: (err) => {
          console.error("Error Load Datas")
        }
      })
  }
  editStudent(student: any) {
    this.router.navigate(['/edit-student', student.id]);
  }

  deleteStudent(studentId: number) {
    if (confirm('Are you sure to delete this User ?')) {
      this.studentService.deleteStudent({ id: studentId }).subscribe({
        next: () => {
          this.toastr.success("Student delete sucess");
          this.getAllStudents();
        },
        error: (err) => {
          console.log(err);
          this.toastr.error("Sorry!!!, can not delete Student");
        }
      });
    }
  }
}
