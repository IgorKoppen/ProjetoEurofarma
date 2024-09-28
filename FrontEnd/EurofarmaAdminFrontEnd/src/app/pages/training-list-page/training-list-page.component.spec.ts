import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingListPageComponent } from './training-list-page.component';

describe('TrainingListPageComponent', () => {
  let component: TrainingListPageComponent;
  let fixture: ComponentFixture<TrainingListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainingListPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainingListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
