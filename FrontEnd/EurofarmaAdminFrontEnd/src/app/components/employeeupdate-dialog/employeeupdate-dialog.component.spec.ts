import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeupdateDialogComponent } from './employeeupdate-dialog.component';

describe('EmployeeupdateDialogComponent', () => {
  let component: EmployeeupdateDialogComponent;
  let fixture: ComponentFixture<EmployeeupdateDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeupdateDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmployeeupdateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
