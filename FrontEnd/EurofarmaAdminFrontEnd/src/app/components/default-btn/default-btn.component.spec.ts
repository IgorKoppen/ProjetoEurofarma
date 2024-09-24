import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultBtnComponent } from './default-btn.component';

describe('DefaultBtnComponent', () => {
  let component: DefaultBtnComponent;
  let fixture: ComponentFixture<DefaultBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefaultBtnComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefaultBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
