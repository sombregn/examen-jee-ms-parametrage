import {Component, OnInit} from '@angular/core';
import {RegistrationResponse} from '../../../services/models/registration-response';
import {Router, RouterLink} from '@angular/router';
import {RegistrationsService} from '../../../services/services/registrations.service';
import {ToastrService} from 'ngx-toastr';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-list-registration',
  imports: [
    NgIf,
    RouterLink
  ],
  templateUrl: './list-registration.component.html',
  styleUrl: './list-registration.component.css'
})

export class ListRegistrationComponent implements OnInit{

  response: RegistrationResponse[] = []

  message = '';

  constructor(
    private router: Router,
    private registrationService: RegistrationsService,
    private toastr: ToastrService
  ) {
  }
  ngOnInit(): void {
    this.getAllRegistration();
  }

  private getAllRegistration(){
    this.registrationService.getAllRegistrations({
    }).subscribe({
      next: (registrations: RegistrationResponse[]) => {
        this.response = registrations
      },
      error: (err) => {
        console.log("Erreur lors de chargement")
      }
    })
  }

  editRegistration(registration: any){
    this.router.navigate(['/edit-registration', registration.id])
  }

  deleteRegistration(registrationId: number){
    if (confirm('Êtes-vous sûr de vouloir supprimer cette Registration ?')) {
      this.registrationService.deleteRegistrationById({ id: registrationId }).subscribe({
        next: () => {
          this.toastr.success("Registration supprimée avec succès");
          this.getAllRegistration();
        },
        error: (err) => {
          console.log(err);
          this.toastr.error("Désolé, impossible de supprimer", "Oups !");
        }
      });
    }
  }

}
