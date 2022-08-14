import { NestFactory } from '@nestjs/core';
import { AppModule } from './presentation/app.module';
import { bootstrapSimulate } from './presentation/datasimulation/simulation';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  await app.listen(3000);
}
bootstrap();
// bootstrapSimulate()
