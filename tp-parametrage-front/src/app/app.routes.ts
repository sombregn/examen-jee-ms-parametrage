import { Routes } from '@angular/router';
import {SidebarComponent} from './shared/component/sidebar/sidebar/sidebar.component';
import {ListStudentsComponent} from './business/student/list-students/list-students.component';
import {FormStudentComponent} from './business/student/form-student/form-student.component';
import {ListClasseComponent} from './business/classe/list-classe/list-classe.component';
import {FormClasseComponent} from './business/classe/form-classe/form-classe.component';
import {FormProgramComponent} from './business/program/form-program/form-program.component';
import {ListProgramComponent} from './business/program/list-program/list-program.component';
import {FormRegistrationComponent} from './business/registration/form-registration/form-registration.component';
import {ListRegistrationComponent} from './business/registration/list-registration/list-registration.component';

export const routes: Routes = [

  {
    path: '',
    component: SidebarComponent,
    children: [
      {
        path: 'students',
        component: ListStudentsComponent
      },
      {
        path: 'add-student',
        component: FormStudentComponent
      },
      {
        path: 'edit-student/:studentId',
        component: FormStudentComponent
      },
      {
        path: 'classes',
        component: ListClasseComponent
      },
      {
        path: 'add-classe',
        component: FormClasseComponent
      },
      {
        path: 'edit-classe/:classeId',
        component: FormClasseComponent
      },
      {
        path: 'programs',
        component: ListProgramComponent
      },
      {
        path: 'add-program',
        component: FormProgramComponent
      },
      {
        path: 'edit-program/:programId',
        component: FormProgramComponent
      },
      {
        path: 'registrations',
        component: ListRegistrationComponent
      },
      {
        path: 'add-registration',
        component: FormRegistrationComponent
      },
      {
        path: 'edit-registration/:registrationId',
        component: FormRegistrationComponent
      },
    ]
  }
];



