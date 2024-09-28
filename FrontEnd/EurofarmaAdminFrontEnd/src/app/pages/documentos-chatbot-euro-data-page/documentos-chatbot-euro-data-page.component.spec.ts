import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentosChatbotEuroDataPageComponent } from './documentos-chatbot-euro-data-page.component';

describe('DocumentosChatbotEuroDataPageComponent', () => {
  let component: DocumentosChatbotEuroDataPageComponent;
  let fixture: ComponentFixture<DocumentosChatbotEuroDataPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DocumentosChatbotEuroDataPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DocumentosChatbotEuroDataPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
