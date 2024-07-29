export interface Document {
    id: string;
    metadata: any;
  }
  
  export interface DocumentResponse {
    documents: Document[];
    metadata: {
      page_key: string;
    };
  }