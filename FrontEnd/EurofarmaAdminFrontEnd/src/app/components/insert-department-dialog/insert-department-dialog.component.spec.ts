import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertDepartmentDialogComponent } from './insert-department-dialog.component';

describe('InsertDepartmentDialogComponent', () => {
  let component: InsertDepartmentDialogComponent;
  let fixture: ComponentFixture<InsertDepartmentDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsertDepartmentDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InsertDepartmentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
