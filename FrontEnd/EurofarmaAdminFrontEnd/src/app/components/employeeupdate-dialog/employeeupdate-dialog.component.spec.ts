import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EmployeeUpdateDialogComponent } from './employeeupdate-dialog.component';


describe('EmployeeupdateDialogComponent', () => {
  let component: EmployeeUpdateDialogComponent;
  let fixture: ComponentFixture<EmployeeUpdateDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeUpdateDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmployeeUpdateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
