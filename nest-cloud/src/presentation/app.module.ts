import { Module } from '@nestjs/common';
import { DatabaseModule } from 'src/di/DatabaseModule';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthModule } from './v1/auth/auth.module';
import { CategoryModule } from './v1/category/category.module';
import { ProductModule } from './v1/product/product.module';


@Module({
  imports: [
    AuthModule,
    DatabaseModule,
    CategoryModule,
    ProductModule
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }
