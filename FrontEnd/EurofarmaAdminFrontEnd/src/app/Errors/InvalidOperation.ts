export class InvalidOperation extends Error {
    public readonly statusCode: number;
  
    constructor(message: string, statusCode: number = 403) {
      super('Ao realizar a operação: ' + message);
      this.name = '';
      this.statusCode = statusCode;
  
      Object.setPrototypeOf(this, InvalidOperation.prototype);
    }
  }