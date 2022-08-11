import { Module } from '@nestjs/common';
import { DatabaseModule } from 'src/di/DatabaseModule';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthController } from './v1/auth/auth.controller';
import { AuthModule } from './v1/auth/auth.module';
import { AuthService } from './v1/auth/auth.service';
import { ConfigModule } from '@nestjs/config';


@Module({
  imports: [
    AuthModule,
    DatabaseModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }
