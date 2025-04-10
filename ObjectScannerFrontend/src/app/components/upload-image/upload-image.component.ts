import { Component } from '@angular/core';
import { ObjectScanService } from '../object-scan.service'; // Ajuste o caminho conforme necessário

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent {
  imageFile: File | null = null;
  scanResult: any; // Você pode definir um tipo mais específico aqui, se necessário

  constructor(private objectScanService: ObjectScanService) {}

  onFileChange(event: any): void {
    this.imageFile = event.target.files[0];
  }

  onSubmit(): void {
    if (this.imageFile) {
      this.objectScanService.uploadImage(this.imageFile).subscribe(
        (result: any) => {  // Especificando o tipo 'any' ou o tipo exato esperado
          this.scanResult = result;
        },
        (error: any) => {  // Especificando o tipo 'any' ou um tipo de erro mais específico
          console.error('Erro na requisição', error);
        }
      );
    }
  }
}

