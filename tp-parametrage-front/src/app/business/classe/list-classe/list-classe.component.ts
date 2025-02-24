import {Component, OnInit} from '@angular/core';
import {ClasseResponse} from '../../../services/models/classe-response';
import {ClassesService} from '../../../services/services/classes.service';
import {ToastrService} from 'ngx-toastr';
import {Router, RouterLink} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-list-classe',
  imports: [
    NgIf,
    RouterLink
  ],
  templateUrl: './list-classe.component.html',
  styleUrl: './list-classe.component.css'
})

export class ListClasseComponent implements OnInit {

  response: ClasseResponse[] = [];
  // selectedClasseId: number | undefined
  constructor(
    private classeService: ClassesService,
    private toastr: ToastrService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.getAllClasses();
  }

  private getAllClasses(){
    this.classeService.getAllClasses()
      .subscribe({
        next: (classes: ClasseResponse[]) => {
          this.response = classes
        },
        error: (err) => {
          console.log("Erreur lors de chargement")
        }
      })
  }

  editClasse(classe: any){
    this.router.navigate(['/edit-classe', classe.id])
  }

  deleteClasse(classeId: number){
    if (confirm('Êtes-vous sûr de vouloir supprimer cette Classe ?')) {
      this.classeService.deleteClasseById({ id: classeId }).subscribe({
        next: () => {
          this.toastr.success("Classe supprimé avec succès");
          this.getAllClasses();
        },
        error: (err) => {
          console.log(err);
          this.toastr.error("Désolé, impossible de supprimer", "Oups !");
        }
      });
    }
  }
}
