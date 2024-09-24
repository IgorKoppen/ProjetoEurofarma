import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeSearchDialogComponent } from './employee-search-dialog.component';

describe('EmployeeSearchDialogComponent', () => {
  let component: EmployeeSearchDialogComponent;
  let fixture: ComponentFixture<EmployeeSearchDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeSearchDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeSearchDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
