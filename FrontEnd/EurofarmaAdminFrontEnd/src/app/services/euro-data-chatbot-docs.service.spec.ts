import { TestBed } from '@angular/core/testing';

import { EuroDataChatbotDocsService } from './euro-data-chatbot-docs.service';

describe('EuroDataChatbotDocsService', () => {
  let service: EuroDataChatbotDocsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EuroDataChatbotDocsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
