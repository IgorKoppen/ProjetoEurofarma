import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteBtnComponent } from './delete-btn.component';

describe('DeleteBtnComponent', () => {
  let component: DeleteBtnComponent;
  let fixture: ComponentFixture<DeleteBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteBtnComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
