import {Component, OnInit} from '@angular/core';
import {RegistrationRequest} from '../../../services/models/registration-request';
import {RegistrationsService} from '../../../services/services/registrations.service';
import {ClassesService} from '../../../services/services/classes.service';
import {StudentsService} from '../../../services/services/students.service';
import {ProgramsService} from '../../../services/services/programs.service';
import {AcademieYearsService} from '../../../services/services/academie-years.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {AcademieYearResponse, ClasseResponse, ProgramResponse, StudentResponse} from '../../../services/models';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-form-registration',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './form-registration.component.html',
  styleUrl: './form-registration.component.css'
})

export class FormRegistrationComponent implements OnInit{

  errorMsg: Array<string> = [];
  isEditMode = false;

  request: RegistrationRequest = {
    id: undefined,
    date: '',
    description: '',
    archive: false,
    studentId: 0,
    classeId: 0,
    programId: 0,
    academieYearId: 0
  }

  ngOnInit(): void {
    this.loadClasse();
    this.loadStudent();
    this.loadAcademie();
    this.loadProgram();
    this.loadRegistration();
  }

  constructor(
    private service: RegistrationsService,
    private classeService: ClassesService,
    private studentService: StudentsService,
    private programService: ProgramsService,
    private academieService: AcademieYearsService,
    private router: Router,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute,

  ) {
  }

  studentResponse: StudentResponse[] = [];
  classeResponse: ClasseResponse[] = [];
  programResponse: ProgramResponse[] = [];
  academieResponse: AcademieYearResponse[] = [];

  saveRegistration(){
    if (this.isEditMode){
      this.updateRegistration();
    }else {
      this.addRegistration();
    }
  }


  loadClasse(){
    this.classeService.getAllClasses().subscribe({
      next: (classes: ClasseResponse[]) => {
        this.classeResponse = classes
      },
      error:(err) => {
        console.log('Erreur lors du chargement')
      }
    })
  }

  loadStudent(){
    this.studentService.getStudents().subscribe({
      next: (students: StudentResponse[]) => {
        this.studentResponse = students
      },
      error:(err) => {
        console.log('Erreur lors du chargement')
      }
    })
  }

  loadProgram(){
    this.programService.getAllPrograms().subscribe({
      next: (programs: ProgramResponse[]) => {
        this.programResponse = programs
      },
      error:(err) => {
        console.log('Erreur lors du chargement')
      }
    })
  }

  loadAcademie(){
    this.academieService.getAllAcademieYears().subscribe({
      next: (academies: AcademieYearResponse[]) => {
        this.academieResponse = academies
      },
      error:(err) => {
        console.log('Erreur lors du chargement')
      }
    })
  }

  loadRegistration(){
    const registrationId = this.activatedRoute.snapshot.params['registrationId'];
    if (registrationId){
      this.isEditMode = true
      this.service.getRegistrationById({
        'id': registrationId
      }).subscribe({
        next: (registration) => {
          const selectedStudent = this.studentResponse.find(student => student.firstName+ ' ' + student.lastName === registration.studentFistName + ' ' + registration.studentLastName)
          const selectedProgram = this.programResponse.find(program => program.name === registration.programName)
          const selectedClasse = this.classeResponse.find(classe => classe.name === registration.classeName)
          const selectedAcademie = this.academieResponse.find(academie => academie.name === registration.academieName)
          this.request = {
            archive: false,
            id: registration.id,
            date: registration.date as string,
            description: registration.description as string,
            studentId: selectedStudent?.id ?? 0,
            classeId: selectedClasse?.id ?? 0,
            programId: selectedProgram?.id ?? 0,
            academieYearId: selectedAcademie?.id ?? 0
          }
        }
      })
    }
  }

  private addRegistration(){
    this.service.addRegistration({
      body: this.request
    }).subscribe({
      next: data => {
        this.toastr.success("Registration effectuée")
        this.router.navigate(['/registrations'])
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.toastr.error('Désolé, une erreur est survenue', 'Oups!');
        }
      }
    })
  }

  private updateRegistration(){
    this.service.updateRegistration({
      body: this.request
    }).subscribe({
      next: () =>{
        this.toastr.success("Registration mis a jour");
        this.router.navigate(['/registrations']);
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.toastr.error('Désolé, une erreur est survenue', 'Oups!');
        }
      }
    })
  }

}
