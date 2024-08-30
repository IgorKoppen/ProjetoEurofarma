export class AuthException extends Error {
    public readonly statusCode: number;
  
    constructor(message: string, statusCode: number = 401) {
      super(message);
      this.name = '';
      this.statusCode = statusCode;
  
      Object.setPrototypeOf(this, AuthException.prototype);
    }
  }