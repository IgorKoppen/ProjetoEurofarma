import { Component, Input } from '@angular/core';
import { MatProgressBar } from '@angular/material/progress-bar';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-add-document-component',
  standalone: true,
  imports: [RouterLink,MatProgressBar],
  templateUrl: './add-document.component.html',
  styleUrl: './add-document.component.css'
})
export class AddDocumentComponentComponent {
  @Input() titulo = "";
  @Input({required:true}) onSubmit: (file: File,linkBack:String) => void = () => {};
  @Input({required:true})linkBack: String = "";
  isLoading:boolean = false;
  selectedFile: File | null = null;
  fileErrorMessage: string | null = null;
  

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const maxSizeInMB = 10;
    const maxSizeInBytes = maxSizeInMB * 1024 * 1024;

    if (file) {
      if (file.size > maxSizeInBytes) {
        this.fileErrorMessage = `O tamanho do arquivo excede o limite de  ${maxSizeInMB}MB.`;
        this.selectedFile = null;
        (event.target as HTMLInputElement).value = '';
      } else {
        this.selectedFile = file;
        this.fileErrorMessage = null;
      }
    }
  }
 
  sendForm(event:MouseEvent){
    event.preventDefault()
    this.isLoading = true;
    if(this.selectedFile){
    this.onSubmit(this.selectedFile,this.linkBack);
    }else{
      this.isLoading = false;
    }
   
  }
}