import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { ConfiguracionautenticacionService } from 'app/configuracionautenticacion/configuracionautenticacion.service';
import { ConfiguracionautenticacionDTO } from 'app/configuracionautenticacion/configuracionautenticacion.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { updateForm } from 'app/common/utils';


@Component({
  selector: 'app-configuracionautenticacion-edit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './configuracionautenticacion-edit.component.html'
})
export class ConfiguracionautenticacionEditComponent implements OnInit {

  configuracionautenticacionService = inject(ConfiguracionautenticacionService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  currentIdautenticacion?: number;

  editForm = new FormGroup({
    idautenticacion: new FormControl({ value: null, disabled: true }),
    usuarioid: new FormControl(null),
    codigosecreto: new FormControl(null, [Validators.maxLength(255)]),
    activado: new FormControl(false)
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      updated: $localize`:@@configuracionautenticacion.update.success:Configuracionautenticacion was updated successfully.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.currentIdautenticacion = +this.route.snapshot.params['idautenticacion'];
    this.configuracionautenticacionService.getConfiguracionautenticacion(this.currentIdautenticacion!)
        .subscribe({
          next: (data) => updateForm(this.editForm, data),
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.editForm.markAllAsTouched();
    if (!this.editForm.valid) {
      return;
    }
    const data = new ConfiguracionautenticacionDTO(this.editForm.value);
    this.configuracionautenticacionService.updateConfiguracionautenticacion(this.currentIdautenticacion!, data)
        .subscribe({
          next: () => this.router.navigate(['/configuracionautenticacions'], {
            state: {
              msgSuccess: this.getMessage('updated')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.editForm, this.getMessage)
        });
  }

}
