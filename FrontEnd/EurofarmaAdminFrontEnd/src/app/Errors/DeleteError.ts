export class DeleteException extends Error {
    public readonly statusCode: number;
  
    constructor(message: string, statusCode: number = 400) {
      super('Falha ao deletar: ' + message);
      this.name = '';
      this.statusCode = statusCode;
  
      Object.setPrototypeOf(this, DeleteException.prototype);
    }
  }