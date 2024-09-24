import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableTrainingsComponent } from './table-trainings.component';

describe('TableTrainingsComponent', () => {
  let component: TableTrainingsComponent;
  let fixture: ComponentFixture<TableTrainingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableTrainingsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableTrainingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
