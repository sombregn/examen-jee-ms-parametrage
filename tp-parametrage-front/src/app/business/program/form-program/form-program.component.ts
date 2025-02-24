import {Component, OnInit} from '@angular/core';
import {ProgramRequest} from '../../../services/models/program-request';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {ProgramsService} from '../../../services/services/programs.service';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-form-program',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './form-program.component.html',
  styleUrl: './form-program.component.css'
})

export class FormProgramComponent implements OnInit{

  errorMsg: Array<string> = [];
  isEditMode = false;

  programRequest: ProgramRequest = {

    id: undefined,
    name: '',
    description: '',
    archive: false
  }

  ngOnInit(): void {
  }

  constructor(
    private programService: ProgramsService,
    private toastrService: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {
  }

  loadProgram(){
    const programId = this.activatedRoute.snapshot.params['programId'];
    if (programId){
      this.isEditMode = true
      this.programService.getProgramById({
        'id': programId,
      }).subscribe({
        next: (program) => {
          this.programRequest = {
            archive: false,
            id: program.id,
            name: program.name as string,
            description: program.description as string
          }
        }
      })
    }
  }

  saveProgram(){
    if (this.isEditMode){
      this.updateProgram()
    }else {
      this.addProgram()
    }
  }

  private addProgram(){
    this.programService.addProgram({
      body: this.programRequest
    }).subscribe({
      next: data => {
        this.toastrService.success("Program ajouté")
        this.router.navigate(['/programs'])
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.toastrService.error('Désolé, une erreur est survenue', 'Oups!');
        }
      }
    })
  }

  private updateProgram(){
    this.programService.updateProgram({
      body: this.programRequest
    }).subscribe({
      next: () =>{
        this.toastrService.success("Program mis a jour");
        this.router.navigate(['/programs']);
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.toastrService.error('Désolé, une erreur est survenue', 'Oups!');
        }
      }
    })
  }

}
