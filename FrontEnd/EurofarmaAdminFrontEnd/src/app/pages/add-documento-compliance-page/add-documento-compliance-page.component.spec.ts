import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDocumentoCompliancePageComponent } from './add-documento-compliance-page.component';

describe('AddDocumentoCompliancePageComponent', () => {
  let component: AddDocumentoCompliancePageComponent;
  let fixture: ComponentFixture<AddDocumentoCompliancePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddDocumentoCompliancePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddDocumentoCompliancePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
