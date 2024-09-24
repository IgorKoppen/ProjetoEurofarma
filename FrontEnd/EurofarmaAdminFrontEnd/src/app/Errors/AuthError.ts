export class AuthException extends Error {
    public readonly status: number;
  
    constructor(message: string, status: number = 401) {
      super(message);
      this.name = '';
      this.status = status;
  
      Object.setPrototypeOf(this, AuthException.prototype);
    }
  }