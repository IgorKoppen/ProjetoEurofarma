import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDocumentoEurofarmaPageComponent } from './add-documento-eurofarma-page.component';

describe('AddDocumentoEurofarmaPageComponent', () => {
  let component: AddDocumentoEurofarmaPageComponent;
  let fixture: ComponentFixture<AddDocumentoEurofarmaPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddDocumentoEurofarmaPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddDocumentoEurofarmaPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
