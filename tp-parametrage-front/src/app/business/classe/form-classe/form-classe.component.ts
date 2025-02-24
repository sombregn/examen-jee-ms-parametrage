import {Component, OnInit} from '@angular/core';
import {ClasseRequest} from '../../../services/models/classe-request';
import {ClassesService} from '../../../services/services/classes.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-form-classe',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './form-classe.component.html',
  styleUrl: './form-classe.component.css'
})
export class FormClasseComponent implements OnInit{

  errorMsg: Array<string> = []
  isEditMode = false;


  classeRequest: ClasseRequest = {
    id: undefined,
    name: '',
    description: '',
    archive: false
  }
  ngOnInit(): void {
    this.loadClasse();
  }

  constructor(
    private toastrService: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private classeService: ClassesService,
  ) {
  }

  loadClasse(){
    const classeId = this.activatedRoute.snapshot.params['classeId'];
    if (classeId){
      this.isEditMode = true
      this.classeService.getClasseById({
        'id': classeId,
      }).subscribe({
        next: (classe) => {
          this.classeRequest = {
            archive: false,
            id: classe.id,
            name: classe.name as string,
            description: classe.description as string
            // sectorId: selectedSector?.id ?? 0
          }
        }
      })
    }
  }

  saveClasse(){
    if (this.isEditMode){
      this.updateClasse()
    }else {
      this.addClasse();
    }
  }

  private addClasse(){
    this.classeService.addClasse({
      body: this.classeRequest
    }).subscribe({
      next: data => {
        this.toastrService.success("Classe ajouté")
        this.router.navigate(['/classes'])
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.toastrService.error('Désolé, une erreur est survenue!!!!');
        }
      }
    })
  }

  private updateClasse(){
    this.classeService.updateClasse({
      body: this.classeRequest
    }).subscribe({
      next: () =>{
        this.toastrService.success("Classe mis a jour");
        this.router.navigate(['/classes']);
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
