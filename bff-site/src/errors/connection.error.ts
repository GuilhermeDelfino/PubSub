import { HttpException } from '@nestjs/common';
export class ConnectionError extends HttpException {
  constructor(message: string) {
    super(message, 503);
  }
}
