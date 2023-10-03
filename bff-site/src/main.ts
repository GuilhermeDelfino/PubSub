import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';

const ORIGINS_CORS = ['http://localhost:3000', 'http://site-next:3000'];

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.enableCors({
    origin: ORIGINS_CORS,
  });
  app.useGlobalPipes(new ValidationPipe({ enableDebugMessages: true }));
  await app.listen(3332);
}
bootstrap();
