import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { DepartmentService } from '../../services/department.service';
import { Department } from '../../interfaces/departmentInterface';
import { TagService } from '../../services/tag.service';
import { Tag } from '../../interfaces/trainingInterface';
import { MatRadioModule } from '@angular/material/radio';

@Component({
  selector: 'app-training-search-dialog',
  standalone: true,
  imports: [MatButtonModule,MatRadioModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent,MatFormFieldModule,MatInputModule,FormsModule,ReactiveFormsModule,MatSelectModule],
  templateUrl: './training-search-dialog.component.html',
  styleUrl: './training-search-dialog.component.css'
})
export class TrainingSearchDialogComponent {
  searchForm: FormGroup;
  tags: Tag[] = [];
  departments: Department[] = [];
  constructor(
    private fb: FormBuilder,
    private tagService: TagService,
    private departmentService: DepartmentService
  ) {
    this.searchForm = this.fb.group({
      filterType: ['criteria'],
      name: [''],
      employeeRegistration: [''],
      instructorRegistration: [''],
      tagId: [''],
      departmentId: ['']
    });
  }

  ngOnInit() {
    this.loadTags();
    this.loadDepartments();

    const filterTypeControl = this.searchForm.get('filterType');
    if (filterTypeControl) {
      filterTypeControl.valueChanges.subscribe(() => {
        this.resetFormFields();
      });
    }
  }

  private loadTags() {
    this.tagService.findAll().subscribe(data => {
      this.tags = data;
    });
  }

  private loadDepartments() {
    this.departmentService.findAll().subscribe(data => {
      this.departments = data;
    });
  }

  private resetFormFields() {
    const filterType = this.searchForm.get('filterType')?.value;
    const resetFields = {
        name: null,
        tagId: null,
        departmentId: null,
        employeeRegistration: null,
        instructorRegistration: null
    };

    if (filterType === 'employeeRegistration') {
        resetFields.instructorRegistration = null;
    } else if (filterType === 'instructorRegistration') {
        resetFields.employeeRegistration = null;
    } else{
      resetFields.departmentId = null;
      resetFields.name = null;
      resetFields.tagId = null;
    }

    this.searchForm.patchValue(resetFields, { emitEvent: false });
}

  getFormClass() {
    return this.searchForm.get('filterType')?.value !== 'criteria' ? 'formMini' : 'form';
  }
}
