import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UsuariosListComponent } from './usuarios/usuarios-list.component';
import { UsuariosAddComponent } from './usuarios/usuarios-add.component';
import { UsuariosEditComponent } from './usuarios/usuarios-edit.component';
import { RolesListComponent } from './roles/roles-list.component';
import { RolesAddComponent } from './roles/roles-add.component';
import { RolesEditComponent } from './roles/roles-edit.component';
import { EmpresasListComponent } from './empresas/empresas-list.component';
import { EmpresasAddComponent } from './empresas/empresas-add.component';
import { EmpresasEditComponent } from './empresas/empresas-edit.component';
import { FichajesListComponent } from './fichajes/fichajes-list.component';
import { FichajesAddComponent } from './fichajes/fichajes-add.component';
import { FichajesEditComponent } from './fichajes/fichajes-edit.component';
import { HorasextrasListComponent } from './horasextras/horasextras-list.component';
import { HorasextrasAddComponent } from './horasextras/horasextras-add.component';
import { HorasextrasEditComponent } from './horasextras/horasextras-edit.component';
import { RegistrocambioscontraseniaListComponent } from './registrocambioscontrasenia/registrocambioscontrasenia-list.component';
import { RegistrocambioscontraseniaAddComponent } from './registrocambioscontrasenia/registrocambioscontrasenia-add.component';
import { RegistrocambioscontraseniaEditComponent } from './registrocambioscontrasenia/registrocambioscontrasenia-edit.component';
import { ConfiguracionautenticacionListComponent } from './configuracionautenticacion/configuracionautenticacion-list.component';
import { ConfiguracionautenticacionAddComponent } from './configuracionautenticacion/configuracionautenticacion-add.component';
import { ConfiguracionautenticacionEditComponent } from './configuracionautenticacion/configuracionautenticacion-edit.component';
import { HistorialactividadListComponent } from './historialactividad/historialactividad-list.component';
import { HistorialactividadAddComponent } from './historialactividad/historialactividad-add.component';
import { HistorialactividadEditComponent } from './historialactividad/historialactividad-edit.component';
import { ErrorComponent } from './error/error.component';


export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: $localize`:@@home.index.headline:Welcome to your new app!`
  },
  {
    path: 'usuarioss',
    component: UsuariosListComponent,
    title: $localize`:@@usuarios.list.headline:Usuarioses`
  },
  {
    path: 'usuarioss/add',
    component: UsuariosAddComponent,
    title: $localize`:@@usuarios.add.headline:Add Usuarios`
  },
  {
    path: 'usuarioss/edit/:idusuario',
    component: UsuariosEditComponent,
    title: $localize`:@@usuarios.edit.headline:Edit Usuarios`
  },
  {
    path: 'roless',
    component: RolesListComponent,
    title: $localize`:@@roles.list.headline:Roleses`
  },
  {
    path: 'roless/add',
    component: RolesAddComponent,
    title: $localize`:@@roles.add.headline:Add Roles`
  },
  {
    path: 'roless/edit/:idrol',
    component: RolesEditComponent,
    title: $localize`:@@roles.edit.headline:Edit Roles`
  },
  {
    path: 'empresass',
    component: EmpresasListComponent,
    title: $localize`:@@empresas.list.headline:Empresases`
  },
  {
    path: 'empresass/add',
    component: EmpresasAddComponent,
    title: $localize`:@@empresas.add.headline:Add Empresas`
  },
  {
    path: 'empresass/edit/:idempresa',
    component: EmpresasEditComponent,
    title: $localize`:@@empresas.edit.headline:Edit Empresas`
  },
  {
    path: 'fichajess',
    component: FichajesListComponent,
    title: $localize`:@@fichajes.list.headline:Fichajeses`
  },
  {
    path: 'fichajess/add',
    component: FichajesAddComponent,
    title: $localize`:@@fichajes.add.headline:Add Fichajes`
  },
  {
    path: 'fichajess/edit/:idfichaje',
    component: FichajesEditComponent,
    title: $localize`:@@fichajes.edit.headline:Edit Fichajes`
  },
  {
    path: 'horasextrass',
    component: HorasextrasListComponent,
    title: $localize`:@@horasextras.list.headline:Horasextrases`
  },
  {
    path: 'horasextrass/add',
    component: HorasextrasAddComponent,
    title: $localize`:@@horasextras.add.headline:Add Horasextras`
  },
  {
    path: 'horasextrass/edit/:idhoraextra',
    component: HorasextrasEditComponent,
    title: $localize`:@@horasextras.edit.headline:Edit Horasextras`
  },
  {
    path: 'registrocambioscontrasenias',
    component: RegistrocambioscontraseniaListComponent,
    title: $localize`:@@registrocambioscontrasenia.list.headline:Registrocambioscontrasenias`
  },
  {
    path: 'registrocambioscontrasenias/add',
    component: RegistrocambioscontraseniaAddComponent,
    title: $localize`:@@registrocambioscontrasenia.add.headline:Add Registrocambioscontrasenia`
  },
  {
    path: 'registrocambioscontrasenias/edit/:idregistro',
    component: RegistrocambioscontraseniaEditComponent,
    title: $localize`:@@registrocambioscontrasenia.edit.headline:Edit Registrocambioscontrasenia`
  },
  {
    path: 'configuracionautenticacions',
    component: ConfiguracionautenticacionListComponent,
    title: $localize`:@@configuracionautenticacion.list.headline:Configuracionautenticacions`
  },
  {
    path: 'configuracionautenticacions/add',
    component: ConfiguracionautenticacionAddComponent,
    title: $localize`:@@configuracionautenticacion.add.headline:Add Configuracionautenticacion`
  },
  {
    path: 'configuracionautenticacions/edit/:idautenticacion',
    component: ConfiguracionautenticacionEditComponent,
    title: $localize`:@@configuracionautenticacion.edit.headline:Edit Configuracionautenticacion`
  },
  {
    path: 'historialactividads',
    component: HistorialactividadListComponent,
    title: $localize`:@@historialactividad.list.headline:Historialactividads`
  },
  {
    path: 'historialactividads/add',
    component: HistorialactividadAddComponent,
    title: $localize`:@@historialactividad.add.headline:Add Historialactividad`
  },
  {
    path: 'historialactividads/edit/:idhistorial',
    component: HistorialactividadEditComponent,
    title: $localize`:@@historialactividad.edit.headline:Edit Historialactividad`
  },
  {
    path: 'error',
    component: ErrorComponent,
    title: $localize`:@@error.headline:Error`
  },
  {
    path: '**',
    component: ErrorComponent,
    title: $localize`:@@notFound.headline:Page not found`
  }
];
