import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingSearchDialogComponent } from './training-search-dialog.component';

describe('TrainingSearchDialogComponent', () => {
  let component: TrainingSearchDialogComponent;
  let fixture: ComponentFixture<TrainingSearchDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainingSearchDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainingSearchDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
