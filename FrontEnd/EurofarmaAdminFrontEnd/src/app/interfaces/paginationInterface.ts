interface Links {
    self: {
      href: string;
    };
  }
  
  interface Page {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
  }

  export {Links,Page}