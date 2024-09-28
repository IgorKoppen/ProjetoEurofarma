import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentosChatbotCompliancePageComponent } from './documentos-chatbot-compliance-page.component';

describe('DocumentosChatbotCompliancePageComponent', () => {
  let component: DocumentosChatbotCompliancePageComponent;
  let fixture: ComponentFixture<DocumentosChatbotCompliancePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DocumentosChatbotCompliancePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DocumentosChatbotCompliancePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
