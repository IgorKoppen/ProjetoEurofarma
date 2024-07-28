import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDocumentComponentComponent } from './add-document.component';

describe('AddDocumentComponentComponent', () => {
  let component: AddDocumentComponentComponent;
  let fixture: ComponentFixture<AddDocumentComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddDocumentComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddDocumentComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
