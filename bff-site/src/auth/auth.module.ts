import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { HttpModule } from '@nestjs/axios';
import { RedisCacheModule } from 'src/cache/redis-cache.module';
@Module({
  imports: [HttpModule, RedisCacheModule],
  providers: [AuthService],
  exports: [AuthService],
})
export class AuthModule {}
