export interface DocumentChatbot {
    id: string;
    metadata: any;
  }
  
  export interface DocumentApiResponse {
    documents: DocumentChatbot[];
    metadata: {
      page_key: string;
    };
  }
  