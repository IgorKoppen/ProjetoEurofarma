import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisableEmployeeBtnComponent } from './disable-employee-btn.component';

describe('DisableEmployeeBtnComponent', () => {
  let component: DisableEmployeeBtnComponent;
  let fixture: ComponentFixture<DisableEmployeeBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisableEmployeeBtnComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisableEmployeeBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
