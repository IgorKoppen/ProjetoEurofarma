import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeinsertDialogComponent } from './employeeinsert-dialog.component';

describe('EmployeeinsertDialogComponent', () => {
  let component: EmployeeinsertDialogComponent;
  let fixture: ComponentFixture<EmployeeinsertDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeinsertDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmployeeinsertDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
