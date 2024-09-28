import { TestBed } from '@angular/core/testing';

import { EuroComplianceChatbotDocsService } from './euro-compliance-chatbot-docs.service';

describe('EuroComplianceChatbotDocsService', () => {
  let service: EuroComplianceChatbotDocsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EuroComplianceChatbotDocsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
